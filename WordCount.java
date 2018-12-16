/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class WordCount {

  public static class TokenizerMapper 
       extends Mapper<Object, Text, Text, IntWritable>{
    
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
  	  String cleanLine = value.toString().toLowerCase().replaceAll("[_|$#<>\\^=\\[\\]\\*/\\\\,;,.\\-:()?!\"]", " ");
      StringTokenizer itr = new StringTokenizer(cleanLine);
      while (itr.hasMoreTokens()) {
      word.set(itr.nextToken().trim());
      context.write(word, one);
      }
    }
  }

  public static class Top100Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {

      private HashMap<Text, IntWritable> countMap = new HashMap<>();

      @Override
      public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

          int sum = 0;
          for (IntWritable val : values) {
              sum -= val.get();
          }

          countMap.put(new Text(key), new IntWritable(sum));
      }

      @Override
      protected void cleanup(Context context) throws IOException, InterruptedException {

          Map<Text, IntWritable> sortedMap = countMap.entrySet().stream()
        		      .sorted((Comparator.comparing(Map.Entry::getValue)))
                  .collect(Collectors.toMap(Map.Entry::getKey,  Map.Entry::getValue,
                          (e1, e2) -> e1, LinkedHashMap::new));
 
    File file = new File("StopWords.txt");
    	Scanner input = new Scanner(file); 
    	 	
    	ArrayList<String> wordsArray = new ArrayList<>();
    	
    	while (input.hasNext()) {
  	      String word  = input.next();
  		wordsArray.add(word);	
//  		System.out.println("added word in stop words " + word + " ||size =  " + wordsArray.size() );
        }
  	
          int counter = 0;
          for (Text key : sortedMap.keySet()) {
              if (counter <= 100) {
              int k = 0;
            	  for (; k < wordsArray.size(); k++) {
            	  		if (wordsArray.get(k).trim().equals(key.toString()))  {
//        	  			System.out.println("k = " + k + "; wordArray = " + wordsArray.get(k).trim() + "; word = " + key.toString()
//        	  			+ " || boolen =" + ((wordsArray.get(k).trim().equals(key.toString()))));
            	  		break;}
            	  	  }
                 if (k == wordsArray.size()) {  context.write(key, sortedMap.get(key));
                    counter ++;
                    }
              }
              else break;	
          }
      }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
    if (otherArgs.length < 2) {
      System.err.println("Usage: wordcount <in> [<in>...] <out>");
      System.exit(2);
    }
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(Top100Reducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    for (int i = 0; i < otherArgs.length - 1; ++i) {
      FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
    }
    FileOutputFormat.setOutputPath(job,
      new Path(otherArgs[otherArgs.length - 1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
