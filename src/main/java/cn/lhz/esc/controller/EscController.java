package cn.lhz.esc.controller;

import cn.lhz.esc.service.EscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Neo
 * @date 2019/10/20 22:33
 */
@RestController
@RequestMapping("/esc")
public class EscController
{
    @Autowired
    private EscService escService;
    public EscController()
    {
    }


}
