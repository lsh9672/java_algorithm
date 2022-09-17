package ssafy.study;

import java.util.Arrays;
import java.util.Random;

public class streamSpeedCheck {
    public static void main(String[] args) {

        //랜덤한 숫자가 담긴 배열 만들기
        int[] arrayNum = new int[10000000];
        Random rand = new Random();
        for(int i = 0; i < arrayNum.length; i++){
            arrayNum[i] = rand.nextInt(9999999);
        }



        //for-loop를 활용한 방법
        long startTime = System.currentTimeMillis();
        int m = Integer.MIN_VALUE;
        for(int i = 0; i < arrayNum.length; i++){
            if(arrayNum[i] > m) m = arrayNum[i];
        }

        long forLoopTime = System.currentTimeMillis() - startTime;


        startTime = System.currentTimeMillis();

        int reduce = Arrays.stream(arrayNum)
                .reduce(Integer.MIN_VALUE, Math::max);

        long streamTime = System.currentTimeMillis() - startTime;

        System.out.println("for-loop : " + forLoopTime +"ms");

        System.out.println("stream : " + streamTime + "ms");
    }

}
