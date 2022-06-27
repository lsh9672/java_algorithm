package baek20220601;

/**
 * 백준 10815번 (실버4, 자료구조)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class First {

    static boolean binarySearch(int start,int end,int targetValue,Integer[] numArray){
        int mid = 0;
        while(start <= end){
            mid = (start + end) / 2;

            if(numArray[mid] < targetValue){
                start = mid+1;
            }
            else if(numArray[mid] > targetValue){
                end = mid-1;
            }
            else{
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //가지고 있는 카드
        int n = Integer.parseInt(br.readLine());
        Integer[] numArray = new Integer[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            numArray[i] = Integer.parseInt(st.nextToken());
        }

        //가지고 있는지 확인할 숫자 배열
        int m = Integer.parseInt(br.readLine());
        Integer[] checkArray = new Integer[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            checkArray[i] = Integer.parseInt(st.nextToken());
        }


        //데이터 최대크기가 크기 떄문에 단순 반복문 돌리면 시간초과남
        //따라서 정렬한후에 이분탐색으로 탐색시간을 줄여야 됨
        Arrays.sort(numArray);

        StringBuilder result = new StringBuilder();

        int start = 0;
        int end = n-1;
        for(int i = 0; i < m; i++){
            if(binarySearch(start,end,checkArray[i],numArray) == false){
                result.append(0).append(" ");
            }
            else{
                result.append(1).append(" ");
            }

        }

        result.setLength(result.length()-1);
        System.out.println(result);

        Stack<Integer> test =new Stack<>();
        List<Integer> test2 = new ArrayList<>();
        List<Integer> test3 = new LinkedList<>();
        List<Integer> test6 = new Stack<>();

        Queue<Integer> test4 = new LinkedList<>();
        Queue<Integer> test5 = new ArrayDeque<>();


    }
}
