import java.io.*;

public class Main {
 
	static int N, M;
	static int[] map;
	static StringBuilder builder = new StringBuilder();
 
	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input[] = reader.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new int[M];
 
		backTracking(1, 0);

		System.out.println(builder);
	}
 
	public static void backTracking(int target, int location) {
 
		if (location == M) {
			for (int val : map) builder.append(val).append(' ');
			builder.append('\n');
			return;
		}
		for (int i = target; i <= N; i++) {
			map[location] = i;
			backTracking(i, location+1);
		}
	}
}