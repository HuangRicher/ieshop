package com.seamwhole.servicefarmplan.service;

import com.seamwhole.servicefarmplan.model.PlanRequirementsItem;

import java.util.List;

public interface PlanRequirementsItemService {

    void addBatch(List<PlanRequirementsItem> items);

    List<PlanRequirementsItem> findByPlanId(String planId);

    void update();
}
