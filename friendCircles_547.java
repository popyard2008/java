import java.util.*;

class friendCircles_547
{
		// Driver code
	public static void main (String[] args)
	{
		int[][] nums = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
		System.out.println(findCircleNum(nums));
	}

	public static int findCircleNum(int[][] M){
		int[] visited = new int[M.length];
		int count = 0;
		for (int i = 0; i < M.length; i++){
			System.out.println("from the main: visited[i =" + i + "] = " + visited[i]);
				if (visited[i] == 0) {
					System.out.println("    from the main in cycle: visited[ i= " + i + "] = 0");
						dfs(M, visited, i);
						count++;
				}
		}
		return count;
	}

		public static void dfs(int[][] M, int[] visited, int i){
			System.out.println("---------one new cylce ----------");
			for (int j = i; j < M.length; j++){
				System.out.println("            sub routine: visited[j =" + j + "] = " + visited[j] + " ; M[" + i + "][" + j + "] = " + M[i][j]);
				  if (M[i][j] == 1 && visited[j] == 0) {
						visited[j] = 1;
						System.out.println("               visited[j =" + j + "] = 0 -> 1");
						dfs(M, visited, j);
					}
				}
			}
}
