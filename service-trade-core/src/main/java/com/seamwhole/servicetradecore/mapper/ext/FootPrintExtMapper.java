package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.mapper.model.FootPrintDO;
import com.seamwhole.servicetradecore.mapper.model.ShopFootPrintDO;

import java.util.List;
import java.util.Map;

public interface FootPrintExtMapper {

    List<FootPrintDO> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    List<FootPrintDO> queryListFootprint(Integer userid);

    List<FootPrintDO> shareList(Map<String, Object> map);

    List<ShopFootPrintDO> queryShopFootPrintList(Map<String, Object> map);
}
