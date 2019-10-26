package cn.lhz.esc.service;

import cn.lhz.esc.entity.Application;

import java.util.List;

/**
 * @author Neo
 * @date 2019/10/25 22:21
 */
public interface ApplicationService
{
    int addApp(Application application);

    int deleteApp();

   List<Application> selectAppByEscId(String escId);

}