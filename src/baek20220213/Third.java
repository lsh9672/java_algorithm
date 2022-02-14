package baek20220213;

/*
* 백준 - 20546(기적의 매매법,브론즈1, 구현 연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Third {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //14일치 주가 저장할 배열
    static int[] stockList = new int[14];

    //준현이 방식 - bnp
    static int bnpMethod(int money){
        //보유 주식수 카운트
        int count = 0;

        for(int i = 0; i< 14; i++){
            //보유현금이 더 많으면 구매
            if(stockList[i] <= money){
                count += (money/stockList[i]);
                money -= stockList[i]* (money/stockList[i]);
            }

        }

        return money+(stockList[13]*count);
    }

    //성민이 방식 - timing
    static int timingMethod(int money){
        //보유 주식수 카운트
        int count = 0;

        //기본이 전량 매수 전량 매도

        //3일 연속 상승시 3일째 되는 날 주식 전액 매도(팔아버림)인
        //3일 연속 하락시에 주식 전액 매수(살수 있는 만큼 사버림)

        for(int i = 3; i <14 ; i++){

            //상승하는지 확인
            if(stockList[i-1] > stockList[i-2] && stockList[i-2] > stockList[i-3]){
                // //전량 매도
                if(count > 0) {
                    money += count * stockList[i];
                    count = 0;
                }
            }
            //하락
            else if(stockList[i-1] < stockList[i-2] && stockList[i-2] < stockList[i-3]){
                //보유 현금이 주가보다 많으면 매수
                if(stockList[i] <= money){
                    count += money / stockList[i];
                    money -= stockList[i]*(money / stockList[i]);
                }
            }

        }

        return money + stockList[13]*count;
    }

    public static void main(String[] args) throws Exception{

        int money = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        //주가입력
        for(int i=0 ; i<14;i++){
            stockList[i] = Integer.parseInt(st.nextToken());
        }

        if(bnpMethod(money)>timingMethod(money)){
            System.out.println("BNP");
        }
        else if(bnpMethod(money)<timingMethod(money)){
            System.out.println("TIMING");
        }
        else{
            System.out.println("SAMESAME");
        }

    }
}
