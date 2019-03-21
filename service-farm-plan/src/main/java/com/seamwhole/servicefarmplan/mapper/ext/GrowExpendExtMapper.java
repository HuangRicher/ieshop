package com.seamwhole.servicefarmplan.mapper.ext;

import com.seamwhole.servicefarmplan.model.GrowExpend;

import java.util.List;

public interface GrowExpendExtMapper {
    void batchInsert(List<GrowExpend> list);
}