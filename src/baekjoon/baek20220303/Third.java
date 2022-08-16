package baekjoon.baek20220303;

/*
* 백준 - 11650번(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Third {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        //좌표 저장
        int[][] numList = new int[testCase][2];

        StringTokenizer st = null;
        for(int i = 0; i < testCase; i++){
            st = new StringTokenizer(br.readLine());

            numList[i][0] = Integer.parseInt(st.nextToken());
            numList[i][1] = Integer.parseInt(st.nextToken());

        }

        //일반적인 익명 함수 방식 - 이 방식이 약간 더 빠르게 나옴(700ms)
//        Arrays.sort(numList, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] a, int[] b) {
//
//                if (a[0] == b[0]) return a[1]-b[1];
//                else return a[0] - b[0];
//            }
//        });

        //람다를 이용한 방식 - 조금 더 느리게 나옴(748ms)
        Arrays.sort(numList,(a,b)->{
            if (a[0] == b[0]) return a[1]-b[1];
            else return a[0] - b[0];
        });

        StringBuilder sb = new StringBuilder();
        for(int k =0; k < testCase; k++){
            sb.append(numList[k][0]).append(" ").append(numList[k][1]).append("\n");
        }

        System.out.println(sb);

    }
}
