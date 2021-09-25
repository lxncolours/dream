package com.dream.business.vo;


import com.dream.server.dto.PageVo;

import java.io.Serializable;

/**
 * 用户实体
 *
 * @author Liberica
 * @date 2021-07-17
 */
public class UserVo extends PageVo<UserVo> implements Serializable {

    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = -7501642711032175787L;

    /**
     * 用户id
     */
    private Integer userId;


    /**
     * 登录名
     */
    private String loginName;


    /**
     * 的名字
     */
    private String name;


    private String password;

    private String createTime;

    private String updateTime;

    /**
     * 得到用户id
     *
     * @return {@link Integer}
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获得登录名
     *
     * @return {@link String}
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 得到的名字
     *
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * 集名称
     *
     * @param name 的名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 得到密码
     *
     * @return {@link String}
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 有创建的时间
     *
     * @return {@link String}
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 有更新的时间
     *
     * @return {@link String}
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


    /**
     * 字符串
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", loginName=").append(loginName);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append("]");
        return sb.toString();
    }

}