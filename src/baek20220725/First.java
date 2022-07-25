package baek20220725;

/**
 * 백준 1592번 (역량테스트 대비)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] ballCount = new int[N];

        int currentPeople = 0;

        //볼 던진 횟수
        int throwCount = 0;

        while(true){

            //공을 받은 횟수 증가
            ballCount[currentPeople]++;

            //볼을 받은 사람이 M번 받았다면 종료
            if(ballCount[currentPeople] == M){
                break;
            }

            //게임이 끝나지 않았다면, 공을 던지는 데 홀수 짝수에 따라서 던지는 방향이 달라짐
            //짝수일때
            if(ballCount[currentPeople]%2 == 0){
                int temp = currentPeople - L;
                if(temp >= 0){
                    currentPeople = temp % N;
                }
                else{
                    //자바의 경우 음수 모듈러 연산의 경우 파이썬과 같은 결과값이 나오지 않음
                    //따라서 별도의 연산을 통해서 파이썬과 동일한 값이 나오게 함.
                    currentPeople = ((temp *(-1)) % N)*(-1) + N;
                }



            }
            //홀수일때 - 시계방향(배열에서 오른쪽으로 던짐)
            else{
                currentPeople = (currentPeople + L) % N;

            }

            throwCount++;

        }

        System.out.println(throwCount);

    }
}
