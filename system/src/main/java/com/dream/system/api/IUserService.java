package com.dream.system.api;


import com.dream.server.domain.User;
import com.dream.server.dto.LoginUserDto;
import com.dream.system.vo.UserVo;

public interface IUserService {

    /**
     * 列表查询
     */
    void list(UserVo userVo);

    /**
     * 保存，id有值时更新，无值时新增
     */
    void save(UserVo userVo);

    /**
     * 新增
     */
    void insert(User user);

    /**
     * 更新
     */
    void update(User user);

    /**
     * 删除
     */
    void delete(Integer id);

    /**
     * 登录
     *
     * @param userVo 用户实体
     * @return {@link LoginUserDto}
     */
    LoginUserDto login(UserVo userVo);


}