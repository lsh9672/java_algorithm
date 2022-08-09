package baek20220809;

public class Fourth {
    static int N;
    static int M;
    static int result;

    //타겟 게임판
    static int[][] targetMaps;


    // 배열 복사해서 새로운거 리턴하는 함수.
    static int[][] copyArray(int[][] tempMaps){
        int[][] newMaps = new int[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                newMaps[i][j] = tempMaps[i][j];
            }
//			 System.arraycopy(tempMaps[i], 0, newMaps[i], 0, N);
        }

        return newMaps;
    }

    //rowColCheck == 0이면 행, 1이면 열
    static void updateMaps(int[][] tempMaps,int rowColCheck, int idx) {
        // 주어진 값에 해당하는 행 업데이트
        if(rowColCheck == 0) {
            for(int i = 0; i < M; i++) {
                if(tempMaps[idx][i] == 0) {
                    tempMaps[idx][i] = 1;
                }
                else {
                    tempMaps[idx][i] = 0;
                }
            }
        }
        else {
            for(int i = 0; i < N; i++) {
                if(tempMaps[i][idx] == 0) {
                    tempMaps[i][idx] = 1;
                }
                else {
                    tempMaps[i][idx] = 0;
                }
            }
        }
    }

    //타겟과 같은지 확인하는 함수.
    static boolean check(int[][] tempMaps) {

        for(int i = 0; i< N; i++) {
            for(int j = 0; j < M; j++) {
                if(targetMaps[i][j] != tempMaps[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    //행을 뒤집는 경우를 만드는 재귀함수.
    static void recursiveRow(int[][] currentMaps,int rowCount,int idx) {

        //목표배열이라면.
        if(check(currentMaps)) {
            System.out.println("test");
            if(rowCount >= result) return;

            result = Math.min(result, rowCount);

            return;
        }

        if(idx >= N) {
            //컬럼을 선택하는 메서드 호출
            recursiveColumn(currentMaps,rowCount,0);
            return;
        }

        // 0번째 행부터 N-1번째 행까지 선택하는 경우의 수

        //해당 로우를 선택하는 경우 - 배열을 복사후 넘겨줌
        //1. 배열 복사.
        int[][] newMaps = copyArray(currentMaps);
        updateMaps(newMaps,0,idx);
        recursiveRow(newMaps,rowCount+1,idx+1);

        //선택하지 않는 경우.
        recursiveRow(currentMaps,rowCount,idx+1);


    }
    //열을 뒤집는 경우를 하는 재귀함수
    static void recursiveColumn(int[][] currentMaps, int ColumnCount, int idx) {
        if(check(currentMaps)) {
            if(ColumnCount >= result) return;

            result = Math.min(result, ColumnCount);
            return;
        }

        if(idx == M) {
            return;
        }

        //해당 컬럼를 선택하는 경우 - 배열을 복사후 넘겨줌
        //1. 배열 복사.
        int[][] newMaps = copyArray(currentMaps);
        updateMaps(newMaps,1,idx);
        recursiveColumn(newMaps,ColumnCount+1,idx+1);

        //선택하지 않는 경우.
        recursiveColumn(currentMaps,ColumnCount,idx+1);

    }

    public int solution(int[][] beginning, int[][] target) {
        N = beginning.length;
        M = beginning[0].length;

        targetMaps = target;
        result = Integer.MAX_VALUE;

        recursiveRow(beginning,0,0);

        if(result == Integer.MAX_VALUE)
            return -1;

        return result;
    }

    public static void main(String[] args) {
//        Solution s = new Solution();
        int[][] beginning = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
        int[][] target = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};

//        System.out.println(s.solution(beginning,target));
    }
}
