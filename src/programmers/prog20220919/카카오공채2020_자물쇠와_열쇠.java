package programmers.prog20220919;


public class 카카오공채2020_자물쇠와_열쇠 {

    static int N; //열쇠
    static int M; //자물쇠
    static int mapsLen;
    static int[][] maps;

    //모든 자물쇠 칸이 딱 맞게 되었는 지 확인 - 전부 1이어야 됨.
    static boolean lockCheck(){
        for(int i = N-1; i < M+N-1; i++){
            for(int j = N-1 ; j < M+N-1; j++){
                if(maps[i][j] == 0 || maps[i][j] == 2){
                    return false;
                }
            }
        }
        return true;
    }


    //lock 부분 초기화.
    static void initMap(int[][] lock){
        for(int i = N-1; i < M+N-1; i++){
            for(int j = N-1 ; j < M+N-1; j++){
                maps[i][j] = lock[i - (N-1)][j - (N-1)];
            }
        }

    }

    //배열 90도 회전
    static int[][] rotateArray(int[][] originArray){
        int[][] returnArray = new int[originArray.length][originArray[0].length];

        for(int i = 0; i < originArray.length; i++){
            for(int j = 0; j < originArray.length; j++){
                returnArray[i][j] = originArray[originArray.length -1 - j][i];
            }
        }

        return returnArray;

    }

    //한칸씩이동하면서 확인
    static boolean moveAndCheck(int[][] lock, int[][] key){
        for(int i = 0; i < mapsLen - (N-1); i++){
            for(int j = 0; j < mapsLen - (N-1); j++){
                //키를 더하기 전에 lock영역 초기화
                initMap(lock);

                for(int x = 0; x < N; x++){
                    for(int y = 0; y < N; y++){
                        maps[x+i][y+j] += key[x][y];
                    }
                }

                //key를 전부 더했으면 확인.
                //확인 결과 모든 홈을 채울 수 있다면, 종료
                if(lockCheck()){
                    return true;
                }

            }
        }

        return false;

    }

    public boolean solution(int[][] key, int[][] lock) {

        N = key.length;
        M = lock.length;

        mapsLen = lock.length + (N-1) *2;

        //연산을 위해 크기를 늘린 새로운 배열을 만듦 - 크기는 lock.length + (N-1)*2
        maps = new int[mapsLen][mapsLen];


        //0,90,180,270도에 대해서 각각 하나씩 이동시키면서 lock과 맞춰봄
        for(int i = 0; i < 4; i++){

            //한칸씩 이동시키면서 lock과 대조하는 로직
            if(moveAndCheck(lock, key)){
                return true;
            }

            //회전
            key = rotateArray(key);

        }

        return false;
    }

    public static void main(String[] args) {
        카카오공채2020_자물쇠와_열쇠 tt = new 카카오공채2020_자물쇠와_열쇠();

        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        System.out.println(tt.solution(key,lock));
    }
}
