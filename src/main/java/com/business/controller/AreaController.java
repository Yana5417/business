package com.business.controller;

import java.util.List;

import com.business.domain.Area;
import com.business.domain.ResResult;
import com.business.domain.ResultCode;
import com.business.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
@RestController
public class AreaController {
    @Autowired
    private AreaService areaService;
    @GetMapping("/area/list")
    public ResResult listArea(@RequestParam("parentAreaId") String parentAreaId){
        List<Area> list = areaService.listAreaByParentId(parentAreaId);
        return new ResResult(ResultCode.SUCCESS,list);
    }
}
