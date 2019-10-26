package cn.lhz.esc.controller;

import cn.lhz.esc.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Neo
 * @date 2019/10/26 21:15
 */
@RestController
@RequestMapping("/ope")
public class OperationController
{
    @Autowired
    private OperationService operationService;

    public OperationController()
    {
    }
}
