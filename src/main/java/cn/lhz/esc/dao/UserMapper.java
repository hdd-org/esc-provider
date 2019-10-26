package cn.lhz.esc.dao;

import cn.lhz.esc.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
int addUser(User user);

User selectUserByUserNamePassword(@Param("userUsername")String userName, @Param("userPassword")String password);

int updateUser(User user);


}