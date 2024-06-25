package com.gec.wiki.pojo.req;

import lombok.Data;


@Data
public class CategorySaveReq {
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