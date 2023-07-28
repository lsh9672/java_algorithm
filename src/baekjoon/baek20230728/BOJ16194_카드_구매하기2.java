package baekjoon.baek20230728;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 1. 1차원 배열을 이용한 디피
 * 2. 구매하려고 하는 카드의 개수 N이 1000개, 카드 팩은 1~N까지 존재, 해당 경우를 브루트 포스로 하면 볼것도 없이 시간 초과다.
 * 3. 따라서 dp를 이용하는 문제이다
 * 4.카드 팩 i로 1~N개를 가지는 최소 값을 누적시켜주면된다.
 * 5. 가령, 현재 구하려는 카드가 5개가 든 카드이고, 7개의 카드를 사는 최소 값은 카드 2개를 살떄의 최소값 + 현재 값이 저장되는 식이다.
 *
 *
 *
 */
public class BOJ16194_카드_구매하기2 {

    private static int N;//구하고자 하는 카드의 수
    private static int[] cardPackPriceList;//각 카드 팩의 가격을 저장하는 배열(인덱스 1부터 사용, 인덱스가 카드팩에 든 카드의 수)

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cardPackPriceList = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++){
            cardPackPriceList[i] = Integer.parseInt(st.nextToken());
        }

        //인덱스가 구하고자 하는 카드수
        int[] dp = new int[N+1];

        //카드팩 안의 카드수가 작은것 부터 N까지 반복함.
        for(int i = 1; i <= N; i++){

            //해당 카드의 가격.
            int price = cardPackPriceList[i];

            //현재 팩보다 적은 개수는 계산 불가(구하려는 가격이 5장일때인데, 팩에 든 카드가 6장이면 필요 없음)
            //현재 팩의 수보다 더 많은 카드 개수부터 계산.
            //j는 구하고자 하는 카드의 수.
            for(int j = i; j <= N; j++){

                //현재 카드 수의 최소값은, (현재 카드수 - 현재선택한 팩의 카드수)의 최소값 + 현재 선택한 팩의 카드수와 기존에 저장된 값을 비교해 봐야됨.
                int tempMinPrice = dp[j - i] + price;

                //기존에 구한것이 없다면 해당 값이 최소.
                if(dp[j] == 0){
                    dp[j] = tempMinPrice;
                    continue;
                }

                //기존에 구한것이 있다면 비교해서 더 작은 값으로 없데이트
                dp[j] = Math.min(dp[j], tempMinPrice);
            }
        }

        System.out.println(dp[N]);
    }
}
