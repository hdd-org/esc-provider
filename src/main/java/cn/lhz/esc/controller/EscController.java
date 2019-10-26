package cn.lhz.esc.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author Neo
 * @date 2019/10/20 22:33
 */
@RestController
public class EscController
{
    public EscController()
    {
    }

    @RequestMapping("/")
        public String index(){
            return "123";

        }
}
