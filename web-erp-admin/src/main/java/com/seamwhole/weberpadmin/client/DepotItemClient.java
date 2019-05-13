package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.DepotItemClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.Constants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.DepotItemStockWarningCount;
import com.seamwhole.weberpadmin.domain.DepotItemVo4WithInfoEx;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = DepotItemClientHystrix.class)
public interface DepotItemClient {

    /**
     * 根据材料信息获取
     * @param materialParam  商品参数
     * @param depotIds  拥有的仓库信息
     */
    @GetMapping(value = "/depotItem/getHeaderIdByMaterial")
    BaseResponseInfo getHeaderIdByMaterial(@RequestParam("materialParam") String materialParam,
                                           @RequestParam("depotIds") String depotIds);

    /**
     * 只根据商品id查询单据列表
     */
    @GetMapping(value = "/depotItem/findDetailByTypeAndMaterialId/{pageSize}/{currentPage}/{materialId}")
    String findDetailByTypeAndMaterialId(@PathVariable(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                         @PathVariable(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                                         @PathVariable("materialId") String mId);

    /**
     * 根据商品id和仓库id查询库存数量
     */
    @GetMapping(value = "/depotItem/findStockNumById/{pageSize}/{currentPage}/{projectId}/{materialId}/{monthTime}")
    String findStockNumById(@PathVariable(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                            @PathVariable(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                            @PathVariable("projectId") Integer pid,
                            @PathVariable("materialId") String mId,
                            @PathVariable("monthTime") String monthTime);

    /**
     * 只根据商品id查询库存数量
     */
    @GetMapping(value = "/depotItem/findStockNumByMaterialId/{pageSize}/{currentPage}/{materialId}/{monthTime}")
    String findStockNumByMaterialId(@PathVariable(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                    @PathVariable(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                                    @PathVariable("materialId") String mId,
                                    @PathVariable("monthTime") String monthTime);


    /**
     * 保存明细
     */
    @PostMapping(value = "/depotItem/saveDetails/{inserted}/{deleted}/{updated}/{headerId}")
    String saveDetails(@PathVariable("inserted") String inserted,
                       @PathVariable("deleted") String deleted,
                       @PathVariable("updated") String updated,
                       @PathVariable("headerId") Long headerId);


    @GetMapping(value = "/depotItem/getDetailList")
    BaseResponseInfo getDetailList(@RequestParam("headerId") Long headerId,
                                   @RequestParam("mpList") String mpList);


    /**
     * 查找所有的明细
     */
    @GetMapping(value = "/depotItem/findByAllByPage/{currentPage}/{pageSize}/{projectId}/{monthTime}/{headIds}/{materialIds}/{mpList}")
    BaseResponseInfo findByAllByPage(@PathVariable("currentPage") Integer currentPage,
                               @PathVariable("pageSize") Integer pageSize,
                               @PathVariable("projectId") Integer projectId,
                               @PathVariable("monthTime") String monthTime,
                               @PathVariable("headIds") String headIds,
                               @PathVariable("materialIds") String materialIds,
                               @PathVariable("mpList") String mpList);

    /**
     * 统计总计金额
     */
    @GetMapping(value = "/depotItem/totalCountMoney/{projectId}/{monthTime}/{headIds}/{materialIds}")
    BaseResponseInfo totalCountMoney(@PathVariable("projectId") Integer pid,
                                     @PathVariable("monthTime") String monthTime,
                                     @PathVariable("headIds") String headIds,
                                     @PathVariable("materialIds") String materialIds) ;

    /**
     * 进货统计
     */
    @GetMapping(value = "/depotItem/buyIn/{currentPage}/{pageSize}/{monthTime}/{headIds}/{materialIds}/{mpList}")
    BaseResponseInfo buyIn(@PathVariable("currentPage") Integer currentPage,
                           @PathVariable("pageSize") Integer pageSize,
                           @PathVariable("monthTime") String monthTime,
                           @PathVariable("headIds") String headIds,
                           @PathVariable("materialIds") String materialIds,
                           @PathVariable("mpList") String mpList);

    /**
     * 销售统计
     */
    @GetMapping(value = "/depotItem/saleOut/{currentPage}/{pageSize}/{monthTime}/{headIds}/{materialIds}/{mpList}")
    BaseResponseInfo saleOut(@PathVariable("currentPage") Integer currentPage,
                             @PathVariable("pageSize") Integer pageSize,
                             @PathVariable("monthTime") String monthTime,
                             @PathVariable("headIds") String headIds,
                             @PathVariable("materialIds") String materialIds,
                             @PathVariable("mpList") String mpList);

    /**
     * 导出excel表格
     */
    @GetMapping(value = "/depotItem/exportExcel/{currentPage}/{pageSize}/{projectId}/{monthTime}/{headIds}/{materialIds}")
    BaseResponseInfo exportExcel(@PathVariable("currentPage") Integer currentPage,
                                 @PathVariable("pageSize") Integer pageSize,
                                 @PathVariable("projectId") Integer projectId,
                                 @PathVariable("monthTime") String monthTime,
                                 @PathVariable("headIds") String headIds,
                                 @PathVariable("materialIds") String materialIds);


    /**
     *  批量删除单据明细信息
     */
    @PostMapping(value = "/depotItem/batchDeleteDepotItemByIds")
    Object batchDeleteDepotItemByIds(@RequestParam("ids") String ids) ;


    /**
     * 库存预警报表
     */
    @GetMapping(value = "/depotItem/findStockWarningCountByPage/{currentPage}/{pageSize}/{projectId}")
   BaseResponseInfo findStockWarningCountByPage(@PathVariable("currentPage") Integer currentPage,
                                          @PathVariable("pageSize") Integer pageSize,
                                          @PathVariable("projectId") Integer pid );
    /**
     * 导出库存预警excel表格
     */
    @GetMapping(value = "/depotItem/exportWarningExcel/{currentPage}/{pageSize}/{projectId}")
    BaseResponseInfo exportWarningExcel(@PathVariable("currentPage") Integer currentPage,
                                        @PathVariable("pageSize") Integer pageSize,
                                        @PathVariable("projectId") Integer projectId);


    @GetMapping(value = "/depotItem/findByAll/{headIds}/{materialIds}/{count}/{pageSize}")
    List<DepotItemVo4WithInfoEx> findByAll(@PathVariable("headIds") String headIds,
                                           @PathVariable("materialIds")String materialIds,
                                           @PathVariable("count")Integer count,
                                           @PathVariable("pageSize")Integer pageSize);

    @GetMapping(value = "/depotItem/findByAll/{type}/{projectId}/{mId}/{monthTime}/{isPrev}")
    BigDecimal findByType(@PathVariable("type")String type,
                          @PathVariable("projectId")Integer projectId,
                          @PathVariable("mId")Long mId,
                          @PathVariable("monthTime")String monthTime,
                          @PathVariable("isPrev")Boolean isPrev);



    @GetMapping(value = "/depotItem/findByAll/{subType}/{mType}/{projectId}/{mId}/{monthTime}/{isPrev}")
    BigDecimal findAssembleByType(@PathVariable("subType")String subType,
                                  @PathVariable("mType")String mType,
                                  @PathVariable("projectId")Integer projectId,
                                  @PathVariable("mId")Long mId,
                                  @PathVariable("monthTime")String monthTime,
                                  @PathVariable("isPrev")Boolean isPrev);


    @GetMapping(value = "/depotItem/findByAll/{type}/{projectId}/{mId}/{monthTime}/{isPrev}")
    BigDecimal findPriceByType(@PathVariable("type")String type,
                               @PathVariable("projectId")Integer projectId,
                               @PathVariable("mId")Long mId,
                               @PathVariable("monthTime")String monthTime,
                               @PathVariable("isPrev")Boolean isPrev);


    @GetMapping(value = "/depotItem/findByAll/{type}/{subType}/{mId}/{monthTime}/{sumType}")
    BigDecimal buyOrSale(@PathVariable("type")String type,
                         @PathVariable("subType")String subType,
                         @PathVariable("mId")Long mId,
                         @PathVariable("monthTime")String monthTime,
                         @PathVariable("sumType")String sumType);


    @GetMapping(value = "/depotItem/findByAll/{count}/{pageSize}/{projectId}")
    List<DepotItemStockWarningCount> findStockWarningCount(@PathVariable("count") Integer count,
                                                           @PathVariable("pageSize")Integer pageSize,
                                                           @PathVariable("projectId")Integer projectId);
}
