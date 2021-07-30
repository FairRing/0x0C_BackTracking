import java.io.*;
import java.util.Arrays;

public class Main {
    // 자음(21)c : b c d f g h j k l m n p q r s t v w x y z
    // 모음(5)v  : a e i o u
    static int L, C;
    static boolean[] isConsonant, isChecked;
    static String[] elements;
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[]; 
        // set L, C
        input = reader.readLine().split(" ");
        L = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        
        // set elements[], isConsonant[], isChecked[]
        isConsonant = new boolean[C];
        isChecked = new boolean[C];
        elements = new String[C];
        elements = reader.readLine().split(" ");
        Arrays.sort(elements);
        
        checkConsonantVowel();
        backTracking(0, 0);


    }
    private static void backTracking(int e, int location) {
        if (location == L) { // BASE CONDITION 1
            int c = 0, v = 0;

            for (int i = 0; i < C; i++) { // BASE CONDITION 2
                if (isChecked[i] && isConsonant[i]) c++;
                if (isChecked[i] && !isConsonant[i]) v++;
            }

            for (int i = 0; i < C; i++) {
                if ((isChecked[i]) && (c>1) && (v>0)) System.out.print(elements[i]);
            }
            if((c>1) && (v>0))System.out.println();
        }
        for (int i = e; i < C; i++) {
            isChecked[i] = true;
            backTracking(i+1, location+1);
            isChecked[i] = false;
        }

    }
    private static void checkConsonantVowel() {
        for (int i = 0; i < C; i++) {
            if (elements[i].equals("a")|| elements[i].equals("e") || elements[i].equals("i") || elements[i].equals("o") || elements[i].equals("u") ) {
                isConsonant[i] = false;
            } else {
                isConsonant[i] = true;
            }
        }
    } // checkConsonantVowel


}
