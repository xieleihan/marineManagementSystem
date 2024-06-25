package com.gec.wiki.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName ebook_snapshot
 */
@TableName(value ="ebook_snapshot")
@Data
public class EbookSnapshot implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 电子书id
     */
    @TableField(value = "ebook_id")
    private Long ebookId;

    /**
     * 快照日期

     */
    @TableField(value = "date")
    private Date date;

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

    /**
     * 阅读增长
     */
    @TableField(value = "view_increase")
    private Integer viewIncrease;

    /**
     * 点赞增长
     */
    @TableField(value = "vote_increase")
    private Integer voteIncrease;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}