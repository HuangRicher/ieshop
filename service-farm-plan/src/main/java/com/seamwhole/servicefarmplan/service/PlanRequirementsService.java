package com.seamwhole.servicefarmplan.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicefarmplan.model.PlanRequirements;

public interface PlanRequirementsService {

    void add(String name,String creator);

    PageInfo<PlanRequirements> findByPage(Integer pageSize,Integer pageNum);


}
