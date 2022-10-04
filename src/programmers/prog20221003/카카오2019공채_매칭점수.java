package programmers.prog20221003;

import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 카카오2019공채_매칭점수 {
    public int solution(String word, String[] pages) {
        int answer = 0;

        //html 내에 해당 키워드가 몇번 등장하는지 체크하기 위해서 알파벳 대소문자만 매칭
        String keyWordPattern = "[a-zA-Z]+"; //한번이상 등장하는 알파벳 매칭.

        //내 url이 뭔지 찾기
        // () => 이 안의 문자를 하나로 인식한다.
        String myUrlPattern = "<meta property=\"og:url\" content=\"(\\S+)\"/>"; //공백이 아닌 문자가 1개이상 매칭되는지
        //외부 링크 찾기
        String outLink = "<a href=\"(\\S)+\">";

        HashMap<String,PageInfo> pageMap = new HashMap<>();

        for(int i = 0; i < pages.length; i++){
            String page = pages[i];

            //현재 자신의 url 구하기
            Pattern pattern = Pattern.compile(myUrlPattern);
            Matcher matcher = pattern.matcher(pages[i]);
            String url = "";
            if(matcher.find()){
                url = matcher.group().split("=")[2].replace("\"","").replace("/>","");
            }

            //기본점수 구하기 - 단어의 수
            pattern = Pattern.compile(keyWordPattern);
            matcher = pattern.matcher(pages[i]);
            int defaultScore = 0;

            while(matcher.find()){
                if(word.toUpperCase().equals(matcher.group().toUpperCase())) defaultScore++;
            }

            //외부 링크 url저장.
            List<String> externalLinkList = new ArrayList<>();

            pattern = Pattern.compile(outLink);
            matcher = pattern.matcher(pages[i]);

            while(matcher.find()){
                String temp = matcher.group().split("=")[1].replace("\"","").replace(">","");
                externalLinkList.add(temp);
            }

            //페이지 객체 생성
            PageInfo pageInfo = new PageInfo(i, url, defaultScore, externalLinkList);

            //해쉬맵에 키는 url, value는 객체로 해서 저장
            //외부링크를 확인할때 현재 있는 페이지로 외부링크를 하는지 확인하기 위해서.
            pageMap.put(pageInfo.url, pageInfo);

        }

        //맵에 넣은 url 하나씩 꺼내서 외부링크 검사.
        for(PageInfo tempPage : pageMap.values()){

            for(String tempUrl : tempPage.externalUrl){
                //해당 외부 링크가 존재하는 지 확인
                if(pageMap.containsKey(tempUrl)){
                    //존재한다면 해당 위치로 링크점수 더하기
                    pageMap.get(tempUrl).totalScore += tempPage.linkScore;
                }
            }
        }

        double maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;
        for(PageInfo tempPage : pageMap.values()){
//            System.out.println("url : " + tempPage.url);
//            System.out.println("index : " + tempPage.index);
//            System.out.println("defaultScore : "+tempPage.defaultScore);
//            System.out.println("linkScore : "+tempPage.linkScore);
//            System.out.println("totalScore : "+ tempPage.totalScore);
//            System.out.println("--------------");
            if(tempPage.totalScore > maxValue){
                maxValue = tempPage.totalScore;
                maxIndex = tempPage.index;;
            }

            else if(tempPage.totalScore == maxValue){
                if(maxIndex > tempPage.index){
                    maxIndex = tempPage.index;
                }
            }

        }

//        System.out.println(maxIndex);


        return maxIndex;
    }

    public static void main(String[] args) {
        카카오2019공채_매칭점수 s = new 카카오2019공채_매칭점수();
//
        String word1 = "blind";
        String[] pages1 = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        System.out.println(s.solution(word1,pages1));

        String word2 = "Muzi";
        String[] pages2 = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
        System.out.println(s.solution(word2,pages2));

    }

    static class PageInfo{
        int index;
        String url;
        int defaultScore; //기본점수와 외부 링크 수.
        List<String> externalUrl;
        double linkScore; //링크 점수
        double totalScore;


       PageInfo(int index, String url, int defaultScore, List<String> externalUrl){
            this.index = index;
            this.url = url;
            this.defaultScore = defaultScore;
            this.externalUrl = externalUrl;
            this.linkScore = (double)defaultScore / (double)externalUrl.size();
            this.totalScore = defaultScore;
        }
    }

}
