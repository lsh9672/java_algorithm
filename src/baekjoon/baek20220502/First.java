package baekjoon.baek20220502;

/**
 * 백준 20920번 (자바 자료구조 연습)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class First {
    static class Word implements Comparable<Word>{
        String key;
        int value;

        public Word(String key,int value){
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(Word word) {
            if (this.value > word.getValue()){
                return -1;
            }
            else if(this.value == word.getValue()){
                if(this.key.length() > word.getKey().length()){
                    return -1;
                }
                else if(this.key.length() == word.getKey().length()){
                    return this.key.compareTo(word.getKey());
                }
                else{
                    return 1;
                }
            }
            else{
                return 1;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String,Integer> words = new HashMap<>();

        for(int i = 0; i < n; i++){
            String temp = br.readLine();
            if(temp.length() >=m){
                if (words.containsKey(temp)){
                    words.put(temp,words.get(temp)+1);
                }
                else{
                    words.put(temp,1);
                }
            }
        }

        List<Word> sortList = new ArrayList<>();

        for(String s : words.keySet()){
            sortList.add(new Word(s,words.get(s)));
        }

        Collections.sort(sortList);
        StringBuilder sb = new StringBuilder();
        for (Word word : sortList) {
            sb.append(word.getKey()).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }
}
