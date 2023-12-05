package com.example.wetro.dijkstra;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Component
public class NodeT implements Comparable<NodeT> {
    //호선
    private String line;
    //노드 이름
    private String name;
    //현재까지의 최단 거리 초기값 무한
    private Integer cost = Integer.MAX_VALUE;
    private Integer time = Integer.MAX_VALUE;
    //최단 경로 저장, 수정 용이하게 위해 링크드리스트
    private List<NodeT> shortestPath = new LinkedList<>();
    //해당 노드의 인접한 노드와 그 사이의 가중치를 저장
    private Map<NodeT, List<Integer>> adjacentNodes = new HashMap<>();
    //환승 횟수
    private int transferCount = 0;

    //일반역 리스트
    private static List<NodeT> nodes = new ArrayList<>();
    //환승역 리스트
    private static List<NodeT> transNodes = new ArrayList<>();

    public NodeT(String Line, String name) {
        this.line = Line;
        this.name = name;
    }

    //노드 선언 및 초기화
    public static void initT() {
        initNodeList();
        NodeT node1 = new NodeT("1", "101");
        NodeT node2 = new NodeT("1", "102");
        NodeT node3 = new NodeT("1", "103");
        NodeT node4 = new NodeT("1", "104");
        NodeT node5 = new NodeT("1", "105");
        NodeT node6 = new NodeT("1", "106");
        NodeT node7 = new NodeT("1", "107");
        NodeT node8 = new NodeT("1", "108");
        NodeT node9 = new NodeT("1", "109");
        NodeT node10 = new NodeT("1", "110");
        NodeT node11 = new NodeT("1", "111");
        NodeT node12 = new NodeT("1", "112");
        NodeT node13 = new NodeT("1", "113");
        NodeT node14 = new NodeT("1", "114");
        NodeT node15 = new NodeT("1", "115");
        NodeT node16 = new NodeT("1", "116");
        NodeT node17 = new NodeT("1", "117");
        NodeT node18 = new NodeT("1", "118");
        NodeT node19 = new NodeT("1", "119");
        NodeT node20 = new NodeT("1", "120");
        NodeT node21 = new NodeT("1", "121");
        NodeT node22 = new NodeT("1", "122");
        NodeT node23 = new NodeT("1", "123");

        NodeT node24 = new NodeT("2", "201");
        NodeT node25 = new NodeT("2", "202");
        NodeT node26 = new NodeT("2", "203");
        NodeT node27 = new NodeT("2", "204");
        NodeT node28 = new NodeT("2", "205");
        NodeT node29 = new NodeT("2", "206");
        NodeT node30 = new NodeT("2", "207");
        NodeT node31 = new NodeT("2", "208");
        NodeT node32 = new NodeT("2", "209");
        NodeT node33 = new NodeT("2", "210");
        NodeT node34 = new NodeT("2", "211");
        NodeT node35 = new NodeT("2", "212");
        NodeT node36 = new NodeT("2", "213");
        NodeT node37 = new NodeT("2", "214");
        NodeT node38 = new NodeT("2", "215");
        NodeT node39 = new NodeT("2", "216");
        NodeT node40 = new NodeT("2", "217");
        NodeT node41 = new NodeT("2", "101");

        NodeT node42 = new NodeT("3", "301");
        NodeT node43 = new NodeT("3", "302");
        NodeT node44 = new NodeT("3", "303");
        NodeT node45 = new NodeT("3", "304");
        NodeT node46 = new NodeT("3", "305");
        NodeT node47 = new NodeT("3", "306");
        NodeT node48 = new NodeT("3", "307");
        NodeT node49 = new NodeT("3", "308");
        NodeT node50 = new NodeT("3", "207");
        NodeT node51 = new NodeT("3", "123");
        NodeT node52 = new NodeT("3", "107");

        NodeT node53 = new NodeT("4", "401");
        NodeT node54 = new NodeT("4", "402");
        NodeT node55 = new NodeT("4", "403");
        NodeT node56 = new NodeT("4", "404");
        NodeT node57 = new NodeT("4", "405");
        NodeT node58 = new NodeT("4", "406");
        NodeT node59 = new NodeT("4", "407");
        NodeT node60 = new NodeT("4", "408");
        NodeT node61 = new NodeT("4", "409");
        NodeT node62 = new NodeT("4", "410");
        NodeT node63 = new NodeT("4", "411");
        NodeT node64 = new NodeT("4", "412");
        NodeT node65 = new NodeT("4", "413");
        NodeT node66 = new NodeT("4", "414");
        NodeT node67 = new NodeT("4", "415");
        NodeT node68 = new NodeT("4", "416");
        NodeT node69 = new NodeT("4", "417");
        NodeT node70 = new NodeT("4", "104");
        NodeT node71 = new NodeT("4", "307");
        NodeT node72 = new NodeT("4", "115");
        NodeT node73 = new NodeT("4", "216");

        NodeT node74 = new NodeT("5", "501");
        NodeT node75 = new NodeT("5", "502");
        NodeT node76 = new NodeT("5", "503");
        NodeT node77 = new NodeT("5", "504");
        NodeT node78 = new NodeT("5", "505");
        NodeT node79 = new NodeT("5", "506");
        NodeT node80 = new NodeT("5", "507");
        NodeT node81 = new NodeT("5", "209");
        NodeT node82 = new NodeT("5", "122");
        NodeT node83 = new NodeT("5", "403");
        NodeT node84 = new NodeT("5", "109");

        NodeT node85 = new NodeT("6", "601");
        NodeT node86 = new NodeT("6", "602");
        NodeT node87 = new NodeT("6", "603");
        NodeT node88 = new NodeT("6", "604");
        NodeT node89 = new NodeT("6", "605");
        NodeT node90 = new NodeT("6", "606");
        NodeT node91 = new NodeT("6", "607");
        NodeT node92 = new NodeT("6", "608");
        NodeT node93 = new NodeT("6", "609");
        NodeT node94 = new NodeT("6", "610");
        NodeT node95 = new NodeT("6", "611");
        NodeT node96 = new NodeT("6", "612");
        NodeT node97 = new NodeT("6", "613");
        NodeT node98 = new NodeT("6", "614");
        NodeT node99 = new NodeT("6", "615");
        NodeT node100 = new NodeT("6", "616");
        NodeT node101 = new NodeT("6", "617");
        NodeT node102 = new NodeT("6", "618");
        NodeT node103 = new NodeT("6", "619");
        NodeT node104 = new NodeT("6", "620");
        NodeT node105 = new NodeT("6", "621");
        NodeT node106 = new NodeT("6", "622");
        NodeT node107 = new NodeT("6", "121");
        NodeT node108 = new NodeT("6", "116");
        NodeT node109 = new NodeT("6", "412");
        NodeT node110 = new NodeT("6", "417");

        NodeT node111 = new NodeT("7", "701");
        NodeT node112 = new NodeT("7", "702");
        NodeT node113 = new NodeT("7", "703");
        NodeT node114 = new NodeT("7", "704");
        NodeT node115 = new NodeT("7", "705");
        NodeT node116 = new NodeT("7", "706");
        NodeT node117 = new NodeT("7", "707");
        NodeT node118 = new NodeT("7", "202");
        NodeT node119 = new NodeT("7", "303");
        NodeT node120 = new NodeT("7", "503");
        NodeT node121 = new NodeT("7", "601");
        NodeT node122 = new NodeT("7", "416");
        NodeT node123 = new NodeT("7", "614");

        NodeT node124 = new NodeT("8", "801");
        NodeT node125 = new NodeT("8", "802");
        NodeT node126 = new NodeT("8", "803");
        NodeT node127 = new NodeT("8", "804");
        NodeT node128 = new NodeT("8", "805");
        NodeT node129 = new NodeT("8", "806");
        NodeT node130 = new NodeT("8", "409");
        NodeT node131 = new NodeT("8", "608");
        NodeT node132 = new NodeT("8", "705");
        NodeT node133 = new NodeT("8", "618");
        NodeT node134 = new NodeT("8", "214");
        NodeT node146 = new NodeT("8", "113");

        NodeT node135 = new NodeT("9", "901");
        NodeT node136 = new NodeT("9", "902");
        NodeT node137 = new NodeT("9", "903");
        NodeT node138 = new NodeT("9", "904");
        NodeT node139 = new NodeT("9", "112");
        NodeT node140 = new NodeT("9", "406");
        NodeT node141 = new NodeT("9", "605");
        NodeT node142 = new NodeT("9", "119");
        NodeT node143 = new NodeT("9", "702");
        NodeT node144 = new NodeT("9", "621");
        NodeT node145 = new NodeT("9", "211");

        NodeT[] allNodes = {
                node1, node2, node3, node4, node5, node6, node7, node8, node9, node10,
                node11, node12, node13, node14, node15, node16, node17, node18, node19, node20,
                node21, node22, node23, node24, node25, node26, node27, node28, node29, node30,
                node31, node32, node33, node34, node35, node36, node37, node38, node39, node40,
                node41, node42, node43, node44, node45, node46, node47, node48, node49, node50,
                node51, node52, node53, node54, node55, node56, node57, node58, node59, node60,
                node61, node62, node63, node64, node65, node66, node67, node68, node69, node70,
                node71, node72, node73, node74, node75, node76, node77, node78, node79, node80,
                node81, node82, node83, node84, node85, node86, node87, node88, node89, node90,
                node91, node92, node93, node94, node95, node96, node97, node98, node99, node100,
                node101, node102, node103, node104, node105, node106, node107, node108, node109, node110,
                node111, node112, node113, node114, node115, node116, node117, node118, node119, node120,
                node121, node122, node123, node124, node125, node126, node127, node128, node129, node130,
                node131, node132, node133, node134, node135, node136, node137, node138, node139, node140,
                node141, node142, node143, node144, node145, node146
        };

        Map<String, Integer> nameCount = new HashMap<>();
        for (NodeT node : allNodes) {
            nameCount.put(node.getName(), nameCount.getOrDefault(node.getName(), 0) + 1);
        }

        for (NodeT node : allNodes) {
            if (nameCount.get(node.getName()) == 1) {
                nodes.add(node);
            } else {
                transNodes.add(node);
            }
        }
        // 1호선
        node1.addAdjacentNodeT(node2, 200, 200);
        node1.addAdjacentNodeT(node24, 1000, 300);
        node1.addAdjacentNodeT(node23, 480, 200);
        node1.addAdjacentNodeT(node51, 480, 200);

        node2.addAdjacentNodeT(node1, 200, 200);
        node2.addAdjacentNodeT(node3, 300, 300);
//103
        node3.addAdjacentNodeT(node2, 300, 300);
        node3.addAdjacentNodeT(node4, 1000, 500);
        node3.addAdjacentNodeT(node70, 1000, 500);
//104
        node4.addAdjacentNodeT(node3, 1000, 500);
        node4.addAdjacentNodeT(node5, 500, 340);
        node4.addAdjacentNodeT(node53, 1000, 650);
//105
        node5.addAdjacentNodeT(node4, 500, 340);
        node5.addAdjacentNodeT(node6, 150, 450);
//106
        node6.addAdjacentNodeT(node5, 150, 450);
        node6.addAdjacentNodeT(node7, 320, 120);
        node6.addAdjacentNodeT(node52, 320, 120);
//107
        node7.addAdjacentNodeT(node6, 320, 120);
        node7.addAdjacentNodeT(node8, 400, 650);
        node7.addAdjacentNodeT(node49, 400, 120);
//108
        node8.addAdjacentNodeT(node7, 400, 650);//108 ->
        node8.addAdjacentNodeT(node9, 800, 200);//108 ->
        node8.addAdjacentNodeT(node52, 400, 650);//108 ->
        node8.addAdjacentNodeT(node84, 800, 200);//108 ->
//109
        node9.addAdjacentNodeT(node8, 800, 200);//109 ->
        node9.addAdjacentNodeT(node10, 900, 430);//109 ->
        node9.addAdjacentNodeT(node80, 1000, 540);//109 ->
//110
        node10.addAdjacentNodeT(node9, 900, 430);//110 ->
        node10.addAdjacentNodeT(node11, 500, 120);//110 ->
//111
        node11.addAdjacentNodeT(node10, 500, 120);//111 ->
        node11.addAdjacentNodeT(node12, 1000, 890);//111 ->
        node11.addAdjacentNodeT(node139, 1000, 890);//111 ->
//112
        node12.addAdjacentNodeT(node11, 1000, 890);//112 ->
        node12.addAdjacentNodeT(node13, 2000, 800);//112 ->
        node12.addAdjacentNodeT(node146, 2000, 800);//112 ->
//113
        node13.addAdjacentNodeT(node12, 2000, 800);//113 ->
        node13.addAdjacentNodeT(node14, 500, 700);//113 ->
        node13.addAdjacentNodeT(node124, 600, 430);//113 ->
//114
        node14.addAdjacentNodeT(node13, 500, 700);//114 ->
        node14.addAdjacentNodeT(node15, 220, 540);//114 ->
        node14.addAdjacentNodeT(node146, 500, 700);//114 ->
        node14.addAdjacentNodeT(node72, 220, 540);//114 ->
//115
        node15.addAdjacentNodeT(node14, 220, 540);//115 ->
        node15.addAdjacentNodeT(node16, 230, 330);//115 ->
        node15.addAdjacentNodeT(node59, 320, 330);//115 ->
        node15.addAdjacentNodeT(node60, 480, 280);//115 ->
        node15.addAdjacentNodeT(node108, 230, 330);//115 ->
//116
        node16.addAdjacentNodeT(node15, 230, 330);//116 ->
        node16.addAdjacentNodeT(node17, 300, 280);//116 ->
        node16.addAdjacentNodeT(node90, 320, 650);//116 ->
        node16.addAdjacentNodeT(node91, 250, 440);//116 ->
        node16.addAdjacentNodeT(node72, 230, 330);//116 ->
//117
        node17.addAdjacentNodeT(node16, 300, 280);//117 ->
        node17.addAdjacentNodeT(node18, 500, 800);//117 ->
        node17.addAdjacentNodeT(node108, 300, 280);//117 ->
//118
        node18.addAdjacentNodeT(node17, 500, 800);//118 ->
        node18.addAdjacentNodeT(node19, 480, 1000);//118 ->
        node18.addAdjacentNodeT(node142, 480, 1000);//118 ->
//119
        node19.addAdjacentNodeT(node18, 480, 1000);//119 ->
        node19.addAdjacentNodeT(node20, 500, 2000);//119 ->
        node19.addAdjacentNodeT(node136, 430, 800);//119 ->
        node19.addAdjacentNodeT(node137, 1000, 1000);//119 ->
//120
        node20.addAdjacentNodeT(node19, 500, 2000);//120 ->
        node20.addAdjacentNodeT(node21, 400, 700);//120 ->
        node20.addAdjacentNodeT(node142, 500, 2000);//120 ->
        node20.addAdjacentNodeT(node107, 400, 700);//120 ->
//121
        node21.addAdjacentNodeT(node20, 400, 700);//121 ->
        node21.addAdjacentNodeT(node22, 900, 650);//121 ->
        node21.addAdjacentNodeT(node86, 700, 280);//121 ->
        node21.addAdjacentNodeT(node87, 500, 800);//121 ->
        node21.addAdjacentNodeT(node82, 900, 650);//121 ->
//122
        node22.addAdjacentNodeT(node21, 900, 650);//122 ->
        node22.addAdjacentNodeT(node23, 300, 440);//122 ->
        node22.addAdjacentNodeT(node77, 320, 430);//122 ->
        node22.addAdjacentNodeT(node78, 480, 120);//122 ->
        node22.addAdjacentNodeT(node107, 900, 650);//122 ->
        node22.addAdjacentNodeT(node51, 300, 440);//122 ->
//123
        node23.addAdjacentNodeT(node22, 300, 440);//123 ->
        node23.addAdjacentNodeT(node1, 480, 200);//123 ->
        node23.addAdjacentNodeT(node45, 250, 200);//123 ->
        node23.addAdjacentNodeT(node46, 300, 300);//123 ->
        node23.addAdjacentNodeT(node41, 480, 200);//123 ->
        node23.addAdjacentNodeT(node82, 300, 440);//123 ->

        // 2호선
        node24.addAdjacentNodeT(node1, 1000, 300);//201 ->
        node24.addAdjacentNodeT(node25, 250, 500);//201 ->
        node24.addAdjacentNodeT(node118, 250, 500);//201 ->
        node24.addAdjacentNodeT(node41, 1000, 300);//201 ->

        node25.addAdjacentNodeT(node24, 250, 500);//202 ->
        node25.addAdjacentNodeT(node26, 480, 340);//202 ->
        node25.addAdjacentNodeT(node44, 1000, 2000);//202 ->
        node25.addAdjacentNodeT(node119, 1000, 2000);//202 ->

        node26.addAdjacentNodeT(node25, 480, 340);//203 ->
        node26.addAdjacentNodeT(node27, 400, 450);//203 ->
        node26.addAdjacentNodeT(node118, 480, 340);//203 ->

        node27.addAdjacentNodeT(node26, 400, 450);//204 ->
        node27.addAdjacentNodeT(node28, 250, 120);//204 ->

        node28.addAdjacentNodeT(node27, 250, 120);//205 ->
        node28.addAdjacentNodeT(node29, 500, 650);//205 ->

        node29.addAdjacentNodeT(node28, 500, 650);//206 ->
        node29.addAdjacentNodeT(node30, 320, 200);
        node29.addAdjacentNodeT(node50, 320, 200);

        node30.addAdjacentNodeT(node29, 320, 200);//207 ->
        node30.addAdjacentNodeT(node31, 250, 430);
        node30.addAdjacentNodeT(node42, 300, 2000);

        node31.addAdjacentNodeT(node30, 250, 430);//208 ->
        node31.addAdjacentNodeT(node32, 300, 120);
        node31.addAdjacentNodeT(node50, 250, 430);
        node31.addAdjacentNodeT(node81, 300, 120);

        node32.addAdjacentNodeT(node31, 300, 120);//209 ->
        node32.addAdjacentNodeT(node33, 150, 890);
        node32.addAdjacentNodeT(node74, 320, 450);

        node33.addAdjacentNodeT(node32, 150, 890);//210 ->
        node33.addAdjacentNodeT(node34, 900, 800);
        node33.addAdjacentNodeT(node81, 150, 890);
        node33.addAdjacentNodeT(node145, 900, 800);

        node34.addAdjacentNodeT(node33, 900, 800);//211 ->
        node34.addAdjacentNodeT(node35, 320, 700);
        node34.addAdjacentNodeT(node105, 300, 440);
        node34.addAdjacentNodeT(node144, 300, 440);

        node35.addAdjacentNodeT(node34, 320, 700);
        node35.addAdjacentNodeT(node36, 150, 540);
        node35.addAdjacentNodeT(node145, 320, 700);

        node36.addAdjacentNodeT(node35, 150, 540);
        node36.addAdjacentNodeT(node37, 500, 330);
        node36.addAdjacentNodeT(node134, 500, 330);

        node37.addAdjacentNodeT(node36, 500, 330);
        node37.addAdjacentNodeT(node38, 210, 280);
        node37.addAdjacentNodeT(node102, 700, 2000);
        node37.addAdjacentNodeT(node133, 700, 2000);

        node38.addAdjacentNodeT(node37, 210, 280);
        node38.addAdjacentNodeT(node39, 150, 800);
        node38.addAdjacentNodeT(node134, 210, 280);
        node38.addAdjacentNodeT(node73, 150, 800);

        node39.addAdjacentNodeT(node38, 150, 800);
        node39.addAdjacentNodeT(node40, 500, 1000);
        node39.addAdjacentNodeT(node69, 900, 340);
        node39.addAdjacentNodeT(node110, 900, 340);

        node40.addAdjacentNodeT(node39, 500, 1000);
        node40.addAdjacentNodeT(node73, 500, 1000);

        node41.addAdjacentNodeT(node2, 200, 200);
        node41.addAdjacentNodeT(node24, 1000, 300);
        node41.addAdjacentNodeT(node23, 480, 200);
        node41.addAdjacentNodeT(node51, 480, 200);

// 3호선
        node42.addAdjacentNodeT(node43, 300, 700);
        node42.addAdjacentNodeT(node30, 300, 2000);
        node42.addAdjacentNodeT(node50, 300, 2000);

        node43.addAdjacentNodeT(node42, 300, 700);
        node43.addAdjacentNodeT(node44, 480, 650);
        node43.addAdjacentNodeT(node119, 480, 650);

        node44.addAdjacentNodeT(node43, 480, 650);
        node44.addAdjacentNodeT(node45, 400, 440);
        node44.addAdjacentNodeT(node25, 1000, 2000);
        node44.addAdjacentNodeT(node118, 1000, 2000);
        node44.addAdjacentNodeT(node76, 700, 700);
        node44.addAdjacentNodeT(node120, 700, 700);

        node45.addAdjacentNodeT(node44, 400, 440);
        node45.addAdjacentNodeT(node119, 400, 440);
        node45.addAdjacentNodeT(node51, 250, 200);
        node45.addAdjacentNodeT(node23, 250, 200);

        node46.addAdjacentNodeT(node23, 300, 300);
        node46.addAdjacentNodeT(node51, 300, 300);
        node46.addAdjacentNodeT(node47, 250, 500);

        node47.addAdjacentNodeT(node46, 250, 500);
        node47.addAdjacentNodeT(node71, 900, 340);
        node47.addAdjacentNodeT(node48, 900, 340);

        node48.addAdjacentNodeT(node47, 900, 340);
        node48.addAdjacentNodeT(node49, 480, 450);
        node48.addAdjacentNodeT(node54, 300, 430);
        node48.addAdjacentNodeT(node53, 150, 200);

        node49.addAdjacentNodeT(node48, 480, 450);
        node49.addAdjacentNodeT(node52, 400, 120);
        node49.addAdjacentNodeT(node71, 480, 450);
        node49.addAdjacentNodeT(node7, 400, 120);

        node50.addAdjacentNodeT(node29, 320, 200);
        node50.addAdjacentNodeT(node31, 250, 430);
        node50.addAdjacentNodeT(node42, 300, 2000);

        node51.addAdjacentNodeT(node45, 250, 200);
        node51.addAdjacentNodeT(node46, 300, 300);
        node51.addAdjacentNodeT(node1, 480, 200);
        node51.addAdjacentNodeT(node41, 480, 200);
        node51.addAdjacentNodeT(node22, 300, 440);
        node51.addAdjacentNodeT(node82, 300, 440);

        node52.addAdjacentNodeT(node6, 320, 120);
        node52.addAdjacentNodeT(node8, 400, 650);
        node52.addAdjacentNodeT(node49, 400, 120);

        // 4호선
        node53.addAdjacentNodeT(node4, 1000, 650);
        node53.addAdjacentNodeT(node70, 1000, 650);
        node53.addAdjacentNodeT(node71, 150, 200);
        node53.addAdjacentNodeT(node48, 150, 200);

        node54.addAdjacentNodeT(node48, 300, 430);
        node54.addAdjacentNodeT(node71, 300, 430);
        node54.addAdjacentNodeT(node55, 210, 120);
        node54.addAdjacentNodeT(node83, 210, 120);

        node55.addAdjacentNodeT(node54, 210, 120);
        node55.addAdjacentNodeT(node56, 320, 890);

        node56.addAdjacentNodeT(node55, 320, 890);
        node56.addAdjacentNodeT(node57, 210, 800);
        node56.addAdjacentNodeT(node83, 320, 890);

        node57.addAdjacentNodeT(node56, 210, 800);
        node57.addAdjacentNodeT(node58, 500, 700);
        node57.addAdjacentNodeT(node140, 500, 700);

        node58.addAdjacentNodeT(node57, 500, 700);
        node58.addAdjacentNodeT(node59, 300, 540);
        node58.addAdjacentNodeT(node135, 300, 650);
        node58.addAdjacentNodeT(node141, 210, 440);
        node58.addAdjacentNodeT(node89, 210, 440);

        node59.addAdjacentNodeT(node58, 300, 540);
        node59.addAdjacentNodeT(node140, 300, 540);
        node59.addAdjacentNodeT(node15, 320, 330);
        node59.addAdjacentNodeT(node72, 320, 330);

        node60.addAdjacentNodeT(node72, 480, 280);
        node60.addAdjacentNodeT(node15, 480, 280);
        node60.addAdjacentNodeT(node61, 300, 800);
        node60.addAdjacentNodeT(node130, 300, 800);

        node61.addAdjacentNodeT(node60, 300, 800);
        node61.addAdjacentNodeT(node62, 480, 1000);

        node62.addAdjacentNodeT(node61, 480, 1000);
        node62.addAdjacentNodeT(node63, 300, 2000);
        node62.addAdjacentNodeT(node130, 480, 1000);

        node63.addAdjacentNodeT(node62, 300, 2000);
        node63.addAdjacentNodeT(node64, 900, 700);

        node64.addAdjacentNodeT(node63, 900, 700);
        node64.addAdjacentNodeT(node65, 400, 650);
        node64.addAdjacentNodeT(node93, 320, 500);
        node64.addAdjacentNodeT(node94, 1000, 340);

        node65.addAdjacentNodeT(node64, 400, 650);
        node65.addAdjacentNodeT(node66, 430, 440);
        node65.addAdjacentNodeT(node109, 400, 650);

        node66.addAdjacentNodeT(node65, 430, 440);
        node66.addAdjacentNodeT(node67, 150, 200);

        node67.addAdjacentNodeT(node66, 150, 200);
        node67.addAdjacentNodeT(node68, 1000, 300);
        node67.addAdjacentNodeT(node122, 1000, 300);

        node68.addAdjacentNodeT(node67, 1000, 300);
        node68.addAdjacentNodeT(node69, 500, 500);
        node68.addAdjacentNodeT(node110, 500, 500);
        node68.addAdjacentNodeT(node116, 300, 120);
        node68.addAdjacentNodeT(node117, 430, 650);

        node69.addAdjacentNodeT(node68, 500, 500);
        node69.addAdjacentNodeT(node122, 500, 500);
        node69.addAdjacentNodeT(node101, 320, 800);
        node69.addAdjacentNodeT(node100, 480, 890);
        node69.addAdjacentNodeT(node73, 900, 340);
        node69.addAdjacentNodeT(node39, 900, 340);

        node70.addAdjacentNodeT(node3, 1000, 500);
        node70.addAdjacentNodeT(node5, 500, 340);
        node70.addAdjacentNodeT(node53, 1000, 650);

        node71.addAdjacentNodeT(node53, 150, 200);
        node71.addAdjacentNodeT(node54, 300, 430);
        node71.addAdjacentNodeT(node47, 900, 340);
        node71.addAdjacentNodeT(node49, 480, 450);

        node72.addAdjacentNodeT(node59, 320, 330);
        node72.addAdjacentNodeT(node60, 480, 280);
        node72.addAdjacentNodeT(node14, 220, 540);
        node72.addAdjacentNodeT(node16, 230, 330);
        node72.addAdjacentNodeT(node108, 230, 330);

        node73.addAdjacentNodeT(node38, 150, 800);
        node73.addAdjacentNodeT(node40, 500, 1000);
        node73.addAdjacentNodeT(node69, 900, 340);
        node73.addAdjacentNodeT(node110, 900, 340);

        // 5호선
        node74.addAdjacentNodeT(node75, 320, 120);
        node74.addAdjacentNodeT(node81, 320, 450);
        node74.addAdjacentNodeT(node32, 320, 450);

        node75.addAdjacentNodeT(node74, 320, 120);
        node75.addAdjacentNodeT(node76, 430, 650);
        node75.addAdjacentNodeT(node120, 430, 650);

        node76.addAdjacentNodeT(node75, 430, 650);
        node76.addAdjacentNodeT(node77, 210, 200);
        node76.addAdjacentNodeT(node119, 700, 700);
        node76.addAdjacentNodeT(node44, 700, 700);
        node76.addAdjacentNodeT(node85, 500, 650);
        node76.addAdjacentNodeT(node121, 500, 650);

        node77.addAdjacentNodeT(node76, 210, 200);
        node77.addAdjacentNodeT(node120, 210, 200);
        node77.addAdjacentNodeT(node82, 320, 430);
        node77.addAdjacentNodeT(node22, 320, 430);

        node78.addAdjacentNodeT(node79, 300, 890);
        node78.addAdjacentNodeT(node22, 480, 120);
        node78.addAdjacentNodeT(node82, 480, 120);

        node79.addAdjacentNodeT(node78, 300, 890);
        node79.addAdjacentNodeT(node55, 320, 800);
        node79.addAdjacentNodeT(node83, 320, 800);

        node80.addAdjacentNodeT(node83, 300, 700);
        node80.addAdjacentNodeT(node55, 300, 700);
        node80.addAdjacentNodeT(node9, 1000, 540);
        node80.addAdjacentNodeT(node84, 1000, 540);

        node81.addAdjacentNodeT(node31, 300, 120);
        node81.addAdjacentNodeT(node33, 150, 890);
        node81.addAdjacentNodeT(node74, 320, 450);

        node82.addAdjacentNodeT(node77, 320, 430);
        node82.addAdjacentNodeT(node78, 480, 120);
        node82.addAdjacentNodeT(node21, 900, 650);
        node82.addAdjacentNodeT(node23, 300, 440);
        node82.addAdjacentNodeT(node51, 300, 440);
        node82.addAdjacentNodeT(node107, 900, 650);

        node83.addAdjacentNodeT(node54, 210, 120);
        node83.addAdjacentNodeT(node56, 320, 890);
        node83.addAdjacentNodeT(node79, 320, 800);
        node83.addAdjacentNodeT(node80, 300, 700);

        node84.addAdjacentNodeT(node8, 800, 200);
        node84.addAdjacentNodeT(node10, 900, 430);
        node84.addAdjacentNodeT(node80, 1000, 540);

// 6호선
        node85.addAdjacentNodeT(node86, 150, 330);
        node85.addAdjacentNodeT(node106, 150, 1000);
        node85.addAdjacentNodeT(node76, 500, 650);
        node85.addAdjacentNodeT(node120, 500, 650);
        node85.addAdjacentNodeT(node111, 430, 440);

        node86.addAdjacentNodeT(node85, 150, 330);
        node86.addAdjacentNodeT(node121, 150, 330);
        node86.addAdjacentNodeT(node107, 70, 280);
        node86.addAdjacentNodeT(node21, 700, 280);

        node87.addAdjacentNodeT(node88, 300, 1000);
        node87.addAdjacentNodeT(node107, 650, 800);
        node87.addAdjacentNodeT(node21, 650, 800);

        node88.addAdjacentNodeT(node87, 300, 1000);
        node88.addAdjacentNodeT(node89, 430, 2000);
        node88.addAdjacentNodeT(node141, 430, 2000);

        node89.addAdjacentNodeT(node88, 430, 2000);
        node89.addAdjacentNodeT(node90, 480, 700);
        node89.addAdjacentNodeT(node136, 480, 280);
        node89.addAdjacentNodeT(node140, 210, 440);
        node89.addAdjacentNodeT(node58, 210, 440);

        node90.addAdjacentNodeT(node89, 480, 700);
        node90.addAdjacentNodeT(node141, 480, 700);
        node90.addAdjacentNodeT(node108, 320, 650);
        node90.addAdjacentNodeT(node16, 320, 650);

        node91.addAdjacentNodeT(node16, 250, 440);
        node91.addAdjacentNodeT(node108, 250, 440);
        node91.addAdjacentNodeT(node92, 500, 200);
        node91.addAdjacentNodeT(node131, 500, 200);

        node92.addAdjacentNodeT(node91, 500, 200);
        node92.addAdjacentNodeT(node93, 700, 300);
        node92.addAdjacentNodeT(node127, 700, 540);
        node92.addAdjacentNodeT(node130, 500, 700);
        node92.addAdjacentNodeT(node61, 500, 700);

        node93.addAdjacentNodeT(node92, 700, 300);
        node93.addAdjacentNodeT(node131, 700, 300);
        node93.addAdjacentNodeT(node109, 320, 500);
        node93.addAdjacentNodeT(node64, 320, 500);

        node94.addAdjacentNodeT(node64, 1000, 340);
        node94.addAdjacentNodeT(node109, 1000, 340);
        node94.addAdjacentNodeT(node95, 700, 450);

        node95.addAdjacentNodeT(node94, 700, 450);
        node95.addAdjacentNodeT(node96, 700, 120);

        node96.addAdjacentNodeT(node95, 700, 120);
        node96.addAdjacentNodeT(node97, 150, 650);

        node97.addAdjacentNodeT(node96, 150, 650);
        node97.addAdjacentNodeT(node98, 430, 200);
        node97.addAdjacentNodeT(node123, 430, 200);

        node98.addAdjacentNodeT(node97, 430, 200);
        node98.addAdjacentNodeT(node99, 500, 430);
        node98.addAdjacentNodeT(node117, 480, 200);

        node99.addAdjacentNodeT(node98, 500, 430);
        node99.addAdjacentNodeT(node100, 700, 120);

        node100.addAdjacentNodeT(node99, 700, 120);
        node100.addAdjacentNodeT(node110, 480, 890);
        node100.addAdjacentNodeT(node69, 480, 890);

        node101.addAdjacentNodeT(node69, 320, 800);
        node101.addAdjacentNodeT(node133, 300, 700);
        node101.addAdjacentNodeT(node102, 300, 700);
        node101.addAdjacentNodeT(node110, 320, 800);

        node102.addAdjacentNodeT(node37, 700, 2000);
        node102.addAdjacentNodeT(node101, 300, 700);
        node102.addAdjacentNodeT(node103, 250, 540);
        node102.addAdjacentNodeT(node115, 250, 1000);
        node102.addAdjacentNodeT(node132, 250, 1000);
        node102.addAdjacentNodeT(node134, 700, 2000);

        node103.addAdjacentNodeT(node133, 250, 540);
        node103.addAdjacentNodeT(node102, 250, 540);
        node103.addAdjacentNodeT(node104, 700, 330);

        node104.addAdjacentNodeT(node144, 320, 280);
        node104.addAdjacentNodeT(node103, 700, 330);
        node104.addAdjacentNodeT(node105, 320, 280);

        node105.addAdjacentNodeT(node138, 250, 650);
        node105.addAdjacentNodeT(node145, 300, 440);
        node105.addAdjacentNodeT(node34, 300, 440);
        node105.addAdjacentNodeT(node104, 320, 280);
        node105.addAdjacentNodeT(node106, 480, 800);

        node106.addAdjacentNodeT(node144, 480, 800);
        node106.addAdjacentNodeT(node121, 150, 1000);
        node106.addAdjacentNodeT(node85, 150, 1000);
        node106.addAdjacentNodeT(node105, 480, 800);

        node107.addAdjacentNodeT(node20, 400, 700);
        node107.addAdjacentNodeT(node22, 900, 650);
        node107.addAdjacentNodeT(node86, 700, 280);
        node107.addAdjacentNodeT(node87, 500, 800);
        node107.addAdjacentNodeT(node82, 900, 650);

        node108.addAdjacentNodeT(node15, 230, 330);
        node108.addAdjacentNodeT(node17, 300, 280);
        node108.addAdjacentNodeT(node90, 320, 660);
        node108.addAdjacentNodeT(node91, 250, 440);
        node108.addAdjacentNodeT(node72, 230, 330);

        node109.addAdjacentNodeT(node63, 900, 700);
        node109.addAdjacentNodeT(node65, 400, 650);
        node109.addAdjacentNodeT(node93, 320, 500);
        node109.addAdjacentNodeT(node94, 1000, 340);

        node110.addAdjacentNodeT(node68, 500, 500);
        node110.addAdjacentNodeT(node122, 500, 500);
        node110.addAdjacentNodeT(node101, 320, 800);
        node110.addAdjacentNodeT(node100, 480, 890);
        node110.addAdjacentNodeT(node73, 900, 340);
        node110.addAdjacentNodeT(node39, 900, 340);


        // 7호선
        node111.addAdjacentNodeT(node85, 430, 440);
        node111.addAdjacentNodeT(node143, 150, 200);
        node111.addAdjacentNodeT(node112, 150, 200);
        node111.addAdjacentNodeT(node121, 430, 440);

        node112.addAdjacentNodeT(node137, 150, 2000);
        node112.addAdjacentNodeT(node138, 500, 700);
        node112.addAdjacentNodeT(node111, 150, 200);
        node112.addAdjacentNodeT(node113, 600, 300);

        node113.addAdjacentNodeT(node143, 600, 300);
        node113.addAdjacentNodeT(node112, 600, 300);
        node113.addAdjacentNodeT(node114, 700, 500);

        node114.addAdjacentNodeT(node132, 250, 340);
        node114.addAdjacentNodeT(node113, 700, 500);
        node114.addAdjacentNodeT(node115, 250, 340);

        node115.addAdjacentNodeT(node102, 250, 1000);
        node115.addAdjacentNodeT(node129, 600, 800);
        node115.addAdjacentNodeT(node133, 250, 1000);
        node115.addAdjacentNodeT(node114, 250, 340);
        node115.addAdjacentNodeT(node116, 600, 450);

        node116.addAdjacentNodeT(node68, 300, 120);
        node116.addAdjacentNodeT(node132, 600, 450);
        node116.addAdjacentNodeT(node115, 600, 450);
        node116.addAdjacentNodeT(node122, 300, 120);

        node117.addAdjacentNodeT(node68, 430, 650);
        node117.addAdjacentNodeT(node98, 480, 200);
        node117.addAdjacentNodeT(node122, 430, 650);
        node117.addAdjacentNodeT(node123, 480, 200);

        node118.addAdjacentNodeT(node24, 250, 500);
        node118.addAdjacentNodeT(node26, 480, 340);
        node118.addAdjacentNodeT(node44, 1000, 2000);
        node118.addAdjacentNodeT(node119, 1000, 2000);

        node119.addAdjacentNodeT(node25, 1000, 2000);
        node119.addAdjacentNodeT(node43, 480, 650);
        node119.addAdjacentNodeT(node45, 400, 440);
        node119.addAdjacentNodeT(node76, 700, 700);
        node119.addAdjacentNodeT(node118, 1000, 2000);
        node119.addAdjacentNodeT(node120, 700, 700);

        node120.addAdjacentNodeT(node44, 700, 700);
        node120.addAdjacentNodeT(node75, 430, 650);
        node120.addAdjacentNodeT(node77, 210, 200);
        node120.addAdjacentNodeT(node85, 500, 650);
        node120.addAdjacentNodeT(node119, 700, 700);
        node120.addAdjacentNodeT(node121, 500, 650);

        node121.addAdjacentNodeT(node76, 500, 650);
        node121.addAdjacentNodeT(node86, 150, 330);
        node121.addAdjacentNodeT(node106, 150, 1000);
        node121.addAdjacentNodeT(node111, 430, 440);
        node121.addAdjacentNodeT(node120, 500, 650);

        node122.addAdjacentNodeT(node67, 1000, 300);
        node122.addAdjacentNodeT(node69, 500, 500);
        node122.addAdjacentNodeT(node110, 500, 500);
        node122.addAdjacentNodeT(node116, 300, 120);
        node122.addAdjacentNodeT(node117, 430, 650);

        node123.addAdjacentNodeT(node97, 430, 200);
        node123.addAdjacentNodeT(node99, 500, 430);
        node123.addAdjacentNodeT(node117, 480, 200);

        node124.addAdjacentNodeT(node125, 1000, 120);
        node124.addAdjacentNodeT(node146, 600, 430);
        node124.addAdjacentNodeT(node13, 600, 430);

        node125.addAdjacentNodeT(node124, 1000, 120);
        node125.addAdjacentNodeT(node126, 700, 890);

        node126.addAdjacentNodeT(node125, 700, 890);
        node126.addAdjacentNodeT(node130, 600, 800);
        node126.addAdjacentNodeT(node61, 600, 800);

        node127.addAdjacentNodeT(node128, 150, 330);
        node127.addAdjacentNodeT(node131, 700, 540);
        node127.addAdjacentNodeT(node92, 700, 540);

        node128.addAdjacentNodeT(node127, 150, 330);
        node128.addAdjacentNodeT(node129, 210, 280);

        node129.addAdjacentNodeT(node128, 210, 280);
        node129.addAdjacentNodeT(node132, 600, 800);
        node129.addAdjacentNodeT(node115, 600, 800);

        node130.addAdjacentNodeT(node60, 300, 800);
        node130.addAdjacentNodeT(node62, 480, 1000);
        node130.addAdjacentNodeT(node92, 500, 700);
        node130.addAdjacentNodeT(node126, 600, 800);
        node130.addAdjacentNodeT(node131, 500, 700);

        node131.addAdjacentNodeT(node61, 500, 700);
        node131.addAdjacentNodeT(node91, 500, 200);
        node131.addAdjacentNodeT(node93, 700, 300);
        node131.addAdjacentNodeT(node127, 700, 540);
        node131.addAdjacentNodeT(node130, 500, 700);

        node132.addAdjacentNodeT(node102, 250, 1000);
        node132.addAdjacentNodeT(node114, 250, 340);
        node132.addAdjacentNodeT(node116, 600, 450);
        node132.addAdjacentNodeT(node129, 600, 800);
        node132.addAdjacentNodeT(node133, 250, 1000);

        node133.addAdjacentNodeT(node37, 700, 2000);
        node133.addAdjacentNodeT(node101, 300, 700);
        node133.addAdjacentNodeT(node103, 250, 540);
        node133.addAdjacentNodeT(node115, 250, 1000);
        node133.addAdjacentNodeT(node132, 250, 1000);
        node133.addAdjacentNodeT(node134, 700, 2000);

        node134.addAdjacentNodeT(node36, 500, 330);
        node134.addAdjacentNodeT(node38, 210, 280);
        node134.addAdjacentNodeT(node102, 700, 2000);
        node134.addAdjacentNodeT(node133, 700, 2000);

        node146.addAdjacentNodeT(node12, 2000, 800);
        node146.addAdjacentNodeT(node14, 500, 700);
        node146.addAdjacentNodeT(node139, 2000, 800);
        node146.addAdjacentNodeT(node124, 600, 430);


        // 9호선
        node135.addAdjacentNodeT(node12, 600, 700);
        node135.addAdjacentNodeT(node58, 300, 650);
        node135.addAdjacentNodeT(node139, 600, 700);
        node135.addAdjacentNodeT(node140, 300, 650);

        node136.addAdjacentNodeT(node19, 430, 800);
        node136.addAdjacentNodeT(node89, 480, 280);
        node136.addAdjacentNodeT(node141, 480, 280);
        node136.addAdjacentNodeT(node142, 430, 800);

        node137.addAdjacentNodeT(node19, 1000, 1000);
        node137.addAdjacentNodeT(node112, 150, 2000);
        node137.addAdjacentNodeT(node142, 1000, 1000);
        node137.addAdjacentNodeT(node143, 150, 2000);

        node138.addAdjacentNodeT(node105, 250, 650);
        node138.addAdjacentNodeT(node112, 500, 700);
        node138.addAdjacentNodeT(node143, 500, 700);
        node138.addAdjacentNodeT(node144, 250, 650);

        node139.addAdjacentNodeT(node135, 600, 700);
        node139.addAdjacentNodeT(node11, 1000, 890);
        node139.addAdjacentNodeT(node13, 2000, 800);
        node139.addAdjacentNodeT(node146, 2000, 800);

        node140.addAdjacentNodeT(node135, 300, 650);
        node140.addAdjacentNodeT(node89, 210, 440);
        node140.addAdjacentNodeT(node141, 210, 440);
        node140.addAdjacentNodeT(node57, 500, 700);
        node140.addAdjacentNodeT(node59, 300, 540);

        node141.addAdjacentNodeT(node136, 480, 280);
        node141.addAdjacentNodeT(node140, 210, 440);
        node141.addAdjacentNodeT(node58, 210, 440);
        node141.addAdjacentNodeT(node88, 430, 2000);
        node141.addAdjacentNodeT(node90, 480, 700);

        node142.addAdjacentNodeT(node136, 430, 800);
        node142.addAdjacentNodeT(node137, 1000, 1000);
        node142.addAdjacentNodeT(node18, 480, 1000);
        node142.addAdjacentNodeT(node20, 500, 2000);

        node143.addAdjacentNodeT(node137, 150, 2000);
        node143.addAdjacentNodeT(node138, 500, 700);
        node143.addAdjacentNodeT(node111, 150, 200);
        node143.addAdjacentNodeT(node113, 600, 300);

        node144.addAdjacentNodeT(node138, 250, 650);
        node144.addAdjacentNodeT(node145, 300, 440);
        node144.addAdjacentNodeT(node34, 300, 440);
        node144.addAdjacentNodeT(node104, 320, 280);
        node144.addAdjacentNodeT(node106, 480, 800);

        node145.addAdjacentNodeT(node144, 300, 440);
        node145.addAdjacentNodeT(node33, 900, 800);
        node145.addAdjacentNodeT(node35, 320, 700);
    }

    //역 리스트 초기화
    public static void initNodeList() {
        nodes = new ArrayList<>();
        transNodes = new ArrayList<>();
    }
    public List<NodeT> findTransferredNodesInShortestPath(List<NodeT> transNodes) {
        List<NodeT> transferredNodes = new ArrayList<>();

        for (NodeT node : shortestPath) {
            if (transNodes.contains(node)) {
                transferredNodes.add(node);
            }
        }

        return transferredNodes;
    }

    @Override
    public int compareTo(NodeT node) {
        return Integer.compare(this.time, node.getTime());
    }

    //노드 초기화
    public static void initializeNodesT() {
        for (NodeT node : nodes) {
            node.setCost(Integer.MAX_VALUE);
            node.setTime(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
            node.setTransferCount(0);
        }
        for (NodeT node : transNodes) {
            node.setCost(Integer.MAX_VALUE);
            node.setTime(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
            node.setTransferCount(0);
        }
    }

    //노드에 인접노드, 가중치 추가, 호선 다르면 추가로 가중치 추가
    public void addAdjacentNodeT(NodeT node, int time, int cost) {
        if (!((this.getLine()).equals(node.getLine()))) {
            time += 2000; // 환승하면 가중치에 추가
        }
        List<Integer> arr = new ArrayList<>();
        arr.add(0, time);
        arr.add(1, cost);
        this.adjacentNodes.put(node, arr);
    }

    //최소환승 계산 메서드
    public static resultT calculateMinTransfer(String start, String end) {
        NodeT source = null;
        NodeT source2 = null;
        NodeT destination = null;
        NodeT destination2 = null;
        initT();

        //이름 맞는거 찾아서 노드 배정
        for (NodeT node : nodes) {
            if (start.equals(node.getName())) {
                source = node;
            }
            if (end.equals(node.getName())) {
                destination = node;
            }
        }

        //출발 or 도착이 환승역이면 source2, destination2도 배정
        for (NodeT node : transNodes) {
            if (source != null) {
                break;
            }
            if (start.equals(node.getName())) {
                source = node;
            }
        }

        for (NodeT node : transNodes) {
            if (start.equals(node.getName())) {
                source2 = node;
            }
        }
        for (NodeT node : transNodes) {
            if (destination != null) {
                break;
            }
            if (end.equals(node.getName())) {
                destination = node;
            }
        }
        for (NodeT node : transNodes) {
            if (end.equals(node.getName())) {
                destination2 = node;
            }
        }

        resultT result1 = calcLogicT(source, destination);

        if (source2 == null && destination2 == null) {
            return result1;
        }
        //출발만 환승역
        if (source2 != null && destination2 == null) {
            resultT result2 = calcLogicT(source2, destination);
            return result1.compareTo(result2) <= 0 ? result1 : result2;
        }
        //도착만 환승역
        if (source2 == null && destination2 != null) {
            resultT result3 = calcLogicT(source, destination2);
            return result1.compareTo(result3) <= 0 ? result1 : result3;
        }
        //둘다 환승역
        if (source2 != null && destination2 != null) {
            resultT result4 = calcLogicT(source2, destination);
            resultT result5 = calcLogicT(source2, destination2);
            resultT result6 = calcLogicT(source, destination2);

            resultT minResult = findMinimumResult(result1, result4, result5, result6);
            return minResult;
        }
        return result1;
    }

    //최소 환승 경로 중 최단 시간 경로 선별
    public static resultT findMinimumResult(resultT... results) {
        resultT minResult = results[0];
        for (int i = 1; i < results.length; i++) {
            if (minResult.compareTo(results[i]) > 0) {
                minResult = results[i];
            }
        }
        return minResult;
    }

    //다익스트라 계산 로직
    public static resultT calcLogicT(NodeT source, NodeT destination) {
        initializeNodesT();

        source.setTime(0);
        source.setCost(0);
        //최단거리 확정된 경로
        Set<NodeT> settleNodes = new HashSet<>();
        //최단거리 미확정 경로, 미확정 노드들 중 최소 거리를 가진 노드 먼저 처리하려고 우선순위큐 사용
        Queue<NodeT> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));

        //
        while (!unsettledNodes.isEmpty()) {
            NodeT currentNode = unsettledNodes.poll();

            if (currentNode.equals(destination)) {
                break;
            }

            currentNode.getAdjacentNodes()
                    .entrySet().stream()
                    .filter(entry -> !settleNodes.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluateTransferAndPath(entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });

            settleNodes.add(currentNode);
        }
        destination.setTime(destination.getTime() - 2000 * destination.getTransferCount());

        //최소환승 경로
        String resultPath = destination.printMinTransfer(destination);
        List<NodeT> transInPath = destination.findTransferredNodesInShortestPath(transNodes);

        return new resultT(destination.getTime(), destination.getCost(), destination.getTransferCount(), resultPath,transInPath);
    }

    //주어진 인접노드와의 최단경로 평가하고 업데이트
    private static void evaluateTransferAndPath(NodeT adjacentNode, Integer time, Integer cost, NodeT sourceNode) {
        Integer newTime = sourceNode.getTime() + time;
        Integer newCost = sourceNode.getCost() + cost;

        //새 가중치가 기존 가중치보다 작으면
        if (newTime < adjacentNode.getTime()) {

            //가중치 초기화
            adjacentNode.setTime(newTime);
            adjacentNode.setCost(newCost);
            //ShortestPath에 추가
            List<NodeT> newPath = Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode))
                    .collect(Collectors.toList());
            adjacentNode.setShortestPath(newPath);

            // 환승 횟수 업데이트
            if (!sourceNode.getLine().equals(adjacentNode.getLine())) {
                adjacentNode.setTransferCount(sourceNode.getTransferCount() + 1);
            } else {
                adjacentNode.setTransferCount(sourceNode.getTransferCount());
            }
        }
    }

    //경로
    private String printMinTransfer(NodeT destination) {
        String path = destination.getShortestPath().stream()
                .map(NodeT::getName)
                .collect(Collectors.joining(" -> "));
        String resultPath = path.isBlank()
                ? String.format("%s", destination.getName())
                : String.format("%s -> %s", path, destination.getName()); // 거리 출력 제거
        return resultPath;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class resultT implements Comparable<resultT> {
        private int transferCount;
        private Integer cost;
        private Integer time;
        private String path;
        private List<NodeT> transInPath;

        public resultT(Integer time, Integer cost, int transferCount, String resultPath, List<NodeT> transInPath) {
            this.time = time;
            this.cost = cost;
            this.transferCount = transferCount;
            this.path = resultPath;
            this.transInPath  = transInPath;
        }

        @Override
        public int compareTo(resultT other) {
            int compare = Integer.compare(this.transferCount, other.transferCount);
            if (compare == 0) {
                compare = Integer.compare(this.time, other.time);
            }
            return compare;
        }

    }
}
