package ssafy.study;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreamAndLambda {

    public static void main(String[] args) {


//        String[] strArray1 = {"ssafy1","ssafy2","ssafy3"};
//        List<String> strArray2 = Arrays.asList(strArray1);
//
//        Map<String,Integer> testMap = new HashMap<>();
//        testMap.put("ssafy1",1);
//        testMap.put("ssafy2",2);
//        testMap.put("ssafy3",3);
//
//        Stream<String> stream1 = Arrays.stream(strArray1);
//        Stream<String> stream2 = strArray2.stream();
//
//        List<String> collect = stream2.sorted().collect(Collectors.toList());
//        stream2.filter(str-> str.length() == 4).forEach(System.out::println);

//        Stream<Map.Entry<String, Integer>> stream3 = testMap.entrySet().stream();
//
//
        Integer[] testArray = {1,5,6,3,7,8};
        List<Integer> collect = Arrays.stream(testArray).sorted().collect(Collectors.toList());

        Arrays.stream(testArray).parallel();

//
//        System.out.println(Arrays.toString(testArray));
//        System.out.println(collect);


        List<String> ssafy = List.of("ssafy1", "ssafy2", "ssafy3", "ssafy4");
        ssafy.stream().parallel();
        Stream<String> stringStream = ssafy.parallelStream();

//        ssafy.stream()
//                .filter(str -> {
//                    System.out.println("first filter = " + str);
//                    return str.length() > 3;
//                })
//                .filter(str -> {
//                    System.out.println("second filter = " + str);
//                    return !str.contains("4");
//                })
//                .map(str -> {
//                    System.out.println("map = " + str);
//                    return str.toUpperCase();
//                })
//                .forEach(str -> {
//                    System.out.println("forEach = " + str + "'\n");
//                });

        IntStream.range(0,10_000_000)
                .limit(5)
                .map(i -> i + 100)
                .forEach(i -> System.out.println("result = " + i));



    }

}
