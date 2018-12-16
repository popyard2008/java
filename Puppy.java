public class Puppy {
	 int puppyAge;
	 int puppyName;

	 public Puppy (String name){
	 	System.out.println("name chosen is " + name);
	 }
	 public void setAge (int age) {
	 	puppyAge = age;
	 }

	 public int getAge() {
	 	System.out.println("Puppy's age is : " + puppyAge);
	 	return puppyAge;
	 }

	 public static void main(String[] args) {
	 		Puppy myPuppy = new Puppy("tommy");
	 		myPuppy.setAge(2);
	 		System.out.println("myPuppy's age is " + myPuppy.getAge());
	 		Puppy myPuppy2 = new Puppy("heihu");
	 		myPuppy2.setAge(3);
	 		System.out.println("myPuppy's age is " + myPuppy2.getAge());
	 }
}

/*
	  int puppyAge;
	  int puppyName;
	  public Puppy(String name) {
	  		// This Constructor has one parameter: name.
	  	System.out.println("Name chosen is :" + name );
	  }

	  	public void setAge (int age) {
	  		puppyAge = age;
	  	}

	  	public int getAge() {
	  		System.out.println("Puppy's age is :" + puppyAge);
	  		return puppyAge;
	  	}

	  	public static void main(String[] args) {
	  		//Object Creation
	  		Puppy myPuppy = new Puppy("tommy");
	  		//call class method to set puppy's age
	  		myPuppy.setAge(2);
	  		myPuppy.getAge();

	  		Puppy myPuppy2 = new Puppy("heihu");
	  		myPuppy2.setAge(5);
	  		myPuppy2.getAge();
	  		//call another class method to get puppy's age

	  		//you can ccess instance variable as folloers as we;;
	  		System.out.println("variable Value :" + myPuppy.puppyAge);
	  		System.out.println("variable Value :" + myPuppy2.puppyAge);
*/

// public class Puppy {
//    int puppyAge;

//    public Puppy(String name) {
//       // This constructor has one parameter, name.
//       System.out.println("Name chosen is :" + name );
//    }

//    public void setAge( int age ) {
//       puppyAge = age;
//    }

//    public int getAge( ) {
//       System.out.println("Puppy's age is :" + puppyAge );
//       return puppyAge;
//    }

//    public static void main(String []args) {
//       /* Object creation */
//       Puppy myPuppy = new Puppy( "tommy" );

//       /* Call class method to set puppy's age */
//       myPuppy.setAge( 2 );

//       /* Call another class method to get puppy's age */
//       myPuppy.getAge( );

//       /* You can access instance variable as follows as well */
//       System.out.println("Variable Value :" + myPuppy.puppyAge );
//    }
// }

// public class Puppy {
//    public Puppy(String name) {
//       // This constructor has one parameter, name.
//       System.out.println("Passed Name is :" + name );
//    }

//    public static void main(String []args) {
//       // Following statement would create an object myPuppy
//       Puppy myPuppy = new Puppy( "tommy" );
//    }
// }

