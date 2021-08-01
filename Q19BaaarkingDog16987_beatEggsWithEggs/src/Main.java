import java.io.*;

public class Main {
    static int[] armor, weight;
    static int N, MAX = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        armor = new int[N];

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            armor[i] = Integer.parseInt(input[0]);       //내구도
            weight[i] = Integer.parseInt(input[1]);   //
        }
        backTracking(0);
        System.out.println(MAX);
    }

    public static void backTracking(int temp) {
        boolean flag, flag2;

        if(temp==N) { // BASE CONDITION.
            int sum = 0;
            for(int i=0; i<N; i++) {
                if(armor[i]==0)
                sum++;
            }
            MAX = Math.max(MAX, sum);
            return;
        }

        flag = false;

        for(int i=0; i<N; i++) {
            if(i==temp) continue;

            if(armor[i]!=0) {

                flag = true;
                int egg1 = armor[temp] - weight[i];
                int egg2 = armor[i] - weight[temp];
                if(egg1>0) armor[temp] -= weight[i];
                else armor[temp] = 0;
                if(egg2>0) armor[i] -= weight[temp];
                else armor[i] = 0;

                flag2 = false;                
                for(int j=temp+1; j<N; j++) {
                    if(armor[j]!=0) {
                        flag2 = true;
                        backTracking(j);
                        break;
                    }
                }

                if(!flag2) backTracking(N);

                armor[temp] = egg1+weight[i];
                armor[i] = egg2+weight[temp];
            }
        } // loop end.
        if(!flag) backTracking(N);
    }
}