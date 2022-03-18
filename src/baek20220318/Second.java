package baek20220318;
/*
* 백준 10845번(자바 자료구조 연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Second {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        Queue<Integer> que = new LinkedList<>();

        //링크드 리스트 이므로 맨 뒤의 값을 가지고 있어야됨.
        //저장을 안하고 있으면 맨뒤의 값을 찾기 위해서 O(n)의 시간이 걸림.
        int backValue = 0;

        List<Integer> result = new ArrayList<>();

        String inputCommand = null;
        StringTokenizer st = null;
        for(int i = 0 ; i < testCase ; i++){
            inputCommand = br.readLine();

            st = new StringTokenizer(inputCommand);
            String command = st.nextToken();
            if(command.equals("push")){
                backValue = Integer.parseInt(st.nextToken());
                que.offer(backValue);
            }
            else if(command.equals("pop")){
                if(que.isEmpty()){
                    result.add(-1);
                }
                else{
                    result.add(que.poll());
                }
            }
            else if(command.equals("size")){
                result.add(que.size());
            }
            else if(command.equals("empty")){
                if(que.isEmpty()){
                    result.add(1);
                }
                else{
                    result.add(0);
                }
            }
            else if(command.equals("front")){
                if(que.isEmpty()){
                    result.add(-1);
                }
                else{
                    result.add(que.peek());
                }
            }
            else if(command.equals("back")){
                if(que.isEmpty()){
                    result.add(-1);
                }
                else{
                    result.add(backValue);
                }

            }

        }

        for (Integer integer : result) {

            System.out.println(integer);
        }

    }
}
