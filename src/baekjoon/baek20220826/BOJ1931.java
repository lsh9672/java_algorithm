package baekjoon.baek20220826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1931 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        List<LectureTime> lectureList = new ArrayList<>();


        int count = 1;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            lectureList.add(new LectureTime(startTime,endTime));
        }

        Collections.sort(lectureList);

        int stdEndTime = lectureList.get(0).endTime;

        for(int i = 1; i < N; i++){
            LectureTime tempLecture = lectureList.get(i);
            if(stdEndTime <= tempLecture.startTime){
                count++;
                stdEndTime = tempLecture.endTime;
            }
        }

        System.out.println(count);
    }

    static class LectureTime implements Comparable<LectureTime>{
        int startTime;
        int endTime;

        LectureTime(int startTime, int endTime){
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(LectureTime o) {
            return this.endTime == o.endTime ? this.startTime - o.startTime : this.endTime - o.endTime;
        }

        @Override
        public String toString() {


            return "startTime : " + startTime + " endTime : " + endTime;
        }
    }
}
