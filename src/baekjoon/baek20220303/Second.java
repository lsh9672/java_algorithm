package baekjoon.baek20220303;

/*
* 백준 10989번(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*일반적인 sort를 사용 - 보통 O(nlogn)*/
//public class Second {

//    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int testCase = Integer.parseInt(br.readLine());
//
//        int[] numList = new int[testCase];
//
//
//        for(int i = 0; i < testCase; i++){
//            numList[i] = Integer.parseInt(br.readLine());
//        }
//
//        Arrays.sort(numList);
//
//        StringBuilder sb = new StringBuilder();
//
//        for (int i : numList) {
//            sb.append(i).append("\n");
//        }
//
//        System.out.println(sb);
//
//    }


//}
//중복되는 값들이 나오기 때문에 counting sort(계수정렬) 를 이용한다(O(n)에 가깝게 나옴)
public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        //인덱스를 1번부터 쓸것
       int[] numList = new int[10001];
       //각 숫자가 몇번나왔는지 저장.
       for(int i = 0; i < testCase; i++){
           numList[Integer.parseInt(br.readLine())] += 1;
       }
       StringBuilder sb = new StringBuilder();
       for(int j = 1; j<=10000; j++){
           for(int k= 0; k < numList[j]; k++){
               sb.append(j).append("\n");
           }
       }

        System.out.println(sb);
    }
}

