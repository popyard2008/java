import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.lang.*;
public class SleepMessages {
    // public static void main(String args[])
    //     throws InterruptedException {
    //     String importantInfo[] = {
    //         "Mares eat oats",
    //         "Does eat oats",
    //         "Little lambs eat ivy",
    //         "A kid will eat ivy too"
    //     };

    //     for (int i = 0;
    //          i < importantInfo.length;
    //          i++) {
    //         //Pause for 4 seconds
    //         Thread.sleep(4000);
    //         //Print a message
    //         System.out.println(importantInfo[i]);
    //     }
    // }

    public static void main(String args[])
                final long start = System.currentTimeMillis();  //START TIME

        throws InterruptedException {
        String importantInfo = "sleeping";
        while (true) {
            //Pause for 4 seconds
            Thread.sleep(20);
            //Print a message
            System.out.println(importantInfo);
            break;
        }
    }



}