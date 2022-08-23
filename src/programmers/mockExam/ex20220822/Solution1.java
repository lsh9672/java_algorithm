package programmers.mockExam.ex20220822;

public class Solution1 {

    static int emptyBottle;

    static int recursive(int a,int b, int value){

        if(emptyBottle < a){
            return value;
        }

        int temp = (emptyBottle / a) * b;
        emptyBottle = emptyBottle - (emptyBottle / a) * a + temp;
        return recursive(a,b,value + temp);
    }

    public int solution(int a, int b, int n) {
        int answer = 0;
        emptyBottle = n;

        // 시작 빈병
        answer = recursive(a,b,0);


        return answer;
    }

    public static void main(String[] args) {
        Solution1 s = new Solution1();

        int a = 2;
        int b = 1;
        int n = 20;

        System.out.println(s.solution(a,b,n));

        a = 3;
        b = 1;
        n = 20;
        System.out.println(s.solution(a,b,n));
    }

}
