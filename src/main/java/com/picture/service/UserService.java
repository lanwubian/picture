package com.picture.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.picture.model.dto.user.UserQueryRequest;
import com.picture.model.entrty.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.picture.model.vo.user.LoginUserVO;
import com.picture.model.vo.user.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author lanwu
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2026-10-19 17:03:14
*/
public interface UserService extends IService<User> {

/**
 * 用户注册方法
 * @param userAccount 用户账号
 * @param userPassword 用户密码
 * @param checkPassword 确认密码
 * @return 返回long类型结果，可能是用户ID或其他状态标识
 */
    long userRegister(String userAccount, String userPassword, String checkPassword);

/**
 * 加密用户密码的方法
 * @param userPassword 用户输入的原始密码
 * @return 加密后的密码字符串
 */
    String getEncryptPassword(String userPassword);

/**
 * 用户登录方法
 * @param userAccount 用户账号
 * @param userPassword 用户密码
 * @param request HTTP请求对象，用于获取请求相关信息
 * @return 登录成功返回LoginUserVO对象，包含用户登录信息
 */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getLoginUser(HttpServletRequest request);

    LoginUserVO getLoginUserVO(User user);

    boolean userLogout(HttpServletRequest request);

    UserVO getUserVO(User user);

    List<UserVO> getUserVOList(List<User> userList);

    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
