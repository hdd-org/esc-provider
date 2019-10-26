package cn.lhz.esc.dao;

import cn.lhz.esc.entity.Esc;

public interface EscMapper {

    int addEsc(Esc esc);

    int updateEsc(Esc esc);

    int deleteEsc(String escId);

}