package programmers.prog20220912;

import java.util.*;

public class 카카오공채2021_순위_검색 {

    static Map<QueryInfo, List<Integer>> volunteerInfo;

    static int binarySearch(List<Integer> scoreList, int targetScore){

        int start = 0;
        int end = scoreList.size();

        while(start < end){
            int mid = (end + start) / 2;

            if(scoreList.get(mid) < targetScore){
                start = mid +1;

            }
            else{
                end = mid;
            }
        }

        return start;
    }


    static void recursive(int idx, String[] selArray, String[] tempInfo){
        if(idx >= selArray.length){
            QueryInfo temp = new QueryInfo(selArray[0],selArray[1],selArray[2],selArray[3]);
            if(volunteerInfo.containsKey(temp)){
                volunteerInfo.get(temp).add(Integer.parseInt(tempInfo[4]));
            }
            else{
                volunteerInfo.put(temp,new ArrayList<>());
                volunteerInfo.get(temp).add(Integer.parseInt(tempInfo[4]));
            }
            return;
        }

//        for(int i = idx; i < tempInfo.length-1; i++){
//            selArray[count] = tempInfo[i];
//            recursive(i+1,count+1,targetCount,selArray,tempInfo);
//            selArray[count] = "-";
//        }

        selArray[idx] = tempInfo[idx];
        recursive(idx+1,selArray,tempInfo);

        selArray[idx] = "-";
        recursive(idx+1,selArray,tempInfo);

    }

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        volunteerInfo = new HashMap<>();

        //info정보 하나씩 꺼내서 map완성
        for(String tempInfo : info){
            String[] selArray = new String[4];
            Arrays.fill(selArray,"-");

            recursive(0, selArray, tempInfo.split(" "));

        }

        for(QueryInfo q : volunteerInfo.keySet()){
            volunteerInfo.get(q).sort(Comparator.naturalOrder());
        }

        //쿼리에서 하나씩 꺼내기
        for(int i= 0; i < query.length; i++){
            String[] tempQuery = query[i].split("and");

            //음식과 점수 분리
            String[] tempValue = tempQuery[3].trim().split(" ");

            //지도 조회할 키 형식으로 만들기
            QueryInfo keyQuery = new QueryInfo(tempQuery[0].trim(),tempQuery[1].trim(),tempQuery[2].trim(),tempValue[0].trim());

            //기준 점수 - 이 점수 이상인 점수의 수를 뽑아야 됨.
            int stdScore = Integer.parseInt(tempValue[1].trim());

            //해당하는 키의 점수리스트를 뽑아옴 - 개수가 많기 때문에 정렬후 이진 탐색을 이용해야 시간을 줄일 수 있음.
            if(volunteerInfo.containsKey(keyQuery)){
                List<Integer> scoreList = volunteerInfo.get(keyQuery);


                //이진탐색을 돌려서 나보다 작은 점수가 몇개 있는지
                int startIndex = binarySearch(scoreList, stdScore);

                answer[i] = scoreList.size() - startIndex;
            }
            else{
                answer[i] = 0;
            }

        }

        return answer;
    }

    public static void main(String[] args) {
        카카오공채2021_순위_검색 tt = new 카카오공채2021_순위_검색();
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        System.out.println(Arrays.toString(tt.solution(info,query)));
    }

    static class QueryInfo{
        String language, field, career, food;

        QueryInfo(String language, String field, String career, String food){
            this.language = language;
            this.field = field;
            this.career = career;
            this.food = food;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof QueryInfo)) return false;

            QueryInfo queryInfo = (QueryInfo) obj;

            return this.language.equals(queryInfo.language) && this.field.equals(queryInfo.field) &&
                    this.career.equals(queryInfo.career) && this.food.equals(queryInfo.food);
        }

        @Override
        public int hashCode() {
            return Objects.hash(language, field, career, food);
        }

        @Override
        public String toString() {
            return "language : " + this.language + ", field : " + this.field + ", career : " + this.career + ", food : " + food;
        }
    }
}
