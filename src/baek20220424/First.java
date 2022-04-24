package baek20220424;

/**
 * 백준 1431번 (시리얼 번호, 실버3, 자바연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;


public class First {

    static int add(String serial_num){
        int total =0;
        for(int i = 0; i < serial_num.length(); i++){
            if(serial_num.charAt(i) >= '0' && serial_num.charAt(i) <= '9'){
                total += serial_num.charAt(i) - '0';
            }
        }

        return total;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());

        String[] serial_num = new String[test_case];

        for (int i = 0; i< test_case; i++){
            serial_num[i] = br.readLine();
        }


        Arrays.sort(serial_num, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {

                if(s1.length() == s2.length()){
                    Integer temp_a = add(s1);
                    Integer temp_b = add(s2);

                    if(temp_a.equals(temp_b)){
                        return s1.compareTo(s2);
                    }
                    else{
                        return temp_a.compareTo(temp_b);
                    }
                }
                else if(s1.length() < s2.length()){
                    return -1;
                }
                else{
                    return 1;
                }
            }
        });

        for (String s : serial_num) {
            System.out.println(s);
        }

    }
}
