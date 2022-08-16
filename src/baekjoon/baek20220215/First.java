package baekjoon.baek20220215;
/*
* 백준 2007년 - 1924번,자바, 구현*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class First {

    //인덱스를 1번부터 씀
    static int[] dayInfo = {0,31,28,31,30,31,30,31,31,30,31,30,31};

    //월화수목금토일 - 7로 나눴때 나머지가 인덱스
    static String[] weekInfo = {"SUN","MON","TUE","WED","THU","FRI","SAT"};

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();

        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());

        //월을 일로 변환하여 누적
        int total = 0;

        for(int i = 1; i<month ; i++){
            total += dayInfo[i];
        }
        total += day;

        System.out.println(weekInfo[total%7]);

    }
}
