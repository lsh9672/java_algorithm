package baek20220225;

/*
* 백준 1475번(자바 구현연습 - 실버5)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Third {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //6과 9 카운트
        int count = 0;

        int[] numList = new int[10];

        String[] input = br.readLine().split("");

        for(int i = 0; i<input.length; i++){
            int temp = Integer.parseInt(input[i]);
            if(temp == 6 || temp == 9){
                count+=1;
                if(count==2){
                    count =0;
                    numList[6] +=1;
                    numList[9] +=1;
                }
            }
            else{
                numList[temp] += 1;
            }
        }

        if(count == 1){
            numList[6]+=1;
            numList[9]+=1;
        }

        int result = numList[0];

        for(int j = 1; j < 10; j++){
            if(result < numList[j]){
                result = numList[j];
            }
        }
        System.out.println(result);

    }
}
