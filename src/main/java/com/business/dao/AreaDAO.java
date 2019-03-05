package com.business.dao;

import java.util.List;

import com.business.domain.Area;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
@Mapper
public interface AreaDAO {
    List<Area> listAreasByParentId(String parentAreaId);
}
