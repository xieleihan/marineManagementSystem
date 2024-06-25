package com.gec.wiki.service;

import com.gec.wiki.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.wiki.pojo.req.CategoryQueryReq;
import com.gec.wiki.pojo.resp.CategoryResp;
import com.gec.wiki.pojo.resp.CommonResp;
import com.gec.wiki.pojo.resp.PageResp;

import java.util.List;

/**
* @author yzh
* @description 针对表【category】的数据库操作Service
* @createDate 2024-06-04 20:23:46
*/
public interface CategoryService extends IService<Category> {
    public PageResp<CategoryResp> getCategoryListByPage(CategoryQueryReq req);
    public List<CategoryResp> allList(CategoryQueryReq req);
}
