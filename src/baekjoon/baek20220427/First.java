package baekjoon.baek20220427;

/**
 * 백준 2304번(자료구조 연습)
 * 아이디어 : 가장 높은 기둥을 기준으로 왼쪽 오른쪽으로 나눠서 계산
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        Integer[][] pillar = new Integer[n][2];
        for(int i =0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());

            //왼쪽위치
            pillar[i][0] = Integer.parseInt(st.nextToken());

            //높이
            pillar[i][1] = Integer.parseInt(st.nextToken());

        }


        Arrays.sort(pillar, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] t1, Integer[] t2) {
                if (t1[0] < t2[0]){
                    return -1;
                }
                else{
                    return 1;
                }
            }
        });

        //최댓값 위치 찾기
        int max = pillar[0][1];
        int maxIndex = 0;

        for(int i =1; i< n; i++){
            if (max < pillar[i][1]){
                max = pillar[i][1];
                maxIndex = i;
            }
        }
        //가장 높은 기둥의 넓이 넣기 - 밑변이 1이므로 높이*1
        int result_total = max;

        //맨앞부터 최대값 위치까지
        int current_hieght = pillar[0][1];
        int current_location = pillar[0][0];
        for(int i = 1; i <= maxIndex ;i++){
            // 현재보다 다음 기둥의 높이가 크다면, 넓이 더하기
            if (current_hieght < pillar[i][1]){
                result_total += (pillar[i][0] - current_location) * current_hieght;

//                System.out.println("current_location = " + current_location + "current_hieght = " + current_hieght );
//                System.out.println((pillar[i][0] - current_location) * current_hieght);

                current_hieght = pillar[i][1];
                current_location = pillar[i][0];

            }
        }
        //맨뒤부터 최대값 위치까지
        current_hieght = pillar[n-1][1];
        current_location = pillar[n-1][0];
        for(int i = n-2; i>=maxIndex; i--){
            // 현재보다 다음 기둥의 높이가 크다면, 넓이 더하기
            if (current_hieght <= pillar[i][1]){
                result_total += (current_location - pillar[i][0]) * current_hieght;
                current_hieght = pillar[i][1];
                current_location = pillar[i][0];
            }
        }


        System.out.println(result_total);

    }
}
