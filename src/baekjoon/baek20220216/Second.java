package baekjoon.baek20220216;

/*
* 백준 - 그릇 7567번 구현연습*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //접시를 입력받아서 배열에 넣기
        String[] dishState = br.readLine().split("");

        //접시가 한개만 들어오면 10
        if(dishState.length == 1){
            System.out.println(10);
        }
        else{
            //초기 높이는 10, 첫번째 접시는 10
            int totalHeight = 10;

            //접시 한개씩 확인하면서 확인 - 이전 접시랑 비교
            for(int i = 1; i< dishState.length; i++){
                if(dishState[i-1].equals(dishState[i])){
                    totalHeight += 5;
                }
                else{
                    totalHeight+=10;
                }
            }
            System.out.println(totalHeight);
        }

    }
}
