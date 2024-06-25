package com.gec.wiki.pojo.req;

import lombok.Data;

@Data
public class EbookQueryReq extends PageReq{
    private Long id;
    private String name;         //名称
    private long category2Id;
}