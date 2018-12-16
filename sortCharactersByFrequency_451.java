import java.util.*;

class sortCharactersByFrequency_451
{
		// Driver code
	public static void main (String[] args)
	{
		String S = "abcdeeee";
		System.out.println("#1 sorted results are " + frequencySort(S));
		System.out.println("#2 sorted results are " + frequencySort2(S));
		System.out.println("$3 sorted results are " + frequencySort3(S));
		System.out.println("#4 sorted results are " + frequencySort4(S));
	}

//method 2:
	public static String frequencySort(String s)
	{
		if (s == null) {
        return null;
    }
    Map<Character, Integer> map = new HashMap();
    char[] charArray = s.toCharArray();
    int max = 0;
    for (Character c : charArray) {
        if (!map.containsKey(c)) {
            map.put(c, 0);
        }
        map.put(c, map.get(c) + 1);
        max = Math.max(max, map.get(c));
    }

    List<Character>[] array = buildArray(map, max);

    return buildString(array);
}

private static List<Character>[] buildArray(Map<Character, Integer> map, int maxCount) {
    List<Character>[] array = new List[maxCount + 1];//construct a list with length of maxCount+1, even  at some counts no char exsits, still get a null
    for (Character c : map.keySet()) {
        int count = map.get(c);//index of the elements is equal to the frequency.
				System.out.println("from buildArray: array[count] = " + array[count]);

        if (array[count] == null) {
            array[count] = new ArrayList();
        }
				System.out.println("from buildArray: c = " + c + "; count = " + count + "; max = " + maxCount);

        array[count].add(c);
    }
		System.out.println("from buildArray: " + Arrays.toString(array));
    return array; //[null, [a, b, c, d], null, [e]]
}

private static String buildString(List<Character>[] array) {
    StringBuilder sb = new StringBuilder();//StringBuilder: build string
    for (int i = array.length - 1; i > 0; i--) {
        List<Character> list = array[i];
				System.out.println("from buildString: i = " + i + "; + list= "  + list);

        if (list != null) {
            for (Character c : list) {
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }
    }
    return sb.toString();
}
//methoad 2:
//The logic is very similar to NO.347 and here we just use a map a count and according to
//the frequency to put it into the right bucket. Then we go through the
//bucket to get the most frequently character and append that to the final stringbuilder.
public static String frequencySort2(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray())
				map.put(c, map.getOrDefault(c, 0) + 1);

		List<Character> [] bucket = new List[s.length() + 1];
		for (char key : map.keySet()) {
				int frequency = map.get(key);
				if (bucket[frequency] == null) bucket[frequency] = new ArrayList<>();
				bucket[frequency].add(key);
		}

		StringBuilder sb = new StringBuilder();
		for (int pos = bucket.length - 1; pos >= 0; pos--)
				if (bucket[pos] != null)
						for (char c : bucket[pos])
								for (int i = 0; i < map.get(c); i++)
										sb.append(c);

		return sb.toString();
}
//method 3:
//And we have normal way using PriorityQueue as follows:
//according to user "orxanb", O(nlogm), m is the distinguish character,
//can be O(1) since only 26 letters. So the overall time complexity should be O(n),
//the same as the buck sort with less memory use.
public static String frequencySort3(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray())
				map.put(c, map.getOrDefault(c, 0) + 1);

		PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
		pq.addAll(map.entrySet());
System.out.println("pq = "  + pq);
		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
				Map.Entry e = pq.poll();
				System.out.println("e = "  + e + "; key: " + e.getKey() + " ; value: " + e.getValue());
				for (int i = 0; i < (int)e.getValue(); i++)
						sb.append(e.getKey());
		}
		return sb.toString();
}

//method 4:
/*
Could not find a simpler way to do this.
I see people are using HashMap/TreeMap which
 are not at all required. If you know bucket sort
 then following solution will be easy to understand!

*/

public static String frequencySort4(String s) {
        if(s.length() < 3)
            return s;
        int max = 0;
        int[] map = new int[256];
        for(char ch : s.toCharArray()) {
            map[ch]++;
            max = Math.max(max,map[ch]);
						System.out.println("map[ch " + ch + " ] = " + map[ch]);

        }


        String[] buckets = new String[max + 1]; // create max buckets
        for(int i = 0 ; i < 256; i++) { // join chars in the same bucket
            String str = buckets[map[i]];
            if(map[i] > 0)
                buckets[map[i]] = (str == null) ? "" + (char)i : (str + (char) i);
        }
        StringBuilder strb = new StringBuilder();
        for(int i = max; i >= 0; i--) { // create string for each bucket.
            if(buckets[i] != null)
                for(char ch : buckets[i].toCharArray())
                    for(int j = 0; j < i; j++)
                        strb.append(ch);
        }
        return strb.toString();
    }
}
