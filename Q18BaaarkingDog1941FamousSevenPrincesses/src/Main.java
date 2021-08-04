import java.io.*;
import java.util.*;

class XY{
    int x, y;
    XY(int x, int y){
        this.x=x;
        this.y=y;
    }
}

public class Main {
    static int MAX=5, ans=0;
    static boolean visited[] = new boolean[MAX*MAX];
    static char map[][] = new char[MAX][MAX];
    static int aroundX[] = {0,0,1,-1}, aroundY[] = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));    
        String input;

        for(int i=0; i<MAX; i++) {
            input = reader.readLine();
            for(int j=0; j<MAX; j++) map[i][j]=input.charAt(j);
        }
        
        DFS(0,0,0);
        System.out.println(ans);
    }
    public static void DFS(int start, int team, int n) {
        if(team==7) {
            if(isPossible(n)) ans++;
            return ;
        }
        
        for(int i=start; i<25; i++) {
            if(!visited[n|(1<<i)]) {
                visited[n|(1<<i)] = true;
                DFS(i,team+1,n|(1<<i));
                visited[n|(1<<i)] = false;
            }
        }
    }

    public static boolean isPossible(int n) {
        ArrayList<Integer>list = new ArrayList<>();
        for(int i=0; i<25; i++) {
            if((n & (1<<i))!=0) {
                list.add(i);
            }
        }

        // CHECK CONNECTED
        boolean map_visited[][] = new boolean[5][5];
        for(int i=0; i<list.size(); i++) {
            int r = list.get(i)/5;
            int c = list.get(i)%5;
            map_visited[r][c] =true;
        }
        
        Queue<XY>q = new LinkedList<>();
        q.add(new XY(list.get(0)/5,list.get(0)%5));
        map_visited[list.get(0)/5][list.get(0)%5]=false;
        
        int bfs_cnt =0;
        while(!q.isEmpty()) {
            XY a= q.poll();
            bfs_cnt++;
            for(int i=0; i<4; i++) {
                int nx = a.x+aroundX[i];
                int ny = a.y+aroundY[i];
                if(isRange(nx,ny) && map_visited[nx][ny]==true) {
                    q.add(new XY(nx,ny));
                    map_visited[nx][ny]=false;
                }
            }
        }

        // FAIL CASE :: NOT CONNECTED ALL
        if(bfs_cnt!=7) return false;
        
        // FALE CASE :: 이다솜 > 4
        int s =0;
        for(int i=0; i<list.size(); i++) {
            int tmp_r = list.get(i)/5;
            int tmp_c = list.get(i)%5;
            if(map[tmp_r][tmp_c]=='S') {
                s++;
            }
        }
        if(s<4)  return false;
        return true;
    }
    public static boolean isRange(int x, int y) {
        
        if(x>=0 && y>=0 && x<5 && y<5) return true;

        return false;
    }
}