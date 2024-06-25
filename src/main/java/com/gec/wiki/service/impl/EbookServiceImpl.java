package com.gec.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.wiki.exception.BusinessException;
import com.gec.wiki.exception.BusinessExceptionCode;
import com.gec.wiki.pojo.Ebook;
import com.gec.wiki.pojo.req.EbookQueryReq;
import com.gec.wiki.pojo.resp.EbookResp;
import com.gec.wiki.pojo.resp.PageResp;
import com.gec.wiki.service.EbookService;
import com.gec.wiki.mapper.EbookMapper;
import com.gec.wiki.utils.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
* @author yzh
* @description 针对表【ebook(电子书)】的数据库操作Service实现
* @createDate 2024-06-04 20:23:46
*/
@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook>
    implements EbookService{

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Override
    public PageResp<EbookResp> getEbookListByPage(EbookQueryReq req) {
        Page<Ebook> page = new Page<>(req.getPage(), req.getSize());
        QueryWrapper<Ebook> wrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(req.getName())&&req.getName()!=""){
            //分类id不为null和不等于0
            wrapper.like("name",req.getName());
        }
        if(!ObjectUtils.isEmpty(req.getCategory2Id())&&req.getCategory2Id()!=0){
            //分类id不为null和不等于0
            wrapper.like("category2_id",req.getCategory2Id());
        }
        page = this.page(page,wrapper);
        List<Ebook> list = page.getRecords();

        LOG.info("总行数：{}",page.getTotal()+"");
        LOG.info("总页数：{}",page.getPages()+"");
//  数据的格式化  把Ebook对象的数据转化  EbookResp对象的
        List<EbookResp> resps = CopyUtil.copyList(list, EbookResp.class);
        PageResp<EbookResp> pageResp = new PageResp<>();
        pageResp.setTotal(page.getTotal());
        pageResp.setList(resps);
        return pageResp;
    }

    @Override
    public String uploadImage(MultipartFile file, String folder) throws IOException {
        //1.有没有上次图片
        if(file == null){
            throw new BusinessException(BusinessExceptionCode.Select_Upload_Image);
        }
        //2.上次的图片不能大于10m
        if(file.getSize()>1024*1024*10){
            throw new BusinessException(BusinessExceptionCode.Files_Larger_Than_10M);
        }
        //3.图片格式是否符合
        //获取文件的后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
                file.getOriginalFilename().length());
        if(!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())){
            throw new BusinessException(BusinessExceptionCode.Files_Wrong_Format);
        }
        //4.图片是否保存成功
        String savePath = folder;
        File savePathFile = new File(savePath);
        //这个目录存不存在
        if(!savePathFile.exists()){
            //不存目录则创建目录
            savePathFile.mkdir();
        }
        //使用UUid生成文件名
        String filename =  UUID.randomUUID().toString().replace("-","")+"."+suffix;


        //保存文件
        try {
            file.transferTo(new File(savePath+filename));
        }catch (IOException e){
            throw new BusinessException(BusinessExceptionCode.Save_File_Exception);
        }
        LOG.info("文件名{}",filename);
        //返回文件名称
        return filename;
    }

}




