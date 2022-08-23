package programmers.mockExam.ex20220822;


import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    public int solution(int[] ingredient) {
        int answer = 0;

        List<Integer> ham = new ArrayList<>();

        StringBuilder sb = null;

        for(int i = 0; i < ingredient.length; i++){
            int temp = ingredient[i];

            //빵이라면 햄버거를 만들수 있는지 확인.
            if(temp == 1){

                //현재 길이가 2 이하라면 그냥 넣음
                if(ham.size() <= 2){
                    ham.add(temp);
                }
                //내부 길이가 3이상이라면 햄버거가 만들어질 수도 있음.
                else{
                    sb = new StringBuilder();
                    for(int k = ham.size()-1; k >= ham.size()-3; k--){
                        sb.append(ham.get(k));
                    }
                    if(sb.toString().equals("321")){
                        answer++;
                        for(int t = 0; t < 3; t++){
                            ham.remove(ham.size()-1);
                        }
                    }
                    else{
                        ham.add(temp);
                    }
                }
            }
            //빵이 아닌 나머지 재료는 그냥 넣음
            else{
                ham.add(temp);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();

        int[] ingredient = {2, 1, 1, 2, 3, 1, 2, 3, 1};
        System.out.println(s.solution(ingredient));

        int[] ingredient2 = {1, 3, 2, 1, 2, 1, 3, 1, 2};
        System.out.println(s.solution(ingredient2));
    }
}
