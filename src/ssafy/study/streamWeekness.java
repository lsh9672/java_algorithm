package ssafy.study;

import java.util.stream.IntStream;

public class streamWeekness {
    public static void main(String[] args) {
        //문제가 되는 케이스
//        IntStream.iterate(0, i -> (i+1) %2)
//                .distinct()
//                .limit(10)
//                .forEach(System.out::println);
//        System.out.println("finish check");

        //정상케이스
        IntStream.iterate(0, i -> (i+1) %2)
                .limit(10)
                .distinct()
                .forEach(System.out::println);
        System.out.println("finish check");

    }
}
