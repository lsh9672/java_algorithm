package baek20220805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Second {

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] result = new int[N];

        // 타워정보
        TowerInfo[] towerArrays = new TowerInfo[N];
        for(int i = 0; i < N; i++) {
            towerArrays[i] = new TowerInfo(i+1,Integer.parseInt(st.nextToken()));
        }
        //스택에 오른쪽부터 넣어서 , 새로운 값을 스택에 넣으려고 할때 이미 들어가 있는 값이 넣으려고 하는 값보다 작으면 들어가있는 값을 전부 뺌.
        Stack<TowerInfo> towerStack = new Stack<>();
        for(int i = towerArrays.length -1; i >= 0; i--) {
            TowerInfo tmpTowerInfo = towerArrays[i];

            // 스택이 비었다면 스택에 넣기
            if(towerStack.isEmpty()) {
                towerStack.add(tmpTowerInfo);
            }
            //스택이 비어있지 않다면 가장위의 값과 비교
            else {
                // 스택 최상단의 탑의 높이보다 작다면 스택에 넣음
//				if(tmpTowerInfo.height < towerStack.peek().height) {
//					towerStack.add(tmpTowerInfo);
//				}

                // 현재 탑의 높이와 스택안에 들어있는 탑의 높이 - 높이가 서로 다르다고 했기 때문에 같은 경우는 존재 하지 않음
                while(!towerStack.isEmpty() && tmpTowerInfo.height > towerStack.peek().height) {
                    // 현재 높이보다 작은거 다빼서 결과에 추가.
                    TowerInfo temp = towerStack.pop();
                    result[temp.idx-1] = tmpTowerInfo.idx;

                }
                towerStack.add(tmpTowerInfo);


            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }

        sb.setLength(sb.length()-1);
        System.out.println(sb);

    }

    static class TowerInfo{
        int idx;
        int height;

        TowerInfo(int idx, int height){
            this.idx = idx;
            this.height = height;
        }
    }

}
