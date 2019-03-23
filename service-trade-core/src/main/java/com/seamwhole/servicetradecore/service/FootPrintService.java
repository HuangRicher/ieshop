package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.mapper.model.FootPrintDO;
import com.seamwhole.servicetradecore.model.FootPrint;

import java.util.List;
import java.util.Map;


public interface FootPrintService {

    FootPrint queryObject(Integer id);

    List<FootPrintDO> queryListFootprint(Integer userid);

    List<FootPrintDO> shareList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(FootPrint footprint);

    void deleteByParam(Map<String, Object> map);
}
