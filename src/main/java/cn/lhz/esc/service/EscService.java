package cn.lhz.esc.service;

import cn.lhz.esc.entity.Esc;

/**
 * @author Neo
 * @date 2019/10/25 22:20
 */
public interface EscService
{
    int addEsc(Esc esc);

    int updateEsc(Esc esc);

    int deleteEsc(String escId);

}