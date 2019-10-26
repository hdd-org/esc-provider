package cn.lhz.esc.service.impl;

import cn.lhz.esc.dao.ApplicationMapper;
import cn.lhz.esc.entity.Application;
import cn.lhz.esc.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Neo
 * @date 2019/10/26 21:08
 */
@Service
public class ApplicationServiceImpl implements ApplicationService
{
    @Autowired
    private ApplicationMapper applicationMapper;
    public ApplicationServiceImpl()
    {
    }

    @Override
    public int addApp(Application application)
    {
      return applicationMapper.addApp(application);
    }

    @Override
    public int deleteApp()
    {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public List<Application> selectAppByEscId(String escId)
    {
        return applicationMapper.selectAppByEscId(escId);
    }
}
