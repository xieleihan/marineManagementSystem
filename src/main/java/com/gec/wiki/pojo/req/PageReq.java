package com.gec.wiki.pojo.req;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class PageReq {
    @NotNull(message = "页码不能为空")
    private Integer page;
    @NotNull(message = "每页显示的条数不能为空")
    @Max(value = 1000,message = "每页的条数不能超过1000条")
    private Integer size;
}