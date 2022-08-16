package baekjoon.baek20220403;


/*백준 11866번(구현연습)*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class First {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        //매번 노드를 삭제해야되서 링크드 리스트를 이용해보았는데, 근소하게 ArrayList가 더 빠름
        // 노드 삭제 비용 > 랜덤엑세스(링크드리스트는 인덱스로 접근하려면 앞에서부터 순차적으로 접근해야됨.)
//        List<Integer> people = new ArrayList<>();
        List<Integer> people = new LinkedList<>();
        //사람 리스트에 채우기
        for(int i = 0; i < n; i++){
            people.add(i+1);
        }
        int prevIndex = 0;
        //리스트 사이즈가 크거나 같을때 까지
        while (people.size() != 0){
            prevIndex = (prevIndex+k-1) % people.size();
            if(people.size() == 1){
                sb.append(people.get(prevIndex)).append(">");
            }
            else{
                sb.append(people.get(prevIndex)).append(",").append(" ");
            }

            people.remove(prevIndex);
        }
        System.out.println(sb);

    }
}
