package programmers.prog20230723;

import java.util.Arrays;

public class 연속된_부분_수열의_합 {

    public int[] solution(int[] sequence, int k) {

        int[] answer = new int[2];
        int minSize = 1000001; //최소 윈도우사이즈를 구해야 됨.

        int windowSize = 1; //최소 윈도우 크기

        int startIndex = 0;
        int endIndex = startIndex + windowSize;

        int windowSum = sequence[startIndex]; //초기 윈도우안의 수의 합.

        //윈도우의 오른쪽 끝 인덱스가 배열의 크기보다 크지 않을때 까지 반복
        while(endIndex <= sequence.length){

            //윈도우안 수의 합이 k이면, 기존에 저장중인것과 비교
            //앞부분부터 돌기 때문에, 배열의 뒤쪽에 수가 더 큰 것이 있을 수도 있음.
            //윈도우의 사이즈는 그대로 유지한 상태로 한칸이동 해야됨.
            if(k == windowSum){

                //목표 합계에 도달하면 이전에 구한 윈도우 사이즈랑 비교

                //이전에 구한 윈도우 사이즈가 더 크다면, 새로운 것으로 업데이트
                if(minSize > windowSize){
                    answer[0] = startIndex;
                    answer[1] = endIndex - 1;

                    minSize = windowSize;
                }
                //윈도우 크기를 한칸 줄임 - 단 윈도우 길이가 1보다 크다면, 한칸 줄이는 식으로 처리.
                //윈도우 크기를 동일하게 해서 움직이는 것은 의미가 없음
                //크기가 같다면, 시작인덱스가 앞서는 것이 답이므로, 한칸 줄여서 탐색해봄.

                //이동시켜야되는데, endIndex위치가 sequence배열의 길이라면, 더 이동 불가.
                //if(endIndex >= sequence.length) break;

                startIndex++;
                windowSize--;

                windowSum -= sequence[startIndex - 1];

            }

            //윈도우 안 수의 합이 k보다 작을때, windowSize값을 하나 증가시켜서 윈도우 크기 증가.
            else if(k > windowSum){

                //이동시켜야되는데, endIndex위치가 sequence배열의 길이라면, 더 이동 불가.
                if(endIndex >= sequence.length) break;

                windowSize++;
                endIndex = startIndex + windowSize; // 윈도우 크기증가에 맞춰 오른쪽 끝인덱스 증가.
                windowSum += sequence[endIndex - 1]; //윈도우 안 수의 합 증가
            }

            //윈도우 안 수의 합이 k보다 클때, startIndex값을 하나 증가시켜서 윈도우 이동,
            else{


                startIndex++; // 시작 인덱스 증가.
                windowSize--; //윈도우 크기 감소
                windowSum -= sequence[startIndex - 1];
            }
        }

        return answer;
    }

    public static void main(String[] args){
        연속된_부분_수열의_합 s = new 연속된_부분_수열의_합();

        int[] sequence = new int[]{10,9,8,7,6,5};
        int k = 5;

        System.out.println(Arrays.toString(s.solution(sequence,k)));
    }

}
