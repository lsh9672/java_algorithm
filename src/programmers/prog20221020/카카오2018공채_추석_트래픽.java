package programmers.prog20221020;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : sh Lee
 * @date : 22. 10. 20.
 */
public class 카카오2018공채_추석_트래픽 {

    static int timeToSec(String strTime){

        String[] timeArray = strTime.split(":");

        int time = Integer.parseInt(timeArray[0]) * 60 * 60;
        int min = Integer.parseInt(timeArray[1]) * 60;
        int sec = Integer.parseInt(timeArray[2]);

        return time + min + sec;
    }

    public int solution(String[] lines) {

        //공백을 기준으로 되어있기 때문에 공백을 기준으로 자르고 HH:MM:SS => SS로 변환함.
        List<Node> timeNode = new ArrayList<>();
        for(int i = 0; i < lines.length; i++){
            String line = lines[i];

            String[] timeArray = line.split(" ");

            String[] tempTimeArray = timeArray[1].split("[.]");
            int endTime =(int)(((double) timeToSec(tempTimeArray[0]) + (double)Integer.parseInt(tempTimeArray[1]) * 0.001) * 1000.0); //종료시간.
            int processTime = (int)(Double.parseDouble(timeArray[2].substring(0,timeArray[2].length()-1)) * 1000.0);//처리시간

            int startTime = endTime - processTime + 1;

           timeNode.add(new Node(startTime, endTime));
        }


        int maxProcess = Integer.MIN_VALUE; //최대 처리량
        for(int i= 0; i < timeNode.size(); i++){
            //기준 구간.
            Node stdNode = timeNode.get(i);
            int count = 1;
            for(int j = i+1; j < timeNode.size(); j++){
                Node tempNode = timeNode.get(j);

                if(stdNode.end + 1000 > tempNode.start) {
                    count++;
                }
            }


            maxProcess = Math.max(maxProcess,count);
        }
        return maxProcess;
    }

    public static void main(String[] args) {

        카카오2018공채_추석_트래픽 s = new 카카오2018공채_추석_트래픽();
        String[] lines1 = {"2016-09-15 00:00:00.000 3s"};
        System.out.println(s.solution(lines1));

        String[] lines2 = {"2016-09-15 23:59:59.999 0.001s"};
        System.out.println(s.solution(lines2));

        String[] lines3 = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(s.solution(lines3));

        String[] lines4 = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(s.solution(lines4));

        String[] lines5 = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};
        System.out.println(s.solution(lines5));

        String[] lines6 = {"2016-09-15 00:00:00.000 2.3s", "2016-09-15 23:59:59.999 0.1s"};
        System.out.println(s.solution(lines6));

    }

    static class Node{
        int start, end;

        Node(int start,int end){
            this.start = start;
            this.end = end;
        }
    }
}
