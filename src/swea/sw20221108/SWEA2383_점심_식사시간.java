package swea.sw20221108;

import java.io.*;
import java.util.*;

public class SWEA2383_점심_식사시간 {

    static int N;

    static int[][] maps;

    static List<Location> peopleLoc;
    static StairLocation[] stairLoc;

    static int result; //최소시간 저장.

    static boolean[] selectPeople; //true면 0, false면 1

    static int distance(Location people, StairLocation stair){
        return Math.abs(people.x - stair.x) + Math.abs(people.y - stair.y);
    }

    //마지막으로 내려가는 사람의 소요 시간 계산.
    static int lastPeopleTime(List<Location> peopleStair, int type){

        int totalTime = 0;

        //도착순서대로 정렬 - 오름차순 정렬.
        peopleStair.sort(Comparator.naturalOrder());

        int stairCount = stairLoc[type].count; //해당 계단의 capacity

        //내려가려는 사람이 계단 수용량보다 작거나 같다면 다 내려갈 수 있음 - 최대 수용량은 3
        if(peopleStair.size() <= 3){
            //도착하면 1분뒤에 바로 내려가면 됨.
            //도착시간 + 계단 내려가는데 걸리는 시간 + 1분뒤부터 내려갈수 있음.
            totalTime = peopleStair.get(peopleStair.size()-1).distance + stairCount + 1;
        }
        //내려가려는 사람이 더 많다면 확인 필요
        else{
            //내려가는데 걸리는 시간 저장.
            int[] downTime = new int[peopleStair.size()];

            //첫 3명은 그냥 내려갈수 있음
            downTime[0] = peopleStair.get(0).distance + stairCount + 1;
            downTime[1] = peopleStair.get(1).distance + stairCount + 1;
            downTime[2] = peopleStair.get(2).distance + stairCount + 1;

            for(int i = 3; i < peopleStair.size(); i++){

                //계단에 들어가서 맨첨으로 나오는 사람이 걸리는 시간과, 내 도착시간 비교
                if(downTime[i - 3] <= peopleStair.get(i).distance){
                    downTime[i] = peopleStair.get(i).distance + stairCount + 1;
                }
                //도착하고 나서도 아직 들어갈수 없다면.
                else{
                    downTime[i] = downTime[i-3] + stairCount;
                }

            }

            totalTime = downTime[peopleStair.size()-1];

        }

        return totalTime;
    }

    static int timeCheck(){
        List<Location> firstStair = new ArrayList<>(); //첫번째 계단으로 가는 사람 목록
        List<Location> secondStair = new ArrayList<>(); // 두번째 계단으로 가는 사람 목록

        for(int i = 0; i < selectPeople.length; i++){

            Location Node = peopleLoc.get(i);
            //1번 계단으로 가는 경우.
            if(selectPeople[i]){

                firstStair.add(new Location(Node.x,Node.y, distance(Node,stairLoc[0])));
            }
            //2번 계단으로 가는 경우.
            else{
                secondStair.add(new Location(Node.x,Node.y, distance(Node,stairLoc[1])));
            }
        }


        int firstStairTime =  -1;
        int secondStairTime = -1;

        if(firstStair.size() == 0){
            firstStairTime = 0;
            secondStairTime = lastPeopleTime(secondStair, 1);
        }
        else if(secondStair.size() == 0){
            firstStairTime = lastPeopleTime(firstStair, 0);
            secondStairTime = 0;
        }
        else {
            firstStairTime = lastPeopleTime(firstStair, 0);
            secondStairTime = lastPeopleTime(secondStair, 1);
        }
        //두값중에 더 큰 값이 모든사람이 탈출하는데 걸리는 시간임.
        return Math.max(firstStairTime, secondStairTime);

    }


    static void recursive(int idx){

        result = Math.min(result, timeCheck());

        for(int i = idx; i < peopleLoc.size(); i++){
            selectPeople[i] = true;
            recursive(i+1);
            selectPeople[i] = false;
        }
    }


    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("/D:/SSAFY/intellijAlgo/algorithm/src/algo20221107/sample_input (4).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++){
            N = Integer.parseInt(br.readLine());

            maps = new int[N][N];
            peopleLoc = new ArrayList<>();
            stairLoc = new StairLocation[2];
            result = Integer.MAX_VALUE;

            int stairCount = 0;

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    maps[i][j] = Integer.parseInt(st.nextToken());

                    //사람 위치 저장
                    if(maps[i][j] == 1){
                        peopleLoc.add(new Location(i,j,0));
                    }
                    else if(maps[i][j] != 0){
                        stairLoc[stairCount] = new StairLocation(i,j,maps[i][j]);
                        stairCount++;
                    }
                }
            }

            selectPeople = new boolean[peopleLoc.size()];

            recursive(0);

            System.out.println("#" + testCase + " " + result);

        }
    }

    static class Location implements Comparable<Location>{
        int x, y, distance;

        Location(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Location o) {
            return Integer.compare(this.distance,o.distance);
        }

        @Override
        public String toString() {
            return "x : " + x + ", y : " + y + ", distance : " + distance;
        }
    }

    static class StairLocation{
        int x, y, count;

        StairLocation(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
