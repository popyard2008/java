import java.util.*;

public class array {
	private static class Person {
		String name;
		int age;
		public Person(String name, int age){
			this.name = name;
			this.age = age;
		}
	}

	public static void main (String[] args) {
		Person[] p1 = new Person[1];
		p1[0] = new Person("tom", 14);
		Person[] p2 = new Person[1];
		p2 = Arrays.copyOf(p1,1);

		System.out.println(p1[0].name);
		System.out.println(p2[0].name);

		p1[0].name = "mary";

		System.out.println(p1[0].name);
		System.out.println(p2[0].name);


	}




}