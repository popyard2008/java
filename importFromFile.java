import java.io.*;

public class importFromFile {


 public static void main(String args[]) throws IOException {
      FileReader in = null;
      FileWriter out = null;

    String Sample = " he saw a cat  running of that pat's mat ";
     


      try {
         in = new FileReader("/Users/gary.yang/Documents/MyFiles/BitTiger-CS502-1802/week1/wordcount/StopWords.txt");
         in2 = new FileReader("/Users/gary.yang/Documents/MyFiles/BitTiger-CS502-1802/week1/wordcount/4300-0.txt")
         out = new FileWriter("/Users/gary.yang/Documents/MyFiles//StopWordsOutPut.txt");
         
         int c;
         while ((c = in.read()) != -1) {
            // out.write(c);
            System.out.println (c);
         }
      }finally {
         if (in != null) {
            in.close();
         }
         if (out != null) {
            out.close();
         }
      }
   }

/*

    File file = new File("/Users/gary.yang/Documents/MyFiles/BitTiger-CS502-1802/week1/wordcount/StopWords.txt");

    LineIterator lineIterator = null;

    try
    {
        lineIterator = FileUtils.lineIterator(file);
        while(lineIterator.hasNext())
        {
            String line = lineIterator.next();
        // Process line
        }
    }
    catch (IOException e)
    {
    // Handle exception
    }
    finally
    {
        LineIterator.closeQuietly(lineIterator);
    }

*/
}

