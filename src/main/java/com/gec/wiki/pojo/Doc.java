package com.gec.wiki.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName doc
 */
@TableName(value ="doc")
@Data
public class Doc implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电子书id
     */
    @TableField(value = "ebook_id")
    private Long ebookId;

    /**
     * 父id
     */
    @TableField(value = "parent")
    private Long parent;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 顺序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 阅读数
     */
    @TableField(value = "view_count")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @TableField(value = "vote_count")
    private Integer voteCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}