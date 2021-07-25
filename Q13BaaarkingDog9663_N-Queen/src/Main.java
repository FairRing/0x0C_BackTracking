import java.io.*;

public class Main {
    static int N, COUNT;
    static int[] Y; // 체스 세로
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        Y = new int[N];
        COUNT = 0;

        backTracking(0);
		System.out.println(COUNT);
        reader.close();
    }

    private static void backTracking(int l) {
        if (l==N){
            COUNT++;
            return;
        } // BASE CONDITION

        for (int i = 0; i < N; i++) {
            Y[l] = i;
            if (isPossible(l)) backTracking(l+1); // add new queen
        }
    } // end backtraking()

    private static boolean isPossible(int l) {
 
		for (int i = 0; i < l; i++) {
			if (Y[l] == Y[i]) return false; // X축 비교 위치 비교
			if (Math.abs(l-i) == Math.abs(Y[l]-Y[i])) return false; // 대각축 비교(-, + 둘다 비교) <<< 절대값 비교
		}
		return true;
	} // end isPossible()
}
