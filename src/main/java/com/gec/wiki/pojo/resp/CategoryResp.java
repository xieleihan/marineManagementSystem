package com.gec.wiki.pojo.resp;

import lombok.Data;


@Data
public class CategoryResp extends PageResp {
    /**
     * id
     */

    private Long id;

    /**
     * 父id
     */

    private Long parent;

    /**
     * 名称
     */

    private String name;

    /**
     * 顺序
     */

    private Integer sort;

}