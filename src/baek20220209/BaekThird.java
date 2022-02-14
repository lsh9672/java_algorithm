package baek20220209;

/*
* 백준 - A/B(브론즈4, 구현 연습 )*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class BaekThird {

    public static void main(String[] args) throws IOException{

        //float의 정밀도는 7자리까지, double의 정밀도는 9자리 까지
        //문제에서는 정밀도를 9자리까지 허용하기 때문에 double을 써야됨.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //double보다 int가 담는 크기가 더 작아서 int로 변환해서 넣어도됨.
        double a = Integer.parseInt(st.nextToken());
        double b = Integer.parseInt(st.nextToken());

        System.out.println(a/b);
    }
}
