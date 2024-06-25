package com.gec.wiki.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.wiki.pojo.Category;
import com.gec.wiki.pojo.req.CategoryQueryReq;
import com.gec.wiki.pojo.resp.CommonResp;
import com.gec.wiki.pojo.resp.CategoryResp;
import com.gec.wiki.pojo.resp.PageResp;
import com.gec.wiki.service.CategoryService;
import com.gec.wiki.utils.CopyUtil;
import com.gec.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/getCategoryByCategoryReq")
    public CommonResp getCategoryByCategoryReq(CategoryQueryReq req){
        List<Category> list;
        System.out.println("req = " + req);
        if (!ObjectUtils.isEmpty(req.getName())){
            QueryWrapper<Category> wrapper = new QueryWrapper<>();
            wrapper.like("name",req.getName());
            list = categoryService.list(wrapper);
        } else {
            list = categoryService.list();
        }
        List<CategoryResp> categoryResps = CopyUtil.copyList(list, CategoryResp.class);
        CommonResp<List<CategoryResp>> resp = new CommonResp<>();
        resp.setContent(categoryResps);
        return resp;
    }


    //打印日志
    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
//  驼峰命名法，首字母不用大写， 后面每个字母都要大写。
    @GetMapping("/getCategoryListByPage")
    public CommonResp getCategoryListByPage(CategoryQueryReq req){
        PageResp<CategoryResp> pageResp = categoryService.getCategoryListByPage(req);
        CommonResp<PageResp<CategoryResp>> resp = new CommonResp<>();
        resp.setContent(pageResp);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody CategoryResp rep){
        // 把参数格式化成Category
        Category category = CopyUtil.copy(rep, Category.class);
        //什么情况做新增  或者修改
        if(ObjectUtils.isEmpty(rep.getId())){
            //添加
            SnowFlake snowFlake = new SnowFlake();
            category.setId(snowFlake.nextId());
            categoryService.saveOrUpdate(category);
        }else {
            categoryService.saveOrUpdate(category);
        }
        CommonResp<Object> resp = new CommonResp<>();
        return resp;
    }
    @GetMapping("/remove")
    public CommonResp remove(long id){
        categoryService.removeById(id);
        CommonResp<Object> resp = new CommonResp<>();
        return resp;
    }
    @GetMapping("/allList")
    public CommonResp allList(CategoryQueryReq req){
        List<CategoryResp> resp = categoryService.allList(req);
        CommonResp<List<CategoryResp>> listCommonResp = new CommonResp<>();
        listCommonResp.setContent(resp);
        return listCommonResp;
    }



}
