package baek20220209;

/*
* 백준 2420 - 사파리 월드(브론즈4, 자바 구현 연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class BaekSecond {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //각 숫자들이 20억까지 이므로 받을때는 int로 변환해도 되는데, 저장은 long에 해야됨
        //절대값 구할때 int형의 최대 치를 넘어가기 때문에 long에 담겨서 계산되어야 함.
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        System.out.println(Math.abs(n-m));

    }

}
