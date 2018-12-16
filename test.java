/*We are trying to find the frequency of books published each year. Our input data set (file books.txt) is a txt file located in HDFS input folder. 
The schema is (not included in books.txt): 
“ISBN”;”Book-Title”;”Book-Author”;”Year-Of-Publication”;”Publisher”;”Image-URL-S”;”I mage-URL-M”;”Image-URL-L” 
Some sample rows: 
“0195153448”;”Classical Mythology”;”Mark P. O. Morford”;”2002“;”Oxford University Press”;”http://images.amazon.com/images/P/0195153448.01.THUMBZZZ.jpg”; 
“http://images.amazon.com/images/P/0195153448.01.MZZZZZZZ.jpg”;”http://images.a mazon.com/images/P/0195153448.01.LZZZZZZZ.jpg” 
“0002005018”;”Clara Callan”;”Richard Bruce Wright”;”2001“;”HarperFlamingo Canada”;”http://images.amazon.com/images/P/0002005018.01.THUMBZZZ.jpg”; 
“http://images.amazon.com/images/P/0002005018.01.MZZZZZZZ.jpg”;”http://images.a mazon.com/images/P/0002005018.01.LZZZZZZZ.jpg” 
... 
Our objective is to find the frequency of books published each year. Here is a version of MapReduce code. 
*/

public static class BookMapper 
extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>{ 
private final static IntWritable one = new IntWritable(1); 
@Override 
public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter 
) throws IOException { String TempString = value.toString(); String[] SingleBookData = TempString.split("\";\""); output.collect(new Text(SingleBookData[3]), one); 
} } 
public static class BookReducer  extends MapReduceBase implements Reducer<Text,IntWritable,Text,IntWritable> { 
@Override 
public void reduce(Text key, Iterable<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter ) throws IOException { 
int frequencyForYear = 0; while (values.hasNext()) { 
IntWritable value = (IntWritable) values.next(); 
frequencyForYear += value.get(); } 
output.collect(key, new IntWritable(frequencyForYear)); } 
} 
/*Questions: 
* 1)  Explain the functionality of BookMapper.  
	split the data by ";" and get the year at position 4 and assign each record to 1
* 2)  Explain the functionality of BookReducer.  
	  do aggredtion by year; add total for each year
* 3)  Could you use Pig to implement the same functionality? Write your code.  

A = LOAD 'filename' USING PIGSTORAGE(';') ;
B = FOREACH A GENERATE $3 AS frequencyForYear ;
C = group B by frequencyForYear;
E = foreach C generate group, COUNT(c) as count;



* 4)  Could you use Hive to implement the same functionality? Write your code.  

CREATE TABLE books (ISBN string
					, Book_Title string
					,  Book_Author string
					, Year_Of_Publication string
					, Publisher string
					, Image_URL_S string
					, Image_URL_M string
					, Image_URL_L string
					)
) row format delimited fields terminated by ';' stored as textfile;

LOAD DATA INPATH 'filename' OVERWRITE INTO TABLE books;

SELECT Year_Of_Publication
 	, count(*)
 	FROM books
 	ORDER BY Year_Of_Publication




8. (20’) 
Background: 
In HDFS, there is a document with the structured data. The document contains a total of three columns, the first is the id, the second is gender information (F -> female, M -> male), and the third is the height of the population, in cm. Now, we want to use Spark SQL to analyze the data. 
The sample is like following: 
1 F 160 2 M 180 3 M 172 4 F 171 
5 F 162 
*/

object PeopleData { 
	private val schemaString = "id,gender,height" 
	def main(args: Array[String]) { 
val conf = new SparkConf().setAppName("Spark Exercise:People Data Statistics") 
val sc = new SparkContext(conf) 
val peopleDataRDD = sc.textFile(args(0)) 
val sqlCtx = new SQLContext(sc) 
import sqlCtx.implicits._ 
val schemaArray = schemaString.split(",") 

val schema = StructType(schemaArray.map( 
fieldName => StructField(fieldName, StringType, true))) 

val rowRDD: RDD[Row] = peopleDataRDD.map(_.split("\t")).map( 
eachRow => Row(eachRow(0), eachRow(1), eachRow(2))) 

val dfWithSchema = spark.createDataFrame(rowRDD).toDF("id", "gender", "height")


val peopleDF = sqlCtx.createDataFrame(rowRDD, schema) 
//Explain the function of the following code. 

peopleDF.filter(peopleDF("gender").equalTo("F")).filter(peopleDF("height") > 180).show(50) 
//Grouped the people by gender and count the number 
val genderCount = peopleDF.groupBy("gender").count()
genderCount.show()
//Calculate the maximum height of females. 
val maximumHeightFemale = peopleDF.filter(peopleDF("gender").equalTo("F"))
      .agg(max("height"))
//Calculate the average height of males.
val avgHeightMale = peopleDF.filter(peopleDF("gender").equalTo("M"))
      .agg(avg("height"))
 
￼
 
} } 
/*
Questions: 
* 1)  Explain the function of the underlined code.  
* 2)  Write your code to implement grouping the people by gender and count the  number.  
* 3)  Write your code to calculate the maximum height of females.  
* 4)  Write your code to calculate the average height of males.  
* 5)  If you don’t use StructType, how do you transform the peopleDataRDD to  DataFrame? Write your code.  
*/
