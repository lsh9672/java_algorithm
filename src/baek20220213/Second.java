package baek20220213;

/*
* 백준 - 6359(만취한 상범, 브론즈2 , 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Second {

    //감옥 정보 - 0이면 열림 1이면 잠김
    static int[] jail;

    //방의 개수를 인자로 받아서 감옥을 0으로 채움 - 감옥 초기화
    static void jailState(int n){
        jail = new int[n];
        for(int i =0 ;i <n; i++){
            jail[i] =0;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        //방의 개수
        int n = 0;
        for(int i = 0 ; i<testCase; i++){
            n = Integer.parseInt(br.readLine());

            //감옥을 전부 0으로 초기화 - 1라운드에는 모두 연다.
            jailState(n);

            //x는 라운드
            for(int x = 2; x<=n ; x++){
                //y는 모든 감옥을 돈다
                for(int y = 0; y<n; y++){
                    //인덱스가 넘어가지 안도록 한다.
                    if(x*(y+1) -1 < n){
                        //2라운드에서는 각 배수의 방을 닫는다
                        if (x == 2){
                            jail[x*(y+1) -1] = 1;
                        }
                        //닫혀있으면 연다
                        else if(jail[x*(y+1) -1] == 1){
                            jail[x*(y+1) -1] = 0;
                        }
                        //열려있으면 닫는다
                        else {
                            jail[x*(y+1) -1] = 1;
                        }
                    }
                    //인덱스를 넘어가면 패스
                    else{
                        break;
                    }
                }

            }
            int count=0;
            //열린 방 개수 세기
            for(int z= 0 ; z<n; z++){
                if(jail[z] == 0){
                    count+=1;
                }
            }
            System.out.println(count);
        }

    }
}
