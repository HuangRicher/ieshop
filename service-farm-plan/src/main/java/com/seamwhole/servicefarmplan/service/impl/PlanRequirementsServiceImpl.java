package com.seamwhole.servicefarmplan.service.impl;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicefarmplan.mapper.PlanRequirementsMapper;
import com.seamwhole.servicefarmplan.model.PlanRequirements;
import com.seamwhole.servicefarmplan.service.PlanRequirementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanRequirementsServiceImpl implements PlanRequirementsService {

    @Autowired
    private PlanRequirementsMapper planRequirementsMapper;


    @Override
    public void add(String name, String creator) {

    }

    @Override
    public PageInfo<PlanRequirements> findByPage(Integer pageSize, Integer pageNum) {
        return null;
    }
}
