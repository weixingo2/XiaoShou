package com.cpt.mapper;

import com.cpt.entity.User;
import com.cpt.from.UserFrom;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
     /**
     * 获取所有用户的信息
     * @return
     */
     List<User> selectUserList();

     @Select("select * from t_user u where u.username=#{username}")
     User findUsername(String username);



     List<User> pageUserList(@Param("username") String username);

   @Insert("insert into t_user(username)values(#{username})")
    void insert(String s);

   @Delete("delete t.* from t_user t where t.id=#{id}")
    void delete(Integer id);
}
