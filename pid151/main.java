package pid151;
/**
 *
 */
public class main {
	public static void main(String[] args) {
		String input = "This Is Again A Test String  ";

			test(input);

	}
	private static void test(String input) {
		Solution solution = new Solution();
		String Output;
		long begin = System.currentTimeMillis();
		Output = solution.reverseWords(input);
		long end = System.currentTimeMillis();
		System.out.println("the results are: " + Output + "end");
		System.out.println();
		System.out.println("耗时：" + (end - begin) + "ms");
		System.out.println("-------------------");
    // System.out.println(true || (true && false));
    // System.out.println(false && true || true);
	}
}
