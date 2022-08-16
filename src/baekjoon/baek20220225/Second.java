package baekjoon.baek20220225;
/*
* 백준 - 17363번 (자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Second {

    static Map<String,String> mapping = new HashMap<>();

    static void mappingSetting(){
        mapping.put(".",".");
        mapping.put("O","O");
        mapping.put("-","|");
        mapping.put("|","-");
        mapping.put("/","\\");
        mapping.put("\\","/");
        mapping.put("^","<");
        mapping.put("<","v");
        mapping.put("v",">");
        mapping.put(">","^");
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        mappingSetting();

        //세로
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[][] inputString = new String[n][m];
        for(int i = 0; i<n; i++){
            String[] temp = br.readLine().split("");
            inputString[i] = temp;
        }

        String[] result = new String[m];

        //옆으로 넘어뜨리기

        for(int b = 0; b<m; b++){
            StringBuffer sb = new StringBuffer();
            sb.setLength(0);
            for(int a = 0; a < n; a++){
                sb.append(mapping.get(inputString[a][b]));
            }
            result[b] = sb.toString();
        }

        for(int k = m-1; k>=0;k--){
            System.out.println(result[k]);
        }

    }
}
