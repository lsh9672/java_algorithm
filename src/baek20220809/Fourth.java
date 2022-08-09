package baek20220809;

public class Fourth {
    static int result = Integer.MAX_VALUE;

    static int N;
//    static int[] dx;
//    static int[] dy;
//
    static int[][] maps;
    static int[][] targetMap;

    //타켓인지 확인
    static boolean check(int[][] tmpMap){
        for(int i = 0; i < tmpMap.length; i++){
            for(int j = 0; j < tmpMap[0].length; j++){

                if(tmpMap[i][j] != targetMap[i][j]) return false;
            }
        }
        return true;
    }
    //x가 0이면 열, y가 0이면 행
    static void recursive(int preX, int preY, int[][] nextMaps, int value){
        //현재 뒤집은 횟수가 저장된 횟수보다 크다면 더 갈필요 없음.
        if (value > result){
            return;
        }
        if(check(nextMaps)){
            result = Math.min(result,value);
        }

        //재귀시에 영향을 안끼치도록 복사해서 사용.
        int[][] tempMaps = new int[N][N];
        for(int i = 0; i < N; i++){
            System.arraycopy(nextMaps[i],0,tempMaps[i],0,N);
        }

        for(int i = 1; i <= N; i++){
            //이전에 뒤집었으면 리턴.
            if(preX == 0 && preY == i) return;

            if(preX == i && preY == 0) return;
        }



    }

    public int solution(int[][] beginning, int[][] target) {

        maps = beginning;
        targetMap = target;

        N = beginning.length;

        //각 행과 열에 대해 map길이 만큼 만들어두기.

        recursive();

        if(result == Integer.MAX_VALUE) return -1;
        return result;
    }

    public static void main(String[] args) {
        int[][] beginning = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
        int[][] target = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
        Fourth f  = new Fourth();

        System.out.println(solution(beginning,target));
    }
}
