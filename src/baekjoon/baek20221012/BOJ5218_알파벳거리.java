package baekjoon.baek20221012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : sh Lee
 * @date : 22. 10. 12.
 */
public class BOJ5218_알파벳거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        for(int i= 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            String firstStr = st.nextToken();
            String secondStr = st.nextToken();

            int[] result = new int[firstStr.length()];
            for(int j = 0; j < firstStr.length(); j++){

                char firstChar = firstStr.charAt(j);
                char secondChar = secondStr.charAt(j);

                if(secondChar >= firstChar){
                    result[j] = secondChar - firstChar;

                }
                else{
                    result[j] = (secondChar + 26) - firstChar;
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Distances: ");

            for(int temp : result){
                sb.append(temp).append(" ");
            }

            System.out.println(sb.toString());
        }
    }
}
