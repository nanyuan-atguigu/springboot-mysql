package com.atguigu.dao;

import com.atguigu.domain.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface UserDao {
    //@Select("select * from user")
    List<User> findAll();
}
