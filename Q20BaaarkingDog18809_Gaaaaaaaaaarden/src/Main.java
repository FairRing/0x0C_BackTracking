import java.io.*;
import java.util.*;

public class Main {
	private static class Point {
		int x, y, c; // c :: 0 = green, 1 = red

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Point(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

	static int R, C;
	static int countGreen, countRed;
	static int count, result = 0;
	static int GREEN = 2, RED = 3;
	static boolean[][] map;
	static boolean[][][] visit;
	static int[][][] time;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
	static int[] pickRed, pickGreen;
	static boolean[] pick;
	static Queue<Point> queue = new LinkedList<Point>();
	static ArrayList<Point> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(reader.readLine());

		R = stringToInt(token.nextToken());
		C = stringToInt(token.nextToken());
		countGreen = stringToInt(token.nextToken());
		countRed = stringToInt(token.nextToken());

		map = new boolean[R][C];
		visit = new boolean[R][C][2];
		pickGreen = new int[countGreen];
		pickRed = new int[countRed];
		time = new int[R][C][2];

		for (int i = 0; i < R; i++) {
			token = new StringTokenizer(reader.readLine());
			for (int j = 0; j < C; j++) {
				switch (stringToInt(token.nextToken())) {
					case 0:
						map[i][j] = true;
						break;
					case 2:
						list.add(new Point(i, j));
						break;
				}
			}
		}
		pick = new boolean[list.size()];
		permutation(countGreen, 0, false);
		System.out.println(result);
	}

	private static void permutation(int depth, int start, boolean isRed) {
		if (depth == 0) {
			// (1)위치 예상 :: 1. Green, 2. Red
			if (!isRed) { 
				permutation(countRed, 0, true);
			} else { 
				// (2) BFS 시작
				init();
				int time = 1;
				while (!queue.isEmpty()) {
					simulation(time++);
				}
				result = Math.max(result, count >> 1); // count/2
			}
			return;
		}

		for (int i = start; i < list.size(); i++) {
			if (!pick[i]) {
				pick[i] = true;
				if (!isRed) {
					pickGreen[depth - 1] = i;
				} else {
					pickRed[depth - 1] = i;
				}
				permutation(depth - 1, i + 1, isRed);
				pick[i] = false;
			}
		}
	}

	private static void init() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				visit[i][j][0] = visit[i][j][1] = map[i][j] ? true : false;
				time[i][j][0] = time[i][j][1] = 0;
			}
		}
		Point n;
		for (int i = 0; i < countGreen; i++) {
			n = list.get(pickGreen[i]);
			queue.add(new Point(n.x, n.y, 0));
			visit[n.x][n.y][0] = true;
		}
		for (int i = 0; i < countRed; i++) {
			n = list.get(pickRed[i]);
			queue.add(new Point(n.x, n.y, 1));
			visit[n.x][n.y][1] = true;
		}
		count = 0;
	}

	private static void simulation(int T) {
		int size = queue.size();
		for (int s = 0; s < size; s++) {
			Point n = queue.poll();
			if (time[n.x][n.y][0] != 0 && time[n.x][n.y][0] == time[n.x][n.y][1]) { // 동시 확산 jump
				count++;
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];

				if (nx >= 0 && ny >= 0 && nx < R && ny < C && !visit[nx][ny][n.c]) {
					visit[nx][ny][n.c] = true;
					time[nx][ny][n.c] = T;
					queue.add(new Point(nx, ny, n.c));
				}
			}
		}
	}

	private static int stringToInt(String input) {
		return Integer.parseInt(input);
	}
}