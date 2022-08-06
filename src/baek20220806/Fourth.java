package baek20220806;

/**
 * BOJ 17825번
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Fourth {

    static int totalScore;

    static int maxN = 10;
    //주사위 눈금
    static int[] diceArray;
    //기물의 순서
    static int[] orderArray;
    //현재위치를 저장할 배열 1부터 시작
    static int[] currentLoc;


    //4개의 말로 10번 움직이는 경우의 수
    static void playOrder(int idx){
        if(idx >= maxN){
            System.out.println(Arrays.toString(orderArray));
            /*순서대로 게임 말 놓는 로직 작*/
            return;
        }

        for(int i = 1; i <= 4; i++){
            orderArray[idx] = i;
            playOrder(idx+1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        diceArray = new int[10];

        //주사위 눈금 10개 입력받기
        for(int i = 0; i < 10; i++){
            diceArray[i] = Integer.parseInt(st.nextToken());
        }

        //기물 순서 저장.
        orderArray = new int[10];

        //최대 값을 구하기 위해 최소 값 저장
        totalScore = Integer.MIN_VALUE;

        //순서 초기화 - 인덱스 1부터 쓰기위해 크기는 5로 생성
        currentLoc = new int[5];

        playOrder(0);



    }

}
