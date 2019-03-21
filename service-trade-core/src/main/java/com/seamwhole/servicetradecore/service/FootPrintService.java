package com.seamwhole.servicetradecore.service;

import com.seamwhole.servicetradecore.model.FootPrint;

import java.util.List;
import java.util.Map;


public interface FootPrintService {

    FootPrint queryObject(Integer id);

    List<FootPrint> queryList(Map<String, Object> map);

    List<FootPrint> queryListFootprint(String userid);

    List<FootPrint> shareList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(FootPrint footprint);

    void update(FootPrint footprint);

    void delete(Integer id);

    void deleteByParam(Map<String, Object> map);

    void deleteBatch(Integer[] ids);

}
