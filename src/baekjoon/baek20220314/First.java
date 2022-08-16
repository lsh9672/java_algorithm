package baekjoon.baek20220314;
/*
* 백준 1021번 (자바 자료구조 연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class First {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        //뽑으려고 하는 숫자
        Integer[] wantedList = new Integer[m];
        for(int i = 0; i<m; i++){
            wantedList[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> numList = new ArrayList<>();

        for(int i = 1; i<=n; i++){
            numList.add(i);
        }


        int result =0;
        for (int wantedNum : wantedList) {
            //리스트의 첫번째 값이 원하는 값이면 빼기
            if(numList.get(0) == wantedNum){
                numList.remove(0);
            }

            else{
                int left = numList.indexOf(wantedNum);
                int right = numList.size() - numList.indexOf(wantedNum);
                //왼쪽이동이 더 짧으면 왼쪽으로 이동.
                if(left < right){
                    Collections.rotate(numList,left*(-1));
                    result += left;

                }
                else{
                    Collections.rotate(numList,right);
                    result += right;

                }
                numList.remove(0);

            }
        }

        System.out.println(result);


    }
}
