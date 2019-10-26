package cn.lhz.esc.controller;

import cn.lhz.esc.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Neo
 * @date 2019/10/26 21:14
 */
@RestController
@RequestMapping("/app")
public class ApplicationController
{
    @Autowired
    private ApplicationService applicationService;
    public ApplicationController()
    {
    }
}
