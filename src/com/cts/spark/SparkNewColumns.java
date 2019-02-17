package com.cts.spark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import scala.util.regexp.Base.RegExp;

import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.hive.HiveContext;

public class SparkNewColumns {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		SparkConf conf = new SparkConf().setAppName("Spark-New Columns").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(conf);
		HiveContext sc = new org.apache.spark.sql.hive.HiveContext(jsc);
		//SQLContext sc = new org.apache.spark.sql.SQLContext(jsc);
		
		DataFrame df = sc.read().format("com.databricks.spark.csv").option("header", "true").load("/home/vinay/Downloads/iplDataSet/deliveries.csv").na().fill("");
		//df.show(5);
		df.printSchema();
		
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("Sunrisers Hyderabad");temp.add("Rising Pune Supergiant");temp.add("Kolkata Knight Riders");
		temp.add("Kings XI Punjab");temp.add("Royal Challengers Bangalore");
		temp.add("Sunrisers Hyderabad");
		
		Map<String,ArrayList<String>> newColumns = new HashMap<String,ArrayList<String>>();
		newColumns.put("WINNER",temp);
		
		List<Row> rw = new ArrayList<Row>();
		List<String> dummy = new ArrayList<String>();
		for(Map.Entry<String, ArrayList<String>> map : newColumns.entrySet()){
			
			dummy.add(map.getKey());
			dummy.addAll(map.getValue());
			
		}
		
		dummy.forEach(x -> rw.add(RowFactory.create(x)));
		
		String schemaString = "WINNER";
		List<StructField> fields = new ArrayList<StructField>();
		for (String fieldName: schemaString.split(" ")) {
		  fields.add(DataTypes.createStructField(fieldName, DataTypes.StringType, true));
		}
		StructType schema = DataTypes.createStructType(fields);
		
		
		DataFrame dfs =  sc.createDataFrame(rw, schema);
		//dfs.show();
		
		//firstset = firstset.withColumn("id", functions.row_number().over(Window.orderBy(finalkeys.split(",")[0])));
		
		dfs = dfs.withColumn("VINAY_ID", functions.row_number().over(Window.orderBy("WINNER")));
		df = df.withColumn("VINAY_ID", functions.row_number().over(Window.orderBy("INNING")));
		

		DataFrame finalset = df.join(dfs, df.col("VINAY_ID").equalTo(dfs.col("VINAY_ID")), "outer").drop("VINAY_ID");
		//finalset.show();
		finalset.registerTempTable("FINALSET");

		//Updating the column value
		//dataframe.withColumn("make", when(col("make").equalTo("Tesla"), "S").otherwise(col("make")) -- 1.6.2
		//DataFrame update =  finalset.replace("WINNER", "");
       //finalset.withColumn("WINNER", when(finalset.col("WINNER").equalTo("WINNER"),"WINNER").otherwise("value"));
		//DataFrame org = sc.sql("SELECT *, CASE WHEN WINNER = 'Sunrisers Hyderabad' THEN 'false' ELSE 'true' END AS WINNER FROM FINALSET");
		//org.show();
		
		DataFrame replace = finalset.filter("WINNER='Sunrisers Hyderabad'");
		replace.registerTempTable("GSD");

		DataFrame replace1 = sc.sql("SELECT *, CASE WHEN WINNER = 'Sunrisers Hyderabad' THEN 'VINAY' END AS WIN FROM GSD").drop("WINNER").withColumnRenamed("WIN", "WINNER");
		//replace1.show();
		DataFrame org = finalset.filter("WINNER!='Sunrisers Hyderabad'");
		
		DataFrame newFrame = replace1.unionAll(org);
		newFrame.show();
		
		
	}
	
}

