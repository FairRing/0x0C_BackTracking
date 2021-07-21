import java.io.*;
import java.util.Arrays;

public class Main {

    static int N, M;
    static int[] elements, output;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        // init from console input...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        // set arrays length...
        elements = new int[N];
        isVisited = new boolean[N];
        output = new int[N];
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) elements[i] = Integer.parseInt(input[i]);
        Arrays.sort(elements);
        // start backTracking...
        backTracking(0);
    }

    private static void backTracking(int location) {
        if (location == M) { // base condition
            for (int i = 0; i < M; i++) System.out.print(output[i] + " ");
            System.out.println();
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                output[location] = elements[i];
                backTracking(location + 1);
                isVisited[i] = false;
            }
        }
    } // end of backTracking.
}
