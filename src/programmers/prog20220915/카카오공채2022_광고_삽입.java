package programmers.prog20220915;

import java.util.Arrays;

public class 카카오공채2022_광고_삽입 {


    //HH:MM:SS -> 초
    static int timeToSec(String time){
        String[] temp = time.split(":");

        int hour = Integer.parseInt(temp[0]) * 3600;
        int min = Integer.parseInt(temp[1]) * 60;
        int sec = Integer.parseInt(temp[2]);


        return hour + min + sec;
    }

    //초 -> HH:MM:SS
    static String SecToTime(long time){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%02d",time/3600)).append(":").append(String.format("%02d",(time%3600)/60)).append(":").append(String.format("%02d",(time%3600)%60));
        return sb.toString();
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        //최대시간이 99:59:59이므로 3600*100크기의 배열을 만들어서 각 시간의 범위를 저장한다.
        long[] adverTime = new long[360000];

        //시청자들의 영상 재생구간 인원저장.
        for(String log : logs){
            String[] temp = log.split("-");
            int startTime = timeToSec(temp[0]);
            int endTime = timeToSec(temp[1]);


            //00:00:10 ~ 00:00:12 => 2초간 시청했다면, 10,11초에 인원 카운트
            for(int i = startTime; i < endTime; i++){
                adverTime[i]++;
            }
        }


        int advTimeSec = timeToSec(adv_time); //광고시간
        int playTimeSec = timeToSec(play_time); //총 플레이 시간.

        long maxValue = 0;
        int maxStartIndex = 0; //시작시간 저장.


        //adv_time 범위만큼 이동하면서 최대가 되는 값을 구함


        //초기 값을 미리 저장해줌 - 이후에는 이동하면서 바뀌는 부분만 변경하도록 함.
        for(int i = 0; i < advTimeSec; i++){
            maxValue += adverTime[i];
        }

        long totalTime = maxValue;

        //초기 구간을 구했으니 다음 구간부터 탐색 시작.
        for(int i = advTimeSec; i < playTimeSec; i++){
            totalTime += adverTime[i]; //새로 추가된 값 넣고
            totalTime -= adverTime[i-advTimeSec];// 빠지는 값 뺴주고.


            if(totalTime > maxValue){
                maxValue = totalTime;
                maxStartIndex = i - advTimeSec+1;//시작인덱스 저장.
            }

        }

        return SecToTime(maxStartIndex);
    }

    public static void main(String[] args) {
        카카오공채2022_광고_삽입 tt = new 카카오공채2022_광고_삽입();
        String play_time1 = "02:03:55";
        String adv_time1 = "00:14:15";
        String[] logs1 = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        System.out.println(tt.solution(play_time1,adv_time1,logs1));

        String play_time2 = "99:59:59";
        String adv_time2 = "25:00:00";
        String[] logs2 = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
        System.out.println(tt.solution(play_time2,adv_time2,logs2));

        String play_time3 = "50:00:00";
        String adv_time3 = "50:00:00";
        String[] logs3 = {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"};
        System.out.println(tt.solution(play_time3,adv_time3,logs3));


    }
}
