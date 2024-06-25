package com.gec.wiki.pojo.req;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EbookSaveReq {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "name")
    @NotNull(message = "名称不能为空")
    private String name;

    /**
     * 分类1
     */
    @TableField(value = "category1_id")
    private Long category1Id;

    /**
     * 分类2
     */
    @TableField(value = "category2_id")
    private Long category2Id;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 文档数
     */
    @TableField(value = "doc_count")
    private Integer docCount;

    /**
     * 阅读数
     */
    @TableField(value = "view_count")
    private Integer viewCount;
}
