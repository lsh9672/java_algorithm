package baek20220214;

/*
* 백준 8진수2진수(1212번, 자바 구현) => 일일이 구현시 시간초과, 미리 8진수에 대응되는 2진수 값들을 넣어두어야됨*/


import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Third {
    static String[] octToBin = {"000","001","010","011","100","101","110","111"};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] octNum = br.readLine().trim().split("");

        if (octNum.length == 1 && octNum[0].equals("0")){
            System.out.println("0");
        }

        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < octNum.length; i++) {
                int temp = Integer.parseInt(octNum[i]);

                //첫번째 자리면 앞의 0 삭제해야됨
                if (i == 0) {
                    //전부 삭제
                    if (temp == 0) {
                        continue;
                    }
                    //0 두개 삭제
                    else if (temp == 1) {
                        sb.append("1");
                    }
                    //0 한개 삭제
                    else if (temp == 2) {
                        sb.append("10");
                    } else if (temp == 3) {
                        sb.append("11");
                    }
                    else{
                        sb.append(octToBin[temp]);
                    }
                }
                //나머지 경우는 그냥 붙이기
                else {
                    sb.append(octToBin[temp]);
                }
            }
            System.out.println(sb);
        }
    }
}
