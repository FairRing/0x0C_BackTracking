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

        backTracking(0);

        System.out.println(builder);
    }

    private static void backTracking(int location) {
        if (location == M) { 
            for (int i = 0; i < M; i++) {
                builder.append(map[i]).append(' ');
            }
            builder.append('\n');
            return;
        } // base condition
        
        for (int i = 1; i <= N; i++) {
            map[location] = i;
            backTracking(location+1);
        }
    } 
}
