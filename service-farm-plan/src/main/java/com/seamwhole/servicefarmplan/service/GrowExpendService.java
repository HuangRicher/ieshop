package com.seamwhole.servicefarmplan.service;

import com.seamwhole.servicefarmplan.model.GrowExpend;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GrowExpendService {

    boolean batchImport(String fileName, MultipartFile file)throws IOException;

    List<GrowExpend> findByProductId(Integer productId);

    void updateById(Integer id,String expendDetail,Float weight,Float addWeight);

    void delete(Integer id);
}
