package baekjoon.baek20230731;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 디피를 이용해서 해결하는 문제,
 * 수를 문자열로 보면, 각 수는 인덱스를 가지고 있음.
 * n번째는 이전에 구했던 값에서 추가되는 형식이므로, 점화식을 세울 수 있음.
 * 주의할 점은,0이거나, 26을 넘거나 하는 수는 존재 할 수 없음.
 *
 * 각 수의 위치를 인덱스라고 생각하면 기본적으로 아래와 같이 볼 수 있다.]
 * dp[n] = dp[n - 1] + dp[n - 2]
 * 단 이 경우는 몇가지 경우를 따져야 한다. n이 0이거나 n-1와 n을 합쳐서 수를 만들었을때 1~26 의 수인지 등을 따져야 한다.
 */

public class BOJ2011_암호코드 {

    //두 수를 입력받아서 합치고 조건에 맞는지 확인하는
    private static boolean combineNum(char str1, char str2){

        int num1 = Character.getNumericValue(str1) * 10; // 10의 자리
        int num2 = Character.getNumericValue(str2);

        //앞자리가 0이면 존재할 수 없는 수.
        if(num1 == 0) return false;

        int total = num1 + num2;

        //범위를 벗어나면 안됨
        if(total < 1 || total > 26) return false;

        return true;

    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();
        int length = n.length();

        int[] dp = new int[length];//인덱스는 0부터 시작.

        //시작이 0이면 나올 수 없음
        if(Character.getNumericValue(n.charAt(0)) == 0){
            System.out.println(0);
            return;
        }

        if(length == 1){
            System.out.println(1);
            return;
        }

        dp[0] = 1;

        if(Character.getNumericValue(n.charAt(1)) != 0) {
            dp[1] = 1;
        }
        // 1번 인덱스와 2번 인덱스를 합쳤을때, 범위안의 수라면 +1
        if(combineNum(n.charAt(0), n.charAt(1))){
            dp[1]++;
        }

        boolean check = false;

        for(int i = 2; i < length; i++){

            int temp = Character.getNumericValue(n.charAt(i));

            //해당 숫자가 0이라면 자기자신은 불가능 하고, n - 1과 합쳐서 가능한지 봐야됨.
            if(temp == 0){
                if(combineNum(n.charAt(i - 1), n.charAt(i))){

                    dp[i] = dp[i - 2]; //i - 2까지 누적된 값에, i - 1과 i번째를 합한 한가지 경우이므로 그대로임.

                }
                //해당 수가 0인데, 이전의 수도 0이면 해석불가능함.
                else{
                    check = true;
                    break;
                }
            }

            //0이 아니라면 이전 값과 비교해서 가능한 수인지 확인해야됨.
            else{
                //자기 자신하나만 변환하는 것도 가능하고, 앞의 수랑 합쳐도 가능함.
                if(combineNum(n.charAt(i - 1), n.charAt(i))){
                    dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000;
                }
                //자기 자신은 가능하지만, 합치는 것은 불가능.
                else{
                    dp[i] = dp[i - 1];
                }

            }
        }

        if(check) System.out.println(0);
        else System.out.println(dp[length - 1]);

    }
}
