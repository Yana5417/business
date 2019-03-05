package com.business.service;

import java.util.List;

import com.business.domain.Area;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
public interface AreaService {
    List<Area> listAreaByParentId(String parentAreaId);
}
