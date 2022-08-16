package baekjoon.baek20220725;

/**
 * 백준 2564번 (역량테스트 연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Second {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //가로
        int x = Integer.parseInt(st.nextToken());
        //세로
        int y = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(br.readLine());

        Location[] info = new Location[n];
        for(int i  = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            info[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //동근이의 위치
        st = new StringTokenizer(br.readLine());
        int dongDir = Integer.parseInt(st.nextToken());
        int dongDistance = Integer.parseInt(st.nextToken());

        //최단거리
        int result = 0;

        // 1- 북, 2 - 남쪽, 3 - 서쪽,  4 - 동쪽
        for(Location temp : info){

            int storeDir = temp.dir;
            int storeDistance = temp.distance;

            //동근이의 위치별로 나누기
            //북
            if(dongDir == 1){
                //동근이의 위치와 상점의 위치가 같다면 그대로 계산.
                //북->북
                if(storeDir == 1){
                    result += Math.abs(dongDistance - storeDistance);
                }
                //북 -> 남
                else if(storeDir == 2){
                    //시계방향 이동거리와 반시계 이동거리중 최단 값 저장
                    result += Math.min((x-dongDistance) + y + (x- storeDistance),dongDistance + y + storeDistance);
                }
                //북 -> 서 (이 경우는 무조건 반시계가 빠름)
                else if(storeDir == 3){
                    result += dongDistance + storeDistance;
                }
                //북-> 동 (이 경우는 무조건 시계)
                else{
                    result += (x-dongDistance) + storeDistance;

                }

            }
            //남
            else if(dongDir == 2){
                //남 -> 북
                if(storeDir == 1){
                    //시계방향과 반시계 방향중 최단값.
                    result += Math.min(dongDistance + y + storeDistance,(x-dongDistance) + y + (x- storeDistance));
                }
                //남 -> 남
                else if(storeDir == 2){
                    result += Math.abs(dongDistance - storeDistance);
                }
                //남 -> 서(시계방향)
                else if(storeDir == 3){
                    result += dongDistance + (y - storeDistance);
                }
                //남 -> 동 (반시계)
                else{
                    result += (x - dongDistance) + (y - storeDistance);
                }

            }
            //서쪽
            else if(dongDir == 3){

                //서 -> 북(시계)
                if(storeDir == 1){
                    result += dongDistance + storeDistance;
                }
                //서 -> 남(반시계)
                else if(storeDir == 2){
                    result += (y - dongDistance) + storeDistance;
                }
                //서 -> 서
                else if(storeDir == 3){
                    result += Math.abs(dongDistance - storeDistance);
                }
                //서 -> 동
                else{
                    result += Math.min(dongDistance + x + storeDistance,(y-dongDistance) + x + (y - storeDistance));
                }

            }
            //동쪽
            else{
                // 동 -> 북
                if(storeDir == 1){
                    result += dongDistance + (x - storeDistance);
                }
                //동 -> 남
                else if(storeDir == 2){
                    result += (y - dongDistance) + (x - storeDistance);
                }
                //동 -> 서
                else if(storeDir == 3){
                    result += Math.min((y-dongDistance) + x + (y - storeDistance),dongDistance + x + storeDistance);
                }
                //동 -> 동
                else{
                    result += Math.abs(dongDistance - storeDistance);
                }

            }

        }

        System.out.println(result);


    }

    static class Location{
        int dir;
        int distance;

        public Location(int dir, int distance){
            this.dir = dir;
            this.distance = distance;
        }
    }
}
