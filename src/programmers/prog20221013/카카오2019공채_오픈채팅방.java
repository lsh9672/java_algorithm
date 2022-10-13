package programmers.prog20221013;

/**
 * @author : sh Lee
 * @date : 22. 10. 13.
 */

import java.util.*;

/**
 * 1. uid를 기준으로 닉네임을 저장한다.
 * 2. 모든 record를 돌면 최종적으로 바뀐 닉네임이 저장된다.
 * 3. 다시 record배열을 돌면서 result형태로 출력한다.
 */

public class 카카오2019공채_오픈채팅방 {

    public String[] solution(String[] record) {

        Map<String,String> nickName = new HashMap<>();//닉네임을 저장할 맵

        //uid를 키값으로 해서 닉네임을 저장
        for(String tempRecord : record) {
            String[] info = tempRecord.split(" "); //문자열정보를 얻기 위해 공백을 기준으로 분리

            if(info.length == 3){
                nickName.put(info[1], info[2]); //맵에 uid:닉네임 형식으로 넣기
            }

        }

        //다시 레코드 정보를 돌면서 uid를 최종 닉네임으로 치환해서 result와 동일한 형태로 만듦
        List<String> result = new ArrayList<>();
        int count = 0;
        for(String tempRecord : record){
            String[] info = tempRecord.split(" ");

            if(info[0].equals("Enter")){
                result.add(nickName.get(info[1]) + "님이 들어왔습니다.");

            }
            else if(info[0].equals("Leave")){
               result.add(nickName.get(info[1]) + "님이 나갔습니다.");
            }
            else continue;

            count++;
        }


        return result.toArray(new String[result.size()]);
    }

    public static void main(String[] args) {
        카카오2019공채_오픈채팅방 s = new 카카오2019공채_오픈채팅방();

        String[] record1 = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        System.out.println(Arrays.toString(s.solution(record1)));



    }
}
