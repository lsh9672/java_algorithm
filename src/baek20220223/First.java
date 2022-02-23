package baek20220223;
/*
* 백준 - 1259번 펠린드롬 수(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class First {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String temp = br.readLine();
            //0이 입력으로 들어오면 종료
            if(temp.equals("0")) break;

            StringBuffer sb = new StringBuffer(temp);
            sb.reverse();
            if(temp.equals(sb.toString())){
                System.out.println("yes");
            }
            else{
                System.out.println("no");
            }

        }
    }
}
