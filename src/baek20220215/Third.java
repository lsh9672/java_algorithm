package baek20220215;


/* 백준 - 스택 10828번(구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Third {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //값을 담을 리스트
        List<Integer> resultList = new ArrayList<>();

        //명령어 수
        int commandCount = Integer.parseInt(br.readLine());

        //공백을 기준으로 입력받기 위해서
        StringTokenizer st = null;

        //명령 입력받기
        for(int i = 0; i < commandCount; i++){
            st = new StringTokenizer(br.readLine());

            //명령어
            String command = st.nextToken();


            //push
            if(st.hasMoreTokens()){
                //명령어와 함꼐오는 정수
                int commandNum = Integer.parseInt(st.nextToken());
                resultList.add(commandNum);
            }

            //push외의 것
            else{
                switch(command){
                    case "pop":
                        if(resultList.isEmpty()){
                            System.out.println(-1);
                        }
                        else{
                            int temp = resultList.get(resultList.size() - 1);
                            resultList.remove(resultList.size() - 1);
                            System.out.println(temp);
                        }
                        break;
                    case "size":
                        System.out.println(resultList.size());
                        break;
                    case "empty":
                        if(resultList.isEmpty()){
                            System.out.println(1);
                        }
                        else{
                            System.out.println(0);
                        }
                        break;
                    case "top":
                        if(resultList.isEmpty()){
                            System.out.println(-1);
                        }
                        else{
                            System.out.println(resultList.get(resultList.size()-1));
                        }
                        break;
                }
            }

        }
    }
}
