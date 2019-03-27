package com.seamwhole.servicetradecore.cache;

import com.seamwhole.servicetradecore.mapper.model.SysRegionDO;
import com.seamwhole.servicetradecore.service.SysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component
public class RegionCacheUtil implements CommandLineRunner {

    public static List<SysRegionDO> sysRegionEntityList;

    @Autowired
    private SysRegionService sysRegionService;


    @Override
    public void run(String... args) throws Exception {
        sysRegionEntityList = sysRegionService.queryList(new HashMap<String, Object>());
    }
    /**
     * 获取所有国家
     *
     * @return
     */
    public static List<SysRegionDO> getAllCountry() {
        List<SysRegionDO> resultObj = new ArrayList<>();
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (areaVo.getType().equals(0)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取全部省份
     *
     * @return
     */
    public static List<SysRegionDO> getAllProvice() {
        List<SysRegionDO> resultObj = new ArrayList<>();
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (areaVo.getType().equals(1)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取所有城市
     *
     * @return
     */
    public static List<SysRegionDO> getAllCity() {
        List<SysRegionDO> resultObj = new ArrayList<>();
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (areaVo.getType().equals(2)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据国家获取全部省份
     *
     * @return
     */
    public static List<SysRegionDO> getAllProviceByParentId(Integer areaId) {
        List<SysRegionDO> resultObj = new ArrayList<>();
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(1) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取地市
     *
     * @return
     */
    public static List<SysRegionDO> getChildrenCity(Integer areaId) {
        List<SysRegionDO> resultObj = new ArrayList<>();
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(2) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取地市
     *
     * @return
     */
    public static List<SysRegionDO> getChildrenCity(String proviceName) {
        List<SysRegionDO> resultObj = new ArrayList<>();
        if (null == proviceName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(2) && proviceName.equals(areaVo.getParentName())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区县
     *
     * @return
     */
    public static List<SysRegionDO> getChildrenDistrict(Integer areaId) {
        List<SysRegionDO> resultObj = new ArrayList<>();
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(3) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区县
     *
     * @return
     */
    public static List<SysRegionDO> getChildrenDistrict(String provinceName, String cityName) {
        List<SysRegionDO> resultObj = new ArrayList<>();
        if (null == provinceName || null == cityName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(3)
                        && cityName.equals(areaVo.getParentName())
                        && null != getAreaByAreaId(areaVo.getParentId())
                        && provinceName.equals(getAreaByAreaId(areaVo.getParentId()).getParentName())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }


    /**
     * 获取区县
     *
     * @return
     */
    public static List<SysRegionDO> getChildrenByParentId(Integer parentId) {
        List<SysRegionDO> resultObj = new ArrayList<>();
        if (null == parentId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (null != areaVo.getParentId() && parentId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区域名称
     *
     * @return
     */
    public static String getAreaNameByAreaId(Integer areaId) {
        if (null == areaId) {
            return "";
        }
        String resultObj = areaId.toString();
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (areaVo.getId().equals(areaId)) {
                    resultObj = areaVo.getName();
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static SysRegionDO getAreaByAreaId(Integer areaId) {
        SysRegionDO resultObj = null;
        if (null == areaId) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (areaVo.getId().equals(areaId)) {
                    resultObj = areaVo;
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static Integer getProvinceIdByName(String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (areaVo.getType() == 1 && areaVo.getName().equals(areaName)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static Integer getCityIdByName(Integer provinceId, String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (areaVo.getType() == 2 && areaVo.getName().equals(areaName)
                        && areaVo.getParentId().equals(provinceId)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }


    /**
     * 根据Id获取区域
     *
     * @return
     */
    public static Integer getDistrictIdByName(Integer provinceId, Integer cityId, String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != sysRegionEntityList) {
            for (SysRegionDO areaVo : sysRegionEntityList) {
                if (areaVo.getType() == 3 && areaVo.getName().equals(areaName)
                        && areaVo.getParentId().equals(cityId)
                        && null != getAreaByAreaId(areaVo.getParentId())
                        && null != getAreaByAreaId(areaVo.getParentId()).getParentId()
                        && getAreaByAreaId(areaVo.getParentId()).getParentId().equals(provinceId)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }

}