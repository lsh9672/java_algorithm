package ssafy.algorism.mst;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 2022 08 22 - mst수업 - 크루스칼
 */

public class KruskalTest {

    static int[] parents;
    static int V,E;
    static Edge[] edgeList;

    //크기가 1인 서로소 집합 생성 - 모든 노드가 자신을 부모로 함.
    static void make(){
        parents = new int[V];

        for(int i = 0; i < V; i++){
            parents[i] = i;
        }
    }

    static int find(int a){
        if(parents[a] == a){
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("src/ssafy/algorism/mst/kruskal_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            edgeList[i] = new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        make();
        Arrays.sort(edgeList);

        int result = 0;
        int count = 0;
        for(Edge edge : edgeList){
            if(union(edge.from, edge.to)){
                result += edge.weight;
                if(++count == V-1) break;
            }
        }

        System.out.println(result);
    }

    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from,int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
//            return Integer.compare(this.weight,edge.weight);
            return this.weight - edge.weight;
        }
    }
}
