package baek20220403;

/*백준 1051번(구현연습)*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Integer[][] rectangle = new Integer[n][m];

        //숫자 입력
        for(int i =0; i < n ; i++) {
            String temp = br.readLine();
            for(int j = 0; j < m; j++){
                rectangle[i][j] = Character.getNumericValue(temp.charAt(j));
            }
        }

        int result = 1;

        for(int i = 0; i < n; i++){
            for(int j = 0; j<m ; j++){
                int tempValue = rectangle[i][j];
                for(int k = 1; k < m-j; k++){

                    //k만큼 오른쪽으로 이동하고, 아래로 이동했을때, 같으니 확인
                    if((rectangle[i][j+k] == tempValue)&&(i+k < n && rectangle[i+k][j] == tempValue)){
                            //아래 오른쪽이 같으면 대각선도 같은지 확인, 같으면 해당 크기와 이전에 저장된 result값중 큰쪽으로 업데이트
                            if(rectangle[i+k][j+k] == tempValue) {
                                result = Math.max(result, (k + 1) * (k + 1));
                            }

                    }
                }
            }
        }

        System.out.println(result);

    }
}
