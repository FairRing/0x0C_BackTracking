import java.io.*;
/* Focus * * * * * * * 
 * i) 전체 순회 종료 후 공집합체크.
 * ii) 현재 값을 포함해서 합산하는 case
 * iii) 현재 값을 포함 안 하고 합산하는 case
 */
public class Main {
    static int N, S, COUNT = 0;
    static int[] elements;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[];
        input = reader.readLine().split(" "); // input line 1
        N = Integer.parseInt(input[0]);
        S = Integer.parseInt(input[1]);
        elements = new int[N];
        input = reader.readLine().split(" "); // input line 2
        for (int i = 0; i < input.length; i++) elements[i] = Integer.parseInt(input[i]);

        backTracking(0, 0);
        isEmptySet(); // BASE CONDITION :: 공집합 체크 :: S(부분수열들의 합)가 0일땐, 공집합도 포함됨... tlqkf
        System.out.println(COUNT);
    }

    private static void isEmptySet() {
        if(S==0) COUNT--; // i)
    }

    private static void backTracking(int sum, int location) {
        if(location >= elements.length) { // BASE CONDITION
            if(S==sum) COUNT ++; // 부분수열이 없을때의 합?(=0)도 카운팅됨.. 전체 순회 종료후 확인
            return;
        }
        backTracking(sum+elements[location], location+1); // ii)
        backTracking(sum, location+1); // iii)
    }
}
