package com.gec.wiki.pojo.resp;

import lombok.Data;

import java.util.List;

@Data
public class PageResp <T>{
    private Long total;    //总条数
    private List<T> list;   //显示的数据
}