package com.gec.wiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.wiki.mapper.UserMapper;
import com.gec.wiki.pojo.User;
import com.gec.wiki.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author yzh
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-06-04 20:23:47
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




