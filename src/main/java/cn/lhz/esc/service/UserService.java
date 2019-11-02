package cn.lhz.esc.service;

import cn.lhz.esc.entity.User;

/**
 * @author Neo
 * @date 2019/10/24 0:15
 */
public interface UserService
{
    int addUser(User user);

    int updateUser(User user);

    User selectUserByUserNamePassword(User user);

}