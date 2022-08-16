package baekjoon.baek20220426;

/**
 * 백준 19583번 (자료구조 연습, 자바, 실버2)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class First {

    //문자열 시간을 계산하기 편하게 전부 분으로 바꾸는 함수
    static int timeToMinute(String strTime){
        int resultTime = 0;
        String[] tempTime = strTime.split(":");
        resultTime = (Integer.parseInt(tempTime[0]) * 60) + Integer.parseInt(tempTime[1]);

        return resultTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int meetingStart = timeToMinute(st.nextToken());
        int meetingEnd = timeToMinute(st.nextToken());
        int streamingEnd = timeToMinute(st.nextToken());

        //개강총회 시작시간까지 온사람
        Set<String> enterMeeting = new HashSet<>();

        //개강총회 끝나고, 스트림 끝날떄까지 채팅 친사람
        Set<String> outMeeting = new HashSet<>();

        while(true){
            String temp = br.readLine();

            if(temp == null || temp.equals("")){
                break;
            }

            st = new StringTokenizer(temp);

            int chatTime = timeToMinute(st.nextToken());

            String participant = st.nextToken();

            //총회시작시점까지 채팅쳤으면 입장확인
            if(meetingStart >= chatTime){
                enterMeeting.add(participant);
            }

            else if(meetingEnd <= chatTime && chatTime <= streamingEnd){
                outMeeting.add(participant);
            }
        }
        int result = 0;
        for (String s : enterMeeting) {
            if(outMeeting.contains(s)){
                result++;
            }
        }

        System.out.println(result);
    }
}
