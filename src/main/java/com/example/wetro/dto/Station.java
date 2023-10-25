package com.example.wetro.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    private String start;   //  출발역
    private String finish;  //  도착역
    private Integer time;   //  시간
    private Integer distance;//거리
    private Integer cost;   //비용

}
