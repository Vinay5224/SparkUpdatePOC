package com.cts.utils;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.hive.HiveContext;

public class SparkConfigurations {
	
	public SparkConf conf; 
	public JavaSparkContext jsc; 
	public HiveContext sc;
	
	public void  sparkutils(){
		conf = new SparkConf().setAppName("Spark-ETL").setMaster("local");
		conf.set("fs.defaultFS", "hdfs://34.231.82.9:8020");
		jsc = new JavaSparkContext(conf);
		sc = new org.apache.spark.sql.hive.HiveContext(jsc);
	}

}
