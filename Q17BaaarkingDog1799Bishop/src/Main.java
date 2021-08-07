import java.io.*;

public class Main {
    static int N;
    static int[] result;
    static int[][] map;
    static boolean[][] isVisited, isBlack;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        result = new int[2];
        map = new int[N][N];
        isVisited = new boolean[N][N];
        isBlack = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String input[] = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                isBlack[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1);
            }
        }

        // 검은판과 흰판의 비숍은  "서로 만날수 없다."
        // true == balck, false == white
        DFS(0, true, 0);
        DFS(0, false, 0);
        System.out.println(result[0] + result[1]);
    }

    static void DFS(int index, boolean color, int count) {

        for (int i = index; i < N * N; i++) {
            int x = i / N;
            int y = i % N;

            if (map[x][y] == 0 || isBlack[x][y] != color || !check(x, y)) continue;

            isVisited[x][y] = true;
            DFS(i + 1, color, count + 1);
            isVisited[x][y] = false;
        }

        result[color ? 0 : 1] = Math.max(result[color ? 0 : 1], count);
    }

    static boolean check(int x, int y) {
        int[] aroundX = { -1, -1 }, aroundY = { -1, 1 };

        for (int i = 0; i < 2; i++) {
            int nx = x;
            int ny = y;
            while (true) {
                if (0 > nx || nx >= N || 0 > ny || ny >= N) break;
                if (isVisited[nx][ny]) return false;
                nx += aroundX[i];
                ny += aroundY[i];
            }
        }
        return true;
    }
}