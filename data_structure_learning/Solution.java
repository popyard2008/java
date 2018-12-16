import java.util.*;
public class Solution {
	private static class Person{
		String name;
		int age;
		public Person(String name, int age){
			this.name = name;
			this.age = age;
		}
	}

   public static void main(String[] args) {
		Person[] p1 = new Person[1];
		Person[] p2 = new Person[1];
		p1[0] = new Person("Tom", 13);
		p2 = Arrays.copyOf(p1,1);

		System.out.println(p1[0].name);
		System.out.println(p2[2].name);

		p1[0].name = "Mary";
		System.out.println(p1[0].name);
		System.out.println(p2[0].name);
	}

}