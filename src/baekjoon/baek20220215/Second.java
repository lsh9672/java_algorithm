package baekjoon.baek20220215;

/*
* 백준 - 1251번 단어나누기*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;


public class Second {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputWord = br.readLine();

        //생성된 문자열 추가할 리스트 - 몇개가 생길지 모르기때문에 가변형인 리스트 사용
        ArrayList<String> resultList = new ArrayList<>();


        //쪼갠단어 임시저장(뒤집어야 되므로)
        String[] temp = new String[3];
        for(int i = 1; i < inputWord.length()-1;i++){
            for(int j = i+1; j<inputWord.length();j++){

                temp[0] = inputWord.substring(0,i);
                temp[1] = inputWord.substring(i,j);
                temp[2] = inputWord.substring(j);

                StringBuffer sb = new StringBuffer();

                //문자열 뒤집기
                for(int x = 0; x < 3;x++){
                    String tempString = temp[x];
                    for(int y = tempString.length()-1;y>=0; y--){
                        sb.append(tempString.charAt(y));
                    }
                }

                resultList.add(sb.toString());

                //스트링버퍼 초기화시 setLength(0)으로 초기화하는 것이 가장 빠르다고 한다.
                sb.setLength(0);
            }
        }

        //리스트 정렬후 첫번쨰 값 출력 - 아래의 리스트에서 바로 sort메서드 호출방식은 자바8이상
        resultList.sort(Comparator.naturalOrder());

        System.out.println(resultList.get(0));
    }
}
