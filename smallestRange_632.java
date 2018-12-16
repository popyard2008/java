import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

class smallestRange_632
{
		// Driver code
	public static void main (String[] args)
	{

		List<Integer> list1 = new ArrayList<>(Arrays.asList(0,9,12,20));
		List<Integer> list2 = new ArrayList<>(Arrays.asList(4,10,15,24,26));
		List<Integer> list3 = new ArrayList<>(Arrays.asList(18,22,30, 45));

		// HERE IS how to construct a list of list
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		// list.add(list3);
		System.out.println("size " + list.size());
		System.out.println(list.get(2).get(1));
		int[] res =  smallestRange(list);
		int[] res2 =  smallestRange_method2(list);
     System.out.println("degree of Array is " + res[0] + " and " + res[1]);
		 System.out.println("degree of Array is " + res2[0] + " and " + res2[1]);



		List<Integer> w = new ArrayList<Integer>();
      List<List<Integer>> a = new ArrayList<List<Integer>>();//Use Arraylist inside
      for(int i=1;i<10; i++){
          w.add(i);
          a.add(new ArrayList(w));
      }

      System.out.println(w);
      System.out.println(a);
		// System.out.println("degree of Array is " + smallestRange(a));
	}

	public static int[] smallestRange(List<List<Integer>> a)
	{
			PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> a.get(o[0]).get(o[1])));
			int max = Integer.MIN_VALUE, start = 0, end = Integer.MAX_VALUE;

			for (int i = 0; i < a.size(); i++) {
					q.offer(new int[]{i, 0});
					max = Math.max(max, a.get(i).get(0));
			}
			while (q.size() == a.size()) {
				int e[] = q.poll(), row = e[0], col = e[1]; //poll the smallest number
				if (end -start > max - a.get(row).get(col)){
					//the range from last run is larger than current run
					start = a.get(row).get(col);
					end = max;
					System.out.println("start = " + start + " ; end = " + end + " max1 = " + max + " ; row = " + row + " ; col = " + col);

				}
				if (col + 1 < a.get(row).size()) {
					q.offer(new int[]{row, col+1});
					//insert the next value in the same list
					max = Math.max(max, a.get(row).get(col + 1));
					//only compare this one to the max one to get all the max
					System.out.println("max2 = " + max );
				}
				System.out.println("q.size() = " + q.size() );

			}
			return new int[]{start, end};

	}

	public static int[] smallestRange_method2(List<List<Integer>> nums) {
	        List<int[]> list = IntStream.range(0, nums.size())
	                .mapToObj( i -> nums.get(i).stream().map(x -> new int[]{x, i}))
	                .flatMap(y -> y)
	                .sorted(Comparator.comparingInt(p -> p[0])).collect(toList());
									System.out.println("list = " + list );

	        int[] counts = new int[nums.size()];

	        BitSet set = new BitSet(nums.size());

	        int start = -1;
	        int[] res = new int[2];
	        for(int i = 0; i < list.size(); i++) {
	            int[] p = list.get(i);
	            set.set(p[1]);
	            counts[p[1]] += 1;

							if(start == -1) { start = 0; }
	            while(start < i && counts[list.get(start)[1]] > 1) {
	                counts[list.get(start)[1]]--;
	                start++;
	            }

	            if(set.cardinality() == nums.size()) {
	                if( (res[0] == 0 && res[1] == 0) || (list.get(i)[0] - list.get(start)[0]) < res[1] - res[0]) {
	                    res[0] = list.get(start)[0];
	                    res[1] = list.get(i)[0];
	                }
	            }
	        }
	        return res;
	    }
}
