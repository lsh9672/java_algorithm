package baekjoon.baek20220212;

/*
* 백준 CPU - 16506 (자바, 실버5, 구현)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashMap;


public class Third {

    //opcode를 저장할 해시맵(0~4비트를 정수로 해서 저장)
    static HashMap<String,Integer> opcodeHash = new HashMap<>();

    //해시에 값을 넣는 함수
    static void inputHash(){
        //1씩증가하다가 NOT-> MULT 2씩 증가하게 됨, 따라서 아무변수나 넣어서 맞춰줌
        String[] op = {"ADD","ADDC","SUB","SUBC","MOV","MOVC","AND","ANDC","OR","ORC","NOT","nothing","MULT","MULTC","LSFTL","LSFTLC","LSFTR","LSFTRC","ASFTR","ASFTRC","RL","RLC","RR","RRC"};
        for(int i= 0 ;i<op.length;i++){
            opcodeHash.put(op[i],i);
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //해시에 값 넣기
        inputHash();

        //명령어 개수
        int n = Integer.parseInt(br.readLine());

        String[] total = new String[n];
        StringTokenizer st = null;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            /*어셈블리어 코드*/
            String opCode = st.nextToken();
            int rD = Integer.parseInt(st.nextToken());
            int rA = Integer.parseInt(st.nextToken());
            //C#또는 rB가 온다
            int rB = Integer.parseInt(st.nextToken());

            //각 비트들을 저장해놓을 배열(바이너리를 문자열로 바꿔서 저장)
            //0: 0~4(5), 1: 5(1) , 2: 6~8(3), 3:9~11(3), 4: 12~15(4)
            String[] result = new String[5];

            //0~4비트 값 꺼냄
            int tempOpCode = opcodeHash.get(opCode);
            result[0] = String.format("%5s",Integer.toBinaryString(tempOpCode)).replace(" ","0");

            //6번째 비트는 0으로 고정임
            result[1] = "0";

            //rD는 주어진 값 그대로임
            result[2] = String.format("%3s",Integer.toBinaryString(rD)).replace(" ","0");

            //rA도 그대로
            result[3] = String.format("%3s",Integer.toBinaryString(rA)).replace(" ","0");

            //rB는 op코드 마지막에 따라서 달라진다.
            //op코드의 마지막 자리가 0,1에 따라서 달라지는데 이는 짝수인지 홀수인지를 물어보는것과 동일하다.
            //마지막 자리가 0이면(짝수이면) - rB, 이경우 맨끝자리가 0이되는데 이 말은 2를 곱하는것과 동일하다.
            if (tempOpCode % 2 == 0){
                result[4] = String.format("%4s",Integer.toBinaryString(rB*2)).replace(" ","0");
            }
            //1이면(홀수이면) - C#
            else{
                result[4] = String.format("%4s",Integer.toBinaryString(rB)).replace(" ","0");
            }

            StringBuilder sb = new StringBuilder();
            for(String k : result){
                sb.append(k);
            }

            total[i] = sb.toString();
        }

        for(String temp: total){
            System.out.println(temp);
        }

    }
}
