import java.io.*;

public class Main {

    static int k;
    static int[] S;
    static boolean[] cases;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[];

        while (true) {
            input = reader.readLine().split(" ");
            k = Integer.parseInt(input[0]);
            S = new int[k];
            cases = new boolean[k];

            if (k == 0) break; // LOOP END CONDITION.
                
            for (int i = 0; i < k; i++) S[i] = Integer.parseInt(input[i + 1]);
            backTracking(0, 0);
            System.out.println();
            
        } // scope while()
    }

    // S[]의 k개의 원소중, 6개만.
    private static void backTracking(int element, int location) {
        if (location == 6) { // BASE CONDITION.
            for (int i = 0; i < k; i++) if (cases[i] == true) System.out.print(S[i] + " ");
            System.out.println();
        } // end base condition.

        for (int i = element; i < k; i++) {
            cases[i] = true;
            backTracking(i + 1, location + 1);
            cases[i] = false;
        }
    }
}
