import java.io.*;

public class Main {
 
    public static int N, M;
	public static int[] set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
		N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

		set = new int[M];
        
		backTracking(1, 0);
	}
 
	public static void backTracking(int at, int depth) {
		if (depth == M) {
			for (int val : set) System.out.print(val + " ");
			System.out.println();
			return;
		}
        
		for (int i = at; i <= N; i++) {
			set[depth] = i;
			backTracking(1+i, 1+depth);
		}
	}
}