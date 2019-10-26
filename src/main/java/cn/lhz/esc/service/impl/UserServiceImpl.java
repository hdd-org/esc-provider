package cn.lhz.esc.service.impl;

import cn.lhz.esc.dao.UserMapper;
import cn.lhz.esc.entity.*;
import cn.lhz.esc.service.UserService;
import cn.lhz.esc.utils.EscUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Neo
 * @date 2019/10/24 0:17
 */
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;
    public UserServiceImpl()
    {
    }

    @Override
    public int addUser(User user)
    {

       return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(md5(user));
    }

    @Override
    public User selectUserByUserNamePassword(String username, String password)
    {
        return userMapper.selectUserByUserNamePassword(username,EscUtil.md5(password));
    }

    /**
     * 密码加密
     * @param user
     * @return
     */
    private User md5(User user){
        user.setUserPassword(EscUtil.md5(user.getUserPassword()));
        return user;
    }
}
