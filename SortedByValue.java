import java.util.*;
import java.util.stream.Collectors;
//https://www.concretepage.com/java/jdk-8/java-8-stream-sorted-example
public class SortedByValue {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        // ValueComparator bvc = new ValueComparator(map);
        // TreeMap<String, Double> sorted_map = new TreeMap<String, Double>(bvc);

        map.put("A", 99);
        map.put("B", 67);
        map.put("C", 63);
        map.put("D", 62);

        System.out.println("unsorted map: " + map);




        // sorted_map.putAll(map);
        // System.out.println("results: " + sorted_map);

      // Create a hash map
        HashMap hm = new HashMap();

      // Put elements to the map
        hm.put("Zara", new Double(3434.34));
        hm.put("Mahnaz", new Double(123.22));
        hm.put("Ayan", new Double(1378.00));
        hm.put("Daisy", new Double(99.22));
        hm.put("Qadir", new Double(-19.08));

      // Get a set of the entries
        Set set = hm.entrySet();

      // Get an iterator
        Iterator i = set.iterator();
        System.out.println("unsorted map: " + set);

      // Display elements 
        while(i.hasNext()) {
           Map.Entry me = (Map.Entry)i.next();
           System.out.print(me.getKey() + ": ");
           System.out.println(me.getValue());
       }


   System.out.println();

      // Deposit 1000 into Zara's account
   double balance = ((Double)hm.get("Zara")).doubleValue();
   hm.put("Zara", new Double(balance + 1000));
   System.out.println("Zara's new balance: " + hm.get("Zara"));

   System.out.println("unsorted map: " + set);

   int [] input = {1, 2, 1, 3, 3, 4, 5, 6,6,6,};

   System.out.println("top 2 frequency " + topKFrequent(input, 1));


        List<Student> list = new ArrayList<Student>();
        list.add(new Student(1, "Mahesh", 12));
        list.add(new Student(2, "Suresh", 15));
        list.add(new Student(3, "Nilesh", 10));
        
        System.out.println("---Natural Sorting by Name---");
        List<Student> slist = list.stream().sorted().collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));
        
        System.out.println("---Natural Sorting by Name in reverse order---");
        slist = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));        
        
        System.out.println("---Sorting using Comparator by Age---");
        slist = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));
        
        System.out.println("---Sorting using Comparator by Age with reverse order---");
        slist = list.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));


// SORTED BY MAP:

     // Map<Integer, String> unsortedMap = new HashMap<>();
     //    unsortedMap.put(15, "Mahesh");
     //    unsortedMap.put(10, "Suresh");
     //    unsortedMap.put(30, "Nilesh");
     Map<String, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put("Mahesh", 25);
        unsortedMap.put("Suresh", 10);
        unsortedMap.put("John", 100);
        unsortedMap.put("Nilesh", 30);

        System.out.println("---Sort by Map Value---");
            unsortedMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
              .forEach(e -> System.out.println("Key: "+ e.getKey() +", Value: "+ e.getValue()));

        System.out.println("---Sort by Map Key---");
            unsortedMap.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
              .forEach(e -> System.out.println("Key: "+ e.getKey() +", Value: "+ e.getValue()));

        // default Comparator<T> ;

        System.out.println("---Sort by Map Value---");
            Map<String, Integer> sortedMap = 
            unsortedMap.entrySet().stream().sorted((Comparator.comparing(Map.Entry::getValue)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                              (e1, e2) -> e1, LinkedHashMap::new));

                System.out.println("---Sort by Map Value---" + sortedMap );


/*
    Map<String, Integer> sortedMap = 
     map.entrySet().stream()
    .sorted(Map.Entry.comparingByValue())
    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                              (e1, e2) -> e1, LinkedHashMap::new));    

   System.out.println("sorted map: " + sortedMap); ///NOT WORK

*/
}
   public static List<Integer> topKFrequent(int[] nums, int k) {


    List<Integer>[] bucket = new List[nums.length + 1];
    Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

    for (int n : nums) {
        frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
    }

    for (int key : frequencyMap.keySet()) {
        int frequency = frequencyMap.get(key);
        if (bucket[frequency] == null) {
            bucket[frequency] = new ArrayList<>();
        }
        bucket[frequency].add(key);
    }

    List<Integer> res = new ArrayList<>();

    for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
        if (bucket[pos] != null) {
            res.addAll(bucket[pos]);
        }
    }
    return res;
}


public static class Student implements Comparable<Student> {
    private int id;
    private String name;
    private int age;
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    @Override
    public int compareTo(Student ob) {
        return name.compareTo(ob.getName());
    }
        @Override
        public boolean equals(final Object obj) {
          if (obj == null) {
             return false;
          }
          final Student std = (Student) obj;
          if (this == std) {
             return true;
          } else {
             return (this.name.equals(std.name) && (this.age == std.age));
          }
        }
        @Override
        public int hashCode() {
          int hashno = 7;
          hashno = 13 * hashno + (name == null ? 0 : name.hashCode());
          return hashno;
        }   
} 





/*
class ValueComparator implements Comparator<String> {
    Map<String, Double> base;

    public ValueComparator(Map<String, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
*/



}