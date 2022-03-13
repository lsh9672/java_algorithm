package baek20220308;
/*
* 백준 1181번 (단어정렬, 구현연습)*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;


public class First {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        String[] wordArray = new String[testCase];



        for(int i = 0; i < testCase; i++){
            wordArray[i] = br.readLine();
        }

        Arrays.sort(wordArray, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if(s1.length() == s2.length()){
                    return s1.compareTo(s2);
                }
                else{
                    return s1.length() - s2.length();
                }
            }
        });


        System.out.println(wordArray[0]);

        for(int i = 1; i < testCase; i++){
            if(!wordArray[i].equals(wordArray[i-1])){
                System.out.println(wordArray[i]);
            }
        }
    }
}
