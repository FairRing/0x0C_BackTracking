import java.io.*;
import java.util.*;

public class Main {
    static class Point{
        int y, x;
        Point(int y, int x){
            this.y = y; this.x = x;
        }
    }

    static char[][] seat = new char[5][5];
    static Map<Integer, Point> map = new HashMap<>();                 
    static List<Integer> list = new ArrayList<>();
    static int[] dy = {1, -1, 0 ,0}, dx = {0, 0, 1, -1};
    static int count, S, Y;

    public static void main(String[] args) throws Exception {
        input();
        comb(0);
        System.out.println(count);
    }

    public static void comb(int index) {
        if(Y==4) return;

        if(list.size()==7) {

            count = (bfs()==true ? count+1 : count);
            return;
        }

        for(int i = index; i < 25; i++) {
            Point next = map.get(i);


            S = seat[next.y][next.x]=='S' ? S+1 : S;
            Y = seat[next.y][next.x]=='Y' ? Y+1 : Y;
            list.add(i);

            comb(i+1);


            list.remove((Integer)i);
            S = seat[next.y][next.x]=='S' ? S-1 : S;
            Y = seat[next.y][next.x]=='Y' ? Y-1 : Y;
        }
    }

    public static boolean bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] v = new boolean[5][5];
        boolean[][] m = new boolean[5][5];
        int cnt = 1;

        for(int i : list) {
            Point c = map.get(i);
            m[c.y][c.x] = true; 
        }

        q.offer(map.get(list.get(0)));
        v[map.get(list.get(0)).y][map.get(list.get(0)).x] = true;

        while(!q.isEmpty()) {
            Point c = q.poll();

            for(int i = 0; i < 4; i++) {
                Point next = new Point(c.y+dy[i], c.x+dx[i]);
                if(!isIn(next) || v[next.y][next.x] || m[next.y][next.x]==false) continue;

                v[next.y][next.x] = true; 
                cnt++;
                q.offer(next);
            }
        }
        return cnt==7 ? true : false;
    }

    public static boolean isIn(Point c) {
        if(0 <= c.y && c.y < 5 && 0 <= c.x && c.x < 5) return true;
        else return false;
    }

    public static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 5; i++) {
            seat[i] = br.readLine().toCharArray();
        }

        int index = 0;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                map.put(index++, new Point(i, j));
            }
        }
    }
}