package baek20220329;

/* 백준 14426번 (자바 문자열 연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*set을 이용한풀이*/
//public class First {
//    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int n = Integer.parseInt(st.nextToken());
//        int m = Integer.parseInt(st.nextToken());
//
//
//        //입력받은 n개의 문자로 접두사 만들어 저장
//        Set<String> prefixCollection = new HashSet<>();
//
//        int result = 0;
//
//        for(int i = 0; i <n ; i++){
//            String temp = br.readLine();
//            for(int j = 1; j <= temp.length(); j++){
//                prefixCollection.add(temp.substring(0,j));
//            }
//        }
//        for(int i = 0; i <m ; i++) {
//            String temp = br.readLine();
//
//            if(prefixCollection.contains(temp)){
//                result ++;
//            }
//        }
//        System.out.println(result);
//    }
//
//}

/*startWiths를 이용한 풀이*/
public class First {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] nInputValue = new String[n];

        int result = 0;

        for(int i = 0; i <n ; i++){
            nInputValue[i] = br.readLine();
        }
        for(int i = 0; i <m ; i++) {
            String temp = br.readLine();

            for(String k : nInputValue){
                if(k.startsWith(temp)){
                    result++;
                    break;
                }
            }
        }
        System.out.println(result);
    }

}