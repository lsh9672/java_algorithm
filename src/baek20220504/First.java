package baek20220504;

/**
 * 백준 10157번 (구현연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //(r,c) => (x,y)
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int orderNum = Integer.parseInt(br.readLine());

        if(r*c < orderNum){
            System.out.println(0);
            return;
        }

        int current_x = 1;
        int current_y = 1;

        //상, 우, 하,좌
        Integer[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
        int dirCount = 0;

        int max_x = r;
        int min_x = 1;
        int max_y = c;
        int min_y = 1;

        for(int i = 1; i <= r*c; i++){

            if(i == orderNum){
                break;
            }

            int next_x = current_x + dir[dirCount][0];
            int next_y = current_y + dir[dirCount][1];

            //((next_x > r || next_x <= 0 || next_y > c || next_y <= 0) ||
            if (next_x > max_x || next_x < min_x || next_y > max_y || next_y < min_y){
                dirCount = (dirCount+1)%4;
                if(dirCount == 0){
//                    min_x = current_x;
                    min_y += 1;
                }

                else if(dirCount == 1){
//                    max_y = current_y;
                    min_x += 1;
                }
                else if(dirCount == 2){
//                    max_x = current_x;
                    max_y -= 1;
                }
                else{
//                    min_y = current_y;
                    max_x -= 1;

                }
                current_x = current_x + dir[dirCount][0];
                current_y = current_y + dir[dirCount][1];
            }

            else{
                current_x = next_x;
                current_y = next_y;
            }


        }

        System.out.println(current_x+ " "+ current_y);
    }
}
