package baek20220726;

/**
 * 백준 20055번 (A형 대비)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class First {

    static int[] belt;
    static boolean[] robotLoc;
    static int N;
    static int K;
    //올리는 위치과 내리는 위치 정의
    static int start;
    static int end;


    //1번 단계 - 벨트가 각 로봇과 함께 회전한다.
    static void beltMove(){
        //값을 하나씩 밀면 시간이 오래걸리기 때문에 올리는 위치와 내리는 위치만 저장하고 이 지점들을 -- 한다.
        start--;
        end--;

        if(start == -1) start = 2*N -1;
        if(end == -1) end = 2*N -1;

        //로봇이 내리는 위치에 오면 바로 내려버림
        if(robotLoc[end] == true){
            robotLoc[end] = false;
        }


    }
    //2번 단계 - 벨트 회전 방향으로 이동 (이동하려는 칸에는 로봇이 없어야 하고, 내구도가 1이상 남아야 한다.)
    static void robotMove(){
        //올리는 지점과, 내리는 지점 전까지만 탐색하면 됨
        int idx = end-1;
        if(idx == -1) idx = 2*N -1;

        int lastIdx = start-1;
        if(lastIdx == -1) lastIdx = 2*N -1;

        while(idx != lastIdx){

            //로봇이 있다면
            if(robotLoc[idx] == true){
                int nextLoc = (idx+1)%(2*N);
                if(robotLoc[nextLoc] == false && belt[nextLoc] >= 1){
                    //내리는 지점
                    if(nextLoc == end){
                        robotLoc[idx] = false;
                        belt[nextLoc]--;
                    }

                    //내리는 지점이 아님
                    else{
                        robotLoc[idx] = false;
                        robotLoc[nextLoc] = true;
                        belt[nextLoc]--;
                    }
                }
            }
            idx--;
            if(idx == -1){
                idx = 2*N-1;
            }
        }
    }
    //3번 단계 - 올리는 위치에 로봇을 올린다.(단 내구도가 0 이 아니라면)
    static void upRobotInit(){

        if(belt[start] > 0){
            robotLoc[start] = true;
            belt[start]--;
        }
    }

    //4단계  - 매 반복마다 0의 개수가 k이상이면 종료
    static Boolean zeroCheck(){

        int count = 0;
        for(int temp : belt){
            if(temp == 0) {
                count++;

                if(count >= K){
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
        //2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다
            //(로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다)
        //3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        //4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.

        //중요한 점은 내리는 위치이면 로봇을 내림(내리는 위치 : N-1)

        //컨베이너 벨트
        belt = new int[2*N];

        //로봇 위치 - 내리는 위치면 내림
        robotLoc = new boolean[2*N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2*N; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        start = 0;
        end = N-1;

        //반복 단계 세기
        int result = 0;

        //반복
        while(zeroCheck()){
            result++;
            beltMove();
            robotMove();
            upRobotInit();

        }

        System.out.println(result);

    }
}
