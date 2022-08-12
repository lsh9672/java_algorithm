package baek20220812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15686 {

    static int N;
    static int M;
    static int[][] maps;

    //치킨집
    static List<Location> chicken;

    //사람이 사는 집.
    static List<Location> house;

    //각 사람들의 치킨집 까지 최소값 구하기.
    static int[] minPath;

    static int result;

    //도시의 치킨거리 구하기
    static int cityChicken(int[] temp) {
        int total = 0;
        for(int t : temp) {
            total += t;
        }

        return total;
    }

    // 각 사람들의 치킨값까지 최소값 업데이트 하는 배열
    static void updateMin(int[] temp, Location chicken) {
        // temp에 각 사람별 거리 계산해서 넣기
        for(int i = 0; i < house.size(); i++) {
            temp[i] = Integer.min(temp[i],Math.abs(house.get(i).x - chicken.x) + Math.abs(house.get(i).y - chicken.y));
        }
    }

    // 배열 복사 메서드
    static int[] arrayCopy(int[] temp) {
        int[] returnTemp = new int[temp.length];

        System.arraycopy(temp, 0, returnTemp, 0, temp.length);
        return returnTemp;
    }


    static void recursive(int idx, int count, int[] temp) {

        if(count == M) {
            //각 치킨집의 최단거리
            int minValue = cityChicken(temp);
            if(result > minValue) {
                result = minValue;
            }
            return;
        }

        for(int i = idx; i < chicken.size(); i++) {

            //배열 복사하고
            int[] tempArray = arrayCopy(temp);

            //치킨 거리 업데이트하고
            updateMin(tempArray, chicken.get(i));

            //재귀 호출
            recursive(i+1, count+1, tempArray);

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][N];

        // 맵 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 치킨집을 담을 리스트
        chicken = new ArrayList<>();
        house = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(maps[i][j] == 1) {
                    house.add(new Location(i,j));
                }
                if(maps[i][j] == 2) {
                    chicken.add(new Location(i,j));
                }
            }
        }

        minPath = new int[house.size()];
        Arrays.fill(minPath, Integer.MAX_VALUE);

        result = Integer.MAX_VALUE;

        recursive(0,0,minPath);

        System.out.println(result);

    }

    static class Location{
        int x;
        int y;

        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
