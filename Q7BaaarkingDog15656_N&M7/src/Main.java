import java.io.*;
import java.util.Arrays;

public class Main {

    static int N, M;
    static int[] elements, result;
    static BufferedWriter writer; // sysout 시, 시간초과ㅠ
    public static void main(String[] args) throws IOException {
        // SECTION init N, M
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        // SECTION  init array :: elements, result
        elements = new int[N];
        result = new int[N];
        // SECTION  set elements
        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) elements[i] = Integer.parseInt(input[i]);
        Arrays.sort(elements);
        // SECTION start backTracking
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        backTracking(0);
        writer.flush();
        writer.close();
        reader.close();
    }

    private static void backTracking(int location) {
        if (location == M) { // NOTE base condition
            try {
                for (int i = 0; i < M; i++) writer.write(String.valueOf(result[i]+" "));
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        for (int i = 0; i < elements.length; i++) {
                result[location] = elements[i];
                backTracking(location + 1);
        }
    } // end of backTracking.
}