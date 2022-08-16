package baekjoon.baek20220331;
/* 백준 - 18258번 (자바, 자료구조 연습)*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class First {

    static Queue<Integer> intQue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = null;

        int lastValue = 0;

        for(int i = 0; i < n ; i++){
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();
            int value = 0;
            if(st.hasMoreTokens()){
                value = Integer.parseInt(st.nextToken());
            }

            if(command.equals("push")){
                intQue.add(value);
                lastValue = value;
            }
            else if(command.equals("pop")){
                if(intQue.isEmpty()){
                    sb.append(-1).append("\n");
                }
                else{
                    sb.append(intQue.poll()).append("\n");
                }
                continue;
            }
            else if(command.equals("size")){
                sb.append(intQue.size()).append("\n");

            }
            else if(command.equals("empty")){
                if(intQue.isEmpty()){
                    sb.append(1).append("\n");
                }
                else{
                    sb.append(0).append("\n");
                }
                continue;

            }
            else if(command.equals("front")){
                if(intQue.isEmpty()){
                    sb.append(-1).append("\n");
                }
                else{
                    sb.append(intQue.peek()).append("\n");
                }
                continue;
            }
            //back
            else{
                if(intQue.isEmpty()){
                    sb.append(-1).append("\n");
                }
                else{
                    sb.append(lastValue).append("\n");
                }
                continue;
            }

        }
        System.out.println(sb);

    }
}
