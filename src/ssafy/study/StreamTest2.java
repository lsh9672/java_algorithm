package ssafy.study;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StreamTest2 {

    public static void main(String[] args) {


        //학생들 중에 랭크가 상병이고 경력이 3년이상이며, 분야가 백엔드인 사람의 이름을 출력해라.
        List<Student> students = new ArrayList<>();
        students.add(new Student("길민호","상병",3,"Be"));
        students.add(new Student("서준배","상병",5,"fe"));
        students.add(new Student("이상현","상병",3,"Be"));
        students.add(new Student("유영훈","일병",1,"bE"));
        students.add(new Student("길강민","일병",1,"be"));
        students.add(new Student("장별하","이병",0,"Be"));


        System.out.println("외부반복");
        //외부반복을 이용한 방식
        for(Student student : students){
            if(student.rank.equals("상병") && student.career >= 3){
                String temp = student.part;

                if(temp.toUpperCase().equals("BE")){
                    System.out.println(student);
                }
            }
        }

        System.out.println();
        System.out.println("내부반복");
        //람다를 이용한 방식
        students.stream()
                .filter(stud -> stud.rank.equals("상병"))
                .filter(stud -> stud.career >= 3)
                .filter(stud -> stud.part.toUpperCase().equals("BE"))
                .forEach(System.out::println);


    }

    static class Student{
        String name;
        String rank;
        int career;
        String part;

        Student(String name, String rank, int career, String part){
            this.name = name;
            this.rank = rank;
            this.career = career;
            this.part = part;
        }

        @Override
        public String toString() {
            return "name : " + this.name;
        }
    }
}
