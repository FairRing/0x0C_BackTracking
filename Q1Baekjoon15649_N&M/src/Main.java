import java.io.*;

public class Main {
	static int[] set;
	static boolean[] visited;
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
		int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]);
 
		set = new int[M];
		visited = new boolean[N];
		backTracking(N, M, 0);
	}
 
	public static void backTracking(int N, int M, int depth) {
		if (depth == M) {
			for (int e : set) System.out.print(e + " ");
			System.out.println();
			return;
		}
 
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				set[depth] = 1+i;
				backTracking(N, M, depth + 1);
				visited[i] = false;
			}
		}
	}
}