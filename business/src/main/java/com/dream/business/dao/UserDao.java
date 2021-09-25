package com.dream.business.dao;

import com.dream.business.vo.UserVo;
import com.dream.server.domain.User;

import java.util.List;


public interface UserDao {

    List<UserVo> list(UserVo user);

    User selectByLoginName(String loginName);

    void updateUser();

}