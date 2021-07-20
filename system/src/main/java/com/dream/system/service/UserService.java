package com.dream.system.service;

import com.dream.server.domain.User;
import com.dream.server.dto.LoginUserDto;
import com.dream.server.exception.BusinessException;
import com.dream.server.exception.BusinessExceptionCode;
import com.dream.server.mapper.UserMapper;
import com.dream.server.util.CopyUtil;
import com.dream.system.api.IUserService;
import com.dream.system.dao.UserDao;
import com.dream.system.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserDao userDao;

    @Resource
    private UserMapper userMapper;

    /**
     * 列表查询
     */
    @Override
    public void list(UserVo userVo) {
        PageHelper.startPage(userVo.getPage(), userVo.getSize());
        List<UserVo> userList = userDao.list(userVo);
        PageInfo<UserVo> pageInfo = new PageInfo<>(userList);
        userVo.setTotal(pageInfo.getTotal());
        userVo.setList(userList);

    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @Override
    public void save(UserVo userVo) {
        User user = CopyUtil.copy(userVo, User.class);
        if (StringUtils.isEmpty(userVo.getUserId())) {
            User userDo = selectByLoginName(userVo.getLoginName());
            if (userDo != null ){
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
            user.setCreateTime(new Date());
            this.insert(user);
        } else {
            user.setUpdateTime(new Date());
            this.update(user);
        }
    }

    /**
     * 新增
     */
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    /**
     * 更新
     */
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 登录
     *
     * @param userVo
     */
    @Override
    public LoginUserDto login(UserVo userVo) {
        //根据登录名查询
        User user = selectByLoginName(userVo.getLoginName());
        if (user != null && user.getPassword().equals(userVo.getPassword())) {
            return CopyUtil.copy(user, LoginUserDto.class);
        } else {
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
    }

    /**
     * 根据登录名查询user
     *
     * @param loginName
     * @return
     */
    private User selectByLoginName(String loginName) {
        return  userDao.selectByLoginName(loginName);
    }
}
