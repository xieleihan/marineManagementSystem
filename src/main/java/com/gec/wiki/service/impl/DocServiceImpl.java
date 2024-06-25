package com.gec.wiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.wiki.pojo.Doc;
import com.gec.wiki.service.DocService;
import com.gec.wiki.mapper.DocMapper;
import org.springframework.stereotype.Service;

/**
* @author yzh
* @description 针对表【doc】的数据库操作Service实现
* @createDate 2024-06-04 20:23:46
*/
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc>
    implements DocService{

}




