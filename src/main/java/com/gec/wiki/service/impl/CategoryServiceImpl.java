package com.gec.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.wiki.pojo.Category;
import com.gec.wiki.pojo.req.CategoryQueryReq;
import com.gec.wiki.pojo.resp.CategoryResp;
import com.gec.wiki.pojo.resp.PageResp;
import com.gec.wiki.service.CategoryService;
import com.gec.wiki.mapper.CategoryMapper;
import com.gec.wiki.service.EbookService;
import com.gec.wiki.utils.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yzh
* @description 针对表【category】的数据库操作Service实现
* @createDate 2024-06-04 20:23:46
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Override
    public PageResp<CategoryResp> getCategoryListByPage(CategoryQueryReq req){
        Page<Category> page = new Page<>(req.getPage(), req.getSize());
        page = this.page(page);
        List<Category> list = page.getRecords();

        LOG.info("总行数：{}",page.getTotal()+"");
        LOG.info("总页数：{}",page.getPages()+"");
//  数据的格式化  把Category对象的数据转化  CategoryResp对象的
        List<CategoryResp> resps = CopyUtil.copyList(list, CategoryResp.class);
        PageResp<CategoryResp> pageResp = new PageResp<>();
        pageResp.setList(resps);
        pageResp.setTotal(page.getTotal());
        return pageResp;
    }

    @Override
    public List<CategoryResp> allList(CategoryQueryReq req) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        List<Category> list = this.list(wrapper);
        //格式化
        List<CategoryResp> categoryResps = CopyUtil.copyList(list, CategoryResp.class);
        return categoryResps;
    }

}




