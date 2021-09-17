package com.dream.system.dao;

import com.dream.server.domain.User;
import com.dream.system.vo.UserVo;

import java.util.List;


public interface UserDao {

    List<UserVo> list(UserVo user);

    User selectByLoginName(String loginName);

    void updateUser();

}