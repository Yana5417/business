package com.business.service.impl;

import java.util.List;

import com.business.dao.AreaDAO;
import com.business.domain.Area;
import com.business.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
@Service
public class AreaServiceImpl implements AreaService{
    @Autowired
    private AreaDAO areaDAO;

    @Override
    public List<Area> listAreaByParentId(String parentAreaId) {
        return areaDAO.listAreasByParentId(parentAreaId);
    }
}
