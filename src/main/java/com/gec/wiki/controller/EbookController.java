package com.gec.wiki.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.wiki.pojo.Ebook;
import com.gec.wiki.pojo.req.EbookQueryReq;
import com.gec.wiki.pojo.resp.CommonResp;
import com.gec.wiki.pojo.resp.EbookResp;
import com.gec.wiki.pojo.resp.PageResp;
import com.gec.wiki.service.EbookService;
import com.gec.wiki.utils.CopyUtil;
import com.gec.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    EbookService ebookService;

    @GetMapping("/getEbookByEbookReq")
    public CommonResp getEbookByEbookReq(EbookQueryReq req){
        List<Ebook> list;
        System.out.println("req = " + req);
        if (!ObjectUtils.isEmpty(req.getName())){
            QueryWrapper<Ebook> wrapper = new QueryWrapper<>();
            wrapper.like("name",req.getName());
            list = ebookService.list(wrapper);
        } else {
            list = ebookService.list();
        }
        List<EbookResp> ebookResps = CopyUtil.copyList(list, EbookResp.class);
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        resp.setContent(ebookResps);
        return resp;
    }


    //打印日志
    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
//  驼峰命名法，首字母不用大写， 后面每个字母都要大写。
    @GetMapping("/getEbookListByPage")
    public CommonResp getEbookListByPage(@Valid EbookQueryReq req){
        PageResp<EbookResp> list = ebookService.getEbookListByPage(req);
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        resp.setContent(list);
        return resp;
    }


    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody EbookResp rep){
        // 把参数格式化成Ebook
        Ebook ebook = CopyUtil.copy(rep, Ebook.class);
        //什么情况做新增  或者修改
        if(ObjectUtils.isEmpty(rep.getId())){
            //添加
            SnowFlake snowFlake = new SnowFlake();
            ebook.setId(snowFlake.nextId());
            ebookService.saveOrUpdate(ebook);
        }else {
            ebookService.saveOrUpdate(ebook);
        }
        CommonResp<Object> resp = new CommonResp<>();
        return resp;
    }
    @GetMapping("/remove")
    public CommonResp remove(long id){
        ebookService.removeById(id);
        CommonResp<Object> resp = new CommonResp<>();
        return resp;
    }



    @PostMapping("/uploadImage")
    public CommonResp uploadImage(@RequestParam(value = "file",required = false)MultipartFile file) throws IOException {
        String path = "D:\\实训项目\\web\\public\\image\\";
        String uploadImage = ebookService.uploadImage(file, path);
        CommonResp resp = new CommonResp<>();
        resp.setContent(uploadImage);
        return resp;
    }


}
