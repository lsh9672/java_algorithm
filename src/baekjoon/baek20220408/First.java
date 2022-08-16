package baekjoon.baek20220408;

/* 백준 1966번(자바 구현연습)*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        for(int i = 0; i < testCase; i++){

            LinkedList<Integer[]> queDoc = new LinkedList<>();

            st = new StringTokenizer(br.readLine());

            int numDoc = Integer.parseInt(st.nextToken());
            int targetDoc = Integer.parseInt(st.nextToken());

            StringTokenizer st2 = new StringTokenizer(br.readLine());

            //큐 채우기
            for(int j = 0; j<numDoc; j++){
                //[중요도, 인덱스]
                Integer[] temp = new Integer[2];

                temp[0] = Integer.parseInt(st2.nextToken());
                temp[1] = j;

                queDoc.add(temp);
            }

            //몇번째에 인쇄되는지 확인
            int count = 0;
            while(true){
                //큐의 맨앞값을 뺌
                Integer[] temp = queDoc.poll();


                //중간에 break되었는지 여부확인
                boolean check = false;
                for(int x= 0; x < queDoc.size(); x++){
                    if(queDoc.get(x)[0] > temp[0]){
                        queDoc.add(temp);
                        check = true;
                        break;
                    }
                }

                if(check == false){
                    count+=1;
                    if(temp[1] == targetDoc){
                        sb.append(count).append("\n");
                        break;
                    }
                }

            }

        }

        System.out.println(sb);
    }
}
