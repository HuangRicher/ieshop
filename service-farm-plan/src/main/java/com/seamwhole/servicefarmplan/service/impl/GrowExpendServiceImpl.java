package com.seamwhole.servicefarmplan.service.impl;

import com.seamwhole.except.CheckException;
import com.seamwhole.servicefarmplan.mapper.GrowExpendMapper;
import com.seamwhole.servicefarmplan.mapper.ext.GrowExpendExtMapper;
import com.seamwhole.servicefarmplan.model.GrowExpend;
import com.seamwhole.servicefarmplan.model.GrowExpendExample;
import com.seamwhole.servicefarmplan.service.GrowExpendService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrowExpendServiceImpl implements GrowExpendService {

    @Autowired
    private GrowExpendMapper growExpendMapper;

    @Autowired
    private GrowExpendExtMapper growExpendExtMapper;



    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<GrowExpend> growExpendList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            GrowExpend growExpend = new GrowExpend();
            Integer productId=(int)(row.getCell(0).getNumericCellValue());
            Integer dayCount=(int)row.getCell(1).getNumericCellValue();
            String expendDetail=row.getCell(2).getStringCellValue();
            float weight=(float)row.getCell(3).getNumericCellValue();
            float addWeight=(float) (row.getCell(4).getNumericCellValue());

            growExpend.setProductId(productId);
            growExpend.setDayCount(dayCount);
            growExpend.setExpendDetail(expendDetail);
            growExpend.setWeight(weight);
            growExpend.setAddWeight(addWeight);
            growExpendList.add(growExpend);
        }
        growExpendExtMapper.batchInsert(growExpendList);
        return notNull;
    }

    @Override
    public List<GrowExpend> findByProductId(Integer productId) {
        GrowExpendExample example=new GrowExpendExample();
        example.createCriteria().andProductIdEqualTo(productId);
        return growExpendMapper.selectByExample(example);
    }

    @Override
    public void updateById(Integer id,String expendDetail, Float weight, Float addWeight) {
        GrowExpend expend=new GrowExpend();
        expend.setId(id);
        if(StringUtils.isNotBlank(expendDetail)){
            expend.setExpendDetail(expendDetail);
        }
        if(weight!=null && weight>0){
            expend.setWeight(weight);
        }
        if(addWeight!=null && addWeight>0){
            expend.setAddWeight(addWeight);
        }
        growExpendMapper.updateByPrimaryKeySelective(expend);
    }

    @Override
    public void delete(Integer id) {
        growExpendMapper.deleteByPrimaryKey(id);
    }
}
