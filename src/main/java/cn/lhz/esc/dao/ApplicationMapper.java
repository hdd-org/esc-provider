package cn.lhz.esc.dao;

import cn.lhz.esc.entity.Application;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplicationMapper {

    int addApp(Application application);

    int deleteApp();

    List<Application> selectAppByEscId(@Param("escId") String escId);
}