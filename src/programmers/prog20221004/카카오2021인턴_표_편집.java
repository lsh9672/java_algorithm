package programmers.prog20221004;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author : sh Lee
 * @date : 22. 10. 4.
 */

public class 카카오2021인턴_표_편집 {

    static List<Integer> graph;
    static int currentLocation;

    static Stack<RemoveNode> lastRemoveStack;

    //value의 경우, C,Z 일때는 사용하지 않음
    static void updateLocation(String command,int value){

        if(command.equals("U")){
            currentLocation -= value;

        }
        else if(command.equals("D")){
            currentLocation += value;
        }
        else if(command.equals("C")){

            lastRemoveStack.add(new RemoveNode(currentLocation,graph.get(currentLocation))); //마지막으로 삭제된 위치와 값을 스택에 저장.

            graph.remove(currentLocation);

            if(currentLocation == graph.size()){

                currentLocation--;
            }

        }
        else if(command.equals("Z")){

            //이전에 삭제된 값을 저장하는 스택이 비었다면 패스
            if(lastRemoveStack.isEmpty()) return;


            RemoveNode temp = lastRemoveStack.pop();
            graph.add(temp.index, temp.value);
            //현재행보다 복구할 이전에 삭제된 행이 더 먼저라면 현재행+1
            if(currentLocation >= temp.index){
                currentLocation++;

            }
        }

    }
    public String solution(int n, int k, String[] cmd) {

        graph = new LinkedList<>();

        currentLocation = k; //현재위치
        lastRemoveStack = new Stack<>();

        //링크드리스트에 넣기.
        for(int i = 0; i < n; i++){
            graph.add(i);
        }

        for(String temp : cmd){

            String[] tempArray = temp.split(" ");
            if(tempArray.length == 2){
                updateLocation(tempArray[0],Integer.parseInt(tempArray[1]));
            }
            else{
                updateLocation(tempArray[0],-1);
            }

        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        int index = 0;

        while(index < graph.size()){

            if(graph.get(index) == count){
                sb.append("O");
                index++;
                count++;
            }
            else{
                sb.append("X");
                count++;
            }

        }


        return sb.toString();
    }

    public static void main(String[] args) {

        카카오2021인턴_표_편집 s = new 카카오2021인턴_표_편집();
        int n1 = 8;
        int k1 = 2;
        String[] cmd1 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        System.out.println(s.solution(n1,k1,cmd1));

        int n2 = 8;
        int k2 = 2;
        String[] cmd2 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        System.out.println(s.solution(n2,k2,cmd2));

    }

    static class RemoveNode{
        int index, value;

        RemoveNode(int index, int value){
            this.index = index;
            this.value = value;
        }
    }
}
