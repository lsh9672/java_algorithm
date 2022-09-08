package baekjoon.baek20220908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1062_가르침 {

    static int N;
    static int K;
    static int result;

    static boolean[] visited; //알파벳 방문 여부.
    static String[] searchStr; //탐색할 문자열 저장.

    //선택한 알파벳으로 몇개의 단어를 읽을수 있는지 확인.
    static int checkStr(){
        int total = 0;

        boolean flag = false;
        for(String temp : searchStr){

            flag = false;

            for(int i = 0; i < temp.length(); i++){
                //해당 알파벳을 방문하지 않았다면
                if(!visited[temp.charAt(i)-'a']){
                    flag = true;
                    break;
                }
            }

            //중간에 브레이크 되지 않았다면 읽을 수 있는 단어임.
            if(!flag) total++;
        }

        return total;
    }

    static void recursive(int idx, int count){

        //단어를 K개만큼 뽑았다면(5개는 고정이라 -5함.)
        if(count >= K - 5){
            result = Math.max(result,checkStr());
            return;
        }
        /*아래의 조건을 넣게 되면 매번 문자열 비교를 해서 시간이 4배정도 느림*/
        //개수에 도달하지 않았는데 check해본 결과 문자열 개수만큼 읽었다면 더 탐색할 필요 없음
//        else{
//            if(checkStr() == N){
//                result = N;
//                return;
//            }
//        }

        for(int i = idx; i < 26; i++){

            //방문하지 않은 알파벳이라면
            if(!visited[i]){
                visited[i] = true;
                recursive(i+1,count+1);
                visited[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        result = Integer.MIN_VALUE;

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[26];
        searchStr = new String[N];

        for(int i = 0; i < N; i++){
            String temp = br.readLine();
            //반복되는 단어는 볼 필요 없음.
            searchStr[i] = temp.substring(4,temp.length() - 4);
        }

        //반복되는 단어는 방문처리를 함. - 총 5개, a n t i c
        visited[0] = true;
        visited['n'-'a'] = true;
        visited['t'-'a'] = true;
        visited['i'-'a'] = true;
        visited['c'-'a'] = true;

        //만약 k가 5개미만이라면 고정적으로 나오는 단어도 처리가 불가능
        if(K < 5) System.out.println(0);
        //K가 26이상이라면 모든 알파벳을 다 배울수 있어서 모든 단어 읽을 수있음
        else if(K >= 26) System.out.println(N);

        else{
            recursive(0,0);
            System.out.println(result);
        }
    }
}
