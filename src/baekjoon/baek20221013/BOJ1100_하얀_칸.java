package baekjoon.baek20221013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 13.
 */
public class BOJ1100_하얀_칸 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] maps = new char[8][8];

        for(int i = 0; i < 8; i++){
            maps[i] = br.readLine().toCharArray();
        }

        int result = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                //흰색 시작
                if(i%2 == 0){
                    if(j%2 == 0 && maps[i][j] == 'F') result++;
                }
                //검은색 시작
                else{
                    if(j%2 == 1 && maps[i][j] == 'F') result++;

                }

            }
        }
        System.out.println(result);
    }
}
