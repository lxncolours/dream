package com.dream.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.server.dto.LoginUserDto;
import com.dream.server.dto.ResponseVo;
import com.dream.server.util.UuidUtil;
import com.dream.server.util.ValidatorUtil;
import com.dream.system.api.IUserService;
import com.dream.system.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    public static final String BUSINESS_NAME = "用户管理";

    @Resource
    private IUserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseVo list(@RequestBody UserVo userVo) {
        ResponseVo responseVo = new ResponseVo();
        userService.list(userVo);
        responseVo.setContent(userVo);
        return responseVo;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseVo save(@RequestBody UserVo userVo) {
        userVo.setPassword(DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes()));
        // 保存校验
        ValidatorUtil.require(userVo.getLoginName(), "用户账号");
        ValidatorUtil.length(userVo.getLoginName(), "用户账号", 1, 16);
        ValidatorUtil.length(userVo.getName(), "用户名称", 1, 16);
        ValidatorUtil.require(userVo.getPassword(), "用户密码");
        ValidatorUtil.length(userVo.getPassword(), "用户密码", 1, 50);

        ResponseVo responseVo = new ResponseVo();
        userService.save(userVo);
        responseVo.setContent(userVo);
        return responseVo;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        ResponseVo responseVo = new ResponseVo();
//        int userId = Integer.valueOf(id);
        userService.delete(id);
        return responseVo;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseVo login(@RequestBody UserVo userVo, HttpServletRequest request) {
        LOG.info("用户登录开始");
        userVo.setPassword(DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes()));
        ResponseVo responseDto = new ResponseVo();

        // 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
        // String imageCode = (String) request.getSession().getAttribute(userDto.getImageCodeToken());
/*        String imageCode = (String) redisTemplate.opsForValue().get(userVo.getImageCodeToken());
        LOG.info("从redis中获取到的验证码：{}", imageCode);
        if (StringUtils.isEmpty(imageCode)) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码已过期");
            LOG.info("用户登录失败，验证码已过期");
            return responseDto;
        }
        if (!imageCode.toLowerCase().equals(userVo.getImageCode().toLowerCase())) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不对");
            LOG.info("用户登录失败，验证码不对");
            return responseDto;
        } else {
            // 验证通过后，移除验证码
//            request.getSession().removeAttribute(userDto.getImageCodeToken());
            redisTemplate.delete(userVo.getImageCodeToken());
        }*/

        LoginUserDto loginUserDto = userService.login(userVo);
        String token = UuidUtil.getShortUuid();
        loginUserDto.setToken(token);
        LOG.info("token:"+token);
        redisTemplate.opsForValue().set(token, JSON.toJSONString(loginUserDto), 3600, TimeUnit.SECONDS);
        responseDto.setContent(loginUserDto);
        return responseDto;
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout/{token}")
    public ResponseVo logout(@PathVariable String token) {
        ResponseVo responseVo = new ResponseVo();
        redisTemplate.delete(token);
        LOG.info("从redis中删除token:{}", token);
        return responseVo;
    }



}
