package cn.lhz.esc.service.impl;

import cn.lhz.esc.dao.OperationMapper;
import cn.lhz.esc.entity.Operation;
import cn.lhz.esc.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Neo
 * @date 2019/10/26 21:10
 */
@Service
public class OperationServiceImpl implements OperationService
{
    @Autowired
    private OperationMapper operationMapper;

    public OperationServiceImpl()
    {
    }

    @Override
    public int insertOpe(Operation operation)
    {
        return operationMapper.insertOpe(operation);
    }
}
