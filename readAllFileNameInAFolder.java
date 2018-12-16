import java.io.File;
import java.util.*;

public class readAllFileNameInAFolder {

  public static void main(String[] args)
  {
      File folder = new File("/Users/c03017/Downloads/Testing License Files");

      String[] files = folder.list();

      for (String file : files) 
      {
          System.out.println(file);
      }
  }
}
