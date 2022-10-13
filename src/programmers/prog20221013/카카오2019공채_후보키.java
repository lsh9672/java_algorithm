package programmers.prog20221013;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author : sh Lee
 * @date : 22. 10. 13.
 */

public class 카카오2019공채_후보키 {

    static int result;

    static List<String>[] columnInfo;
    static List<List<Integer>> uniqueInfo;

    static List<Integer> listCopy(List<Integer> column){
        List<Integer> returnList = new ArrayList<>();
        returnList.addAll(column);
        return returnList;
    }

    //유일성확인
    static boolean check1(List<Integer> column){

        //선택한 컬럼 값들로 배열을 만들고 중복 값이 있는지 확인.
        int rowCount = columnInfo[0].size();

        //묶은 값들을 저장할 2차원 배열 만들기
        String[][] tempArray = new String[rowCount][column.size()];

        //컬럼을 묶어서 배열만들기
        for(int i = 0; i < column.size(); i++){
            for(int j = 0; j < rowCount; j++){
                tempArray[j][i] = columnInfo[column.get(i)].get(j);
            }

//            System.out.println();
        }

        //배열에 중복이 발생하는지 확인
        for(int i = 0; i < rowCount; i++){
            for(int j = 0; j < rowCount; j++){

//                if(i != j){
//                    System.out.println(column);
//                    System.out.println(Arrays.toString(tempArray[i]));
//                    System.out.println(Arrays.toString(tempArray[j]));
//                    System.out.println("---------");
//                }

                //두 배열이 같으면 종료
                if(i != j && Arrays.equals(tempArray[i], tempArray[j])){

                    return false;
                }
            }
        }

//        System.out.println(column.toString());

        return true;
    }

    //최소성 확인
    static int check2(){

        //길이 순으로 정렬
        uniqueInfo.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> integers, List<Integer> t1) {
                return Integer.compare(integers.size(),t1.size());
            }
        });

//        for(List<Integer> temp : uniqueInfo){
//            System.out.println(temp);
//        }


        List<List<Integer>> candidateKey = new ArrayList<>(); // 후보키

        //유일성확인된 키들 하나씩빼서 확인.
        for(List<Integer> temp : uniqueInfo){

            //현재 뽑은 값과 후보키 모음에 있는 값을 비교
            if(temp.size() == 1){
                candidateKey.add(temp);
            }
            else{
//                System.out.println(temp);
                boolean flag = false;
                for(List<Integer> candidate : candidateKey){
//                    System.out.println(candidate);
//                    System.out.println(temp);
//                    System.out.println("------");

                    if(temp.containsAll(candidate)){

                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    candidateKey.add(temp);
                }

            }

        }



        return candidateKey.size();

    }

   static void recursive(int idx, List<Integer> column){

//        System.out.println(column.toString());


        //컬럼을 한개이상 뽑았다면 사용할수 있는지 확인.
        if(column.size() >= 1){
            //유일성을 만족하는 키값 저장.
            if(check1(column)){
                uniqueInfo.add(column);
            }
        }

        if(idx >= columnInfo.length){
            return;
        }

        for(int i = idx; i < columnInfo.length; i++){
            List<Integer> tempColumn = listCopy(column);
            tempColumn.add(i);
            recursive(i+1,tempColumn);
        }

    }

    public int solution(String[][] relation) {
        //컬럼을 인덱스로 함
        columnInfo = new ArrayList[relation[0].length];
        for(int i = 0; i < relation[0].length; i++){
            columnInfo[i] = new ArrayList<>();
        }

        for(String[] row : relation){
            for(int i = 0; i < row.length; i++){
                columnInfo[i].add(row[i]);
            }
        }

        uniqueInfo = new ArrayList<>();

        recursive(0, new ArrayList<>());



        return check2();
    }

    public static void main(String[] args) {
        카카오2019공채_후보키 s = new 카카오2019공채_후보키();

        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        System.out.println(s.solution(relation));
    }
}
