package ssafy.study;

import java.util.*;

public class OptionalTest {

    static String getStr(){
        return "asdf";
    }

    public static void main(String[] args) {

        Integer[] numArrays = {1,3,2,7,5,8,9,10,4,11};
        List<Integer> numList = new ArrayList<>(Arrays.asList(numArrays));

        if(numList != null){
            numList.sort(Comparator.naturalOrder());
        }

        Optional<String> optional = Optional.of("test");


        Optional<String> optional1 = Optional.ofNullable(getStr());
        String name = optional1.orElse("noop");

    }
}
