package me.jazzy.webtoontracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InformationBody {

    private String detail;
    private Integer httpCode;
    private Long timestamp;
}