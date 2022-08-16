package baekjoon.baek20220219;
/*
* 백준 - 3985번 롤케이크 브론즈1 구현연습*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Second {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cakeLength = Integer.parseInt(br.readLine());

        int peopleNum = Integer.parseInt(br.readLine());

        StringTokenizer st = null;

        int expectResult = 0;
        int expectResultPeople = 0;

        int[] cake = new int[cakeLength+1];
        for(int i = 0; i<peopleNum; i++){
            st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if(second - first + 1 >expectResult){
                expectResult = second - first + 1;
                expectResultPeople = i+1;
            }
            //케이크 - 1번인덱스 부터 씀
            for(int j = first ; j<=second;j++){
                //비어있으면 값 넣기
                if(cake[j] == 0){
                    cake[j] = i+1;
                }
            }

        }
        //각 사람마다 먹을수 있는 케익크기 카운트
        int[] eatCake = new int[peopleNum+1];
        for(int x : cake) {
            if(x != 0){
                eatCake[x]+=1;
            }
        }
        int result = 0;
        int resultPeople = 0;
        for(int y = 1; y< eatCake.length; y++){

            if(eatCake[y] > result){
                result = eatCake[y];
                resultPeople = y;
            }

        }

        System.out.println(expectResultPeople);
        System.out.println(resultPeople);
    }
}
