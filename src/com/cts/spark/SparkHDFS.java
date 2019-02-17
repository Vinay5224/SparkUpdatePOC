package com.cts.spark;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.DataFrame;

import com.cts.utils.SparkConfigurations;

public class SparkHDFS {
	
	
	
	public static void main(String args[]) throws IOException{
		SparkHDFS sp = new SparkHDFS();
		sp.hdfs("hdfs://34.231.82.9:8020/user/cloudera/individual_source");
	}
	
	public String hdfs(String filepath) throws IOException{
	
		SparkConfigurations sc = new SparkConfigurations();
		sc.sparkutils();
		DataFrame df = sc.sc.read().load(filepath);
		df.show();
	     FileSystem hdfs = org.apache.hadoop.fs.FileSystem.get(sc.jsc.hadoopConfiguration());
	            Path path = new Path(sc.conf.get(filepath));

	            if (!hdfs.exists(path)) {
	                 //Path does not exist.
	            	System.out.println("NOT EXISTS");
	            } 
	         else{
	        	 System.out.println("EXISTS");
	               //Path exist.
	           }
		
	return null;	
	}

}
