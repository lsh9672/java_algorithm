package baekjoon.baek20220918;

/*
1. 하이픈으로 이루어져있기 떄문에 split()을 이용해서 하이픈 기준으로 나눈다
2. 각 문자열의 0번째 인덱스를 뽑는다.
3. 뽑은 문자를 이어 붙이는데, String에 붙이면 매 연산마다 string 객체를 생성하기 때문에 비효율적이다. 따라서 StringBuilder객체에 이어 붙인다.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ2902_KMP는_왜_KMP일까 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputStr = br.readLine().split("-");

        StringBuilder result = new StringBuilder();
        for(String temp : inputStr){
             result.append(temp.charAt(0));
        }

        System.out.println(result);
    }
}
