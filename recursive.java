public class recursive {

public static void main(String[] args) {

int j = minusOne(-100);

System.out.println("result is " + j);

}

public static int minusOne (int input) {
		int i = input;
		if (i <= 0) return i;
		--i;
		return minusOne (i);
}
}