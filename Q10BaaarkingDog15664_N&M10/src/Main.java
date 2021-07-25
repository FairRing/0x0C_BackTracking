import java.io.*;
import java.util.*;

// reference about Java SET :http://tcpschool.com/java/java_collectionFramework_set

public class Main {

    static int N, M;
    static int[] elements, isVisited = new int[10001];
    static ArrayList<Integer> result;
    static BufferedReader reader;
    static BufferedWriter writer;

    public static void main(String[] args) throws IOException {
        
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String input[];

        input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        elements = new int[N];
        
        input = reader.readLine().split(" ");
        Set<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(input[i]);
            set.add(temp);
            isVisited[temp]++;
        }
        result = new ArrayList<Integer>(set);
        
        backTracking(0, result.get(0), "");
        writer.flush();
        writer.close();
        reader.close();
    }

    private static void backTracking(int location, int maxValue, String str) {

        if (location == M) {
            try {
                writer.write(String.valueOf(str + "\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } // BASE CONDITION

        for (int i = 0; i < result.size(); i++) {
            if (isVisited[result.get(i)] > 0 && maxValue <= result.get(i)) {
                isVisited[result.get(i)] --;
                backTracking(location+1, result.get(i), str+result.get(i)+" ");
                isVisited[result.get(i)] ++;                
            }
        }
    } // BACKTRACKING() END.
}