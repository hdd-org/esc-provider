package cn.lhz.esc.controller;

import cn.lhz.esc.entity.User;
import cn.lhz.esc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Neo
 * @date 2019/10/24 0:21
 */
@RequestMapping("/user")
@RestController
public class UserController
{
    @Autowired
    private UserService userService;
    public UserController()
    {
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public int addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
