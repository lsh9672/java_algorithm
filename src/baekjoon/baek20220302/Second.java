package baekjoon.baek20220302;

/*
* 백준 1652번(자바 구현연습 브론즈1)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Second {

    //가로로 누울수 있는 경우 구하기
    static int widthCount(String[] room,int n){

        int total =0;
        for(int i = 0 ; i<n; i++){
            //count가 2가 될때마다 total+1
            int count = 0;
            for(int j = 0; j < n; j++){
                if(room[i].charAt(j) == '.'){
                    count+=1;
                }
                else{
                    if(count>=2){
                        total += 1;
                    }
                    count = 0;
                }
            }
            if(count>=2){
                total += 1;
            }

        }

        return total;
    }

    //세로로 누울수 있는 경우 구하기
    static int heightCount(String[] room,int n){

        int total =0;
        for(int i = 0 ; i<n; i++){
            //count가 2가 될때마다 total+1
            int count = 0;
            for(int j = 0; j < n; j++){
                if(room[j].charAt(i) == '.'){
                    count+=1;
                }
                else{
                    if(count>=2){
                        total += 1;
                    }
                    count = 0;
                }
            }
            if(count>=2){
                total += 1;
            }
        }
        return total;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        //방 정보
        String[] room  = new String[n];



        // 방정보 입력받은것으로 채우기
        for(int i = 0; i < n; i++){
            room[i] = br.readLine();
        }
        //가로 결과
        int width = widthCount(room,n);

        //세로 결과
        int height = heightCount(room,n);

        System.out.println(width + " " + height);
    }
}
