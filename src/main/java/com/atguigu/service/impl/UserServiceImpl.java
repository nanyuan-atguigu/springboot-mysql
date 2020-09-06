package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.domain.bean.User;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    //@Resource
     UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED,isolation= Isolation.REPEATABLE_READ)
    @Override
    public List<User> findAll() {

        return userDao.findAll();
    }
}

