package com.cpt.service.Impl;

import com.cpt.entity.User;
import com.cpt.from.UserFrom;
import com.cpt.mapper.UserMapper;
import com.cpt.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public User findUsername(String username) {
        return userMapper.findUsername(username);
    }



    @Override
    public  PageInfo<User> pageUserList(UserFrom from) {
        PageHelper.startPage(from.getPage(),from.getCount());
        return new PageInfo<>(userMapper.pageUserList(from.getUsername()));
    }

}
