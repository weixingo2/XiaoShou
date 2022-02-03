package com.cpt.service;

import com.cpt.entity.User;
import com.cpt.from.UserFrom;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    /**
     * 获取所有用户的信息
     * @return
     */
    List<User> selectUserList();

    User findUsername(String username);



    PageInfo<User> pageUserList(UserFrom from);


    void insert(String s);

    void delete(Integer id);
}
