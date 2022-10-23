package baekjoon.baek20221024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : sh Lee
 * @date : 22. 10. 23.
 */

/**
 * 1. 2중 반복문을 돌면서 색깔하나를 선택한다.
 * 2. 해당 색깔에서 같은 색깔로 BFS탐색을 해서 좌표를 저장하고 개수를 센다.
 * 3. 갯수가 4개 이상이라면, 저장된 좌표들을 전부 빈칸(*)으로 만들고 연쇄 카운트를 하나 증가시킨다.
 * 4. 중력을 적용해서 블록들을 떨어뜨린다.
 * 5. 2번을 다시 실행하면서 블록을 제거한다.
 * 6. 제거할 블록이 없으면 종료한다.
 */

public class BOJ11559_Puyo_Puyo {

    static char[][] maps;

    //같은 색깔 4개가 모여있는지 확인할 bfs메서드
//    static void

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        maps = new char[12][6];
        for(int i= 0; i < 12; i++){
            maps[i] = br.readLine().toCharArray();
        }







    }
}
