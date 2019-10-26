package cn.lhz.esc.service.impl;

import cn.lhz.esc.dao.EscMapper;
import cn.lhz.esc.entity.Esc;
import cn.lhz.esc.service.EscService;
import cn.lhz.esc.utils.EscUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Neo
 * @date 2019/10/25 22:38
 */
@Service
public class EscServiceImpl implements EscService
{
    @Autowired
    private EscMapper escMapper;
    public EscServiceImpl()
    {
    }

    @Override
    public int addEsc(Esc esc)
    {
        esc.setEscPassword(EscUtil.encode(esc.getEscPassword()));
        esc.setEscPublicIp(EscUtil.encode(esc.getEscPublicIp()));
        esc.setEscPort(EscUtil.encode(esc.getEscPort()));
        esc.setEscUsername(EscUtil.encode(esc.getEscUsername()));
        esc.setEscCreateTime((new Date()).toString());
        return escMapper.addEsc(esc);
    }

    @Override
    public int updateEsc(Esc esc)
    {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public int deleteEsc(String escId)
    {
        throw new UnsupportedOperationException("Method not implemented.");
    }
}
