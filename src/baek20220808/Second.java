package baek20220808;

/**
 * SWEA 1228
 */

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Second {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/baek20220808/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int T = 10;
        for(int testCase = 1; testCase <= T; testCase++){
            StringTokenizer st = null;
            //원본 암호문의 길이
            int N = Integer.parseInt(br.readLine());

            //원본 암호문
            List<Integer> originSecret = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                originSecret.add(Integer.parseInt(st.nextToken()));
            }

            int commandNum = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < commandNum; i++){

                String command = st.nextToken(); //삽입한다는 명령어, 그냥 의미 없
                //삽입위치
                int x = Integer.parseInt(st.nextToken());
                //삽일할 숫자
                int y = Integer.parseInt(st.nextToken());

                for(int j = 0; j < y; j++){
                    originSecret.add(x, Integer.parseInt(st.nextToken()));
                    x++;
                }
            }

            StringBuilder result = new StringBuilder();
            result.append("#").append(testCase).append(" ");
            for(int i = 0; i < 10; i++){
                result.append(originSecret.get(i)).append(" ");
            }
            result.setLength(result.length() -1);

            System.out.println(result);

        }
    }
}
