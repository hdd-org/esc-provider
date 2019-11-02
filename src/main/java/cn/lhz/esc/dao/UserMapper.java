package cn.lhz.esc.dao;

import cn.lhz.esc.entity.User;

public interface UserMapper {
int addUser(User user);

User selectUserByUserNamePassword(User user);

int updateUser(User user);


}