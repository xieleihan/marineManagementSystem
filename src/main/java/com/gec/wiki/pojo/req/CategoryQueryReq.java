package com.gec.wiki.pojo.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gec.wiki.pojo.resp.PageResp;
import lombok.Data;

import java.io.Serializable;


@Data
public class CategoryQueryReq extends PageReq {
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