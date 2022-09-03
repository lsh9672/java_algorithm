package baekjoon.baek20220903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2580_스도쿠 {

    static int[][] maps;
    static List<Location> emptyLoc; // 빈공간 좌표 모아두기

    static StringBuilder sb = new StringBuilder();

    //출력을 위해 스트링 빌더에 저장.
    static void saveStringBuilder(int[][] tempMaps){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                sb.append(tempMaps[i][j]).append(" ");
            }

            sb.append("\n");
        }
        sb.setLength(sb.length()-1);
    }

    //가로 세로 각 칸 확인 - 9개의 합이 45가 나오는지 확인
    static boolean check(int[][] tempMaps, Location currentLoc) {

         int stdNum = tempMaps[currentLoc.x][currentLoc.y];

        //가로 확인
        for(int i = 0; i < 9; i++) {
            if(i != currentLoc.y && tempMaps[currentLoc.x][i] == stdNum) return false;
        }

        //세로 확인
        for(int i = 0; i < 9; i++) {
            if(i != currentLoc.x && tempMaps[i][currentLoc.y] == stdNum) return false;

        }

        int startX = currentLoc.x/3 * 3;
        int startY = currentLoc.y/3 * 3;
        //3*3 칸 확인
        for(int i = startX; i < startX + 3; i++) {
            for(int j = startY; j < startY + 3; j++) {
                if(i != currentLoc.x && j != currentLoc.y && tempMaps[i][j] == stdNum) return false;
            }
        }

        return true;
    }


    // 배열 복사 메서드
    static int[][] arrayCopy(int[][] tempMaps){
        int[][] returnMaps = new int[9][9];

        for(int i = 0; i < 9; i++) {
            System.arraycopy(tempMaps[i], 0, returnMaps[i], 0, 9);
        }

        return returnMaps;
    }


    static boolean recursive(int idx, int[][] tempMaps, Location tempLoc) {

        //모든칸에 숫자를 놓았다면.
        if(idx >= emptyLoc.size()) {
            if(check(tempMaps,tempLoc)) {
                //모든 빈칸에 숫자를 놓았고, 문제가 없다면,스트링빌더에 출력형식 만들고 종료
                saveStringBuilder(tempMaps);

                return true;
            }
            return false;
        }



        Location currentLoc = emptyLoc.get(idx);
        int[][] copyMaps = arrayCopy(tempMaps);//배열 복사하고

        for(int num = 1; num < 10; num++) {
            copyMaps[currentLoc.x][currentLoc.y] = num;//반복문 돌면서 해당위치에 숫자하나 놓고
            if(!check(copyMaps,currentLoc)) continue; //해당 위치에 숫자를 놓았는데 안되는 경우라면,
            if(recursive(idx+1,copyMaps, currentLoc)) return true;//재귀 호출 - 재귀돌았는데 true면 true리턴
        }


        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = null;

        emptyLoc = new ArrayList<>();

        maps = new int[9][9];

        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());

                if(maps[i][j] == 0) {
                    emptyLoc.add(new Location(i,j));
                }
            }
        }

        recursive(0, maps, null);
        System.out.println(sb);
    }

    static class Location{
        int x, y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

}
