package baek20220723;

/**
 * 백준 3985번 (월말평가 대비)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int[] cakes = new int[L+1];

        int expectSize = -1;
        int expectMember = 0;

        int[] memberCount = new int[n+1];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int startLoc = Integer.parseInt(st.nextToken());
            int endLoc = Integer.parseInt(st.nextToken());

            //예상되는 값 미리 구해두기
            if(expectSize < endLoc - startLoc){
                expectSize = endLoc-startLoc;
                expectMember = i+1;
            }

            //주어진 종이의 값을 하나씩 열어보면서 배열에 입력하고, 그 수만큼 카운트 해두기
            for(int j = startLoc; j <= endLoc ; j++){
                if(cakes[j] == 0){
                    cakes[j] = i+1;
                    memberCount[i+1]++;

                }
            }

        }

        //각 사용자별로 얻을 수 있는 케이크의 크기를 저장해둔 리스트를 돌면서 최대값에 해당하는 사용자 출력함
        int maxValue = -1;
        int result = 0;
        for(int i = 1; i < n+1; i++){
            if(maxValue < memberCount[i]){
                result = i;
                maxValue = memberCount[i];
            }

        }

        System.out.println(expectMember);
        System.out.println(result);
    }
}
