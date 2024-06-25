package com.gec.wiki.service;

import com.gec.wiki.pojo.Ebook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.wiki.pojo.req.CategoryQueryReq;
import com.gec.wiki.pojo.req.EbookQueryReq;
import com.gec.wiki.pojo.resp.CategoryResp;
import com.gec.wiki.pojo.resp.EbookResp;
import com.gec.wiki.pojo.resp.PageResp;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.invoke.MutableCallSite;
import java.nio.channels.MulticastChannel;

/**
* @author yzh
* @description 针对表【ebook(电子书)】的数据库操作Service
* @createDate 2024-06-04 20:23:46
*/
public interface EbookService extends IService<Ebook> {
    public PageResp<EbookResp> getEbookListByPage(EbookQueryReq req);
    public String uploadImage(MultipartFile file,String folder) throws IOException;
}
