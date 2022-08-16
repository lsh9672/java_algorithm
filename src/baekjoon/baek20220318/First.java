package baekjoon.baek20220318;

/*
* 백준 10866번 (자료구조 연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class First {
    static Deque<Integer> numQueue = new ArrayDeque<>();

    static List<Integer> resultArray = new ArrayList<>();

    static void dequeOperation(String inputCommand){
        StringTokenizer st = new StringTokenizer(inputCommand);

        String command = st.nextToken();

        //덱의 앞에 삽입
        if (command.equals("push_front")){
            int commandNum = Integer.parseInt(st.nextToken());
            numQueue.addFirst(commandNum);
        }
        //덱의 뒤에 넣기
        else if(command.equals("push_back")){
            int commandNum = Integer.parseInt(st.nextToken());
            numQueue.addLast(commandNum);
        }
        else if(command.equals("pop_front")){
            if(numQueue.isEmpty()){
                resultArray.add(-1);
            }
            else{
                resultArray.add(numQueue.removeFirst());
            }

        }
        else if(command.equals("pop_back")){
            if(numQueue.isEmpty()){
                resultArray.add(-1);
            }
            else{
                resultArray.add(numQueue.removeLast());
            }

        }
        else if(command.equals("size")){
            resultArray.add(numQueue.size());

        }
        else if(command.equals("empty")){
            if(numQueue.isEmpty()){
                resultArray.add(1);
            }
            else{
                resultArray.add(0);
            }

        }
        else if(command.equals("front")){
            if(numQueue.isEmpty()){
                resultArray.add(-1);
            }
            else{
                resultArray.add(numQueue.getFirst());
            }
        }
        else if(command.equals("back")){
            if(numQueue.isEmpty()){
                resultArray.add(-1);
            }
            else{
                resultArray.add(numQueue.getLast());
            }
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        int num = 0;
        String inputCommand = null;
        for(int i = 0 ; i < testCase ; i++){
            inputCommand = br.readLine();

            //deque연산
            dequeOperation(inputCommand);

        }

        for (Integer i : resultArray) {
            System.out.println(i);
        }
    }

}
