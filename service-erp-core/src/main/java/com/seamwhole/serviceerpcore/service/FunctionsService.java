package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.Functions;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface FunctionsService {


    Functions getFunctions(long id);

    List<Functions> getFunctions() ;

    List<Functions> select(String name, String type, int offset, int rows);

    Long countFunctions(String name, String type);

    int insertFunctions(String beanJson, HttpServletRequest request);

    int updateFunctions(String beanJson, Long id);

    int deleteFunctions(Long id);

    int batchDeleteFunctions(String ids);

    int checkIsNameExist(Long id, String name);

    List<Functions> getRoleFunctions(String pNumber);

    List<Functions> findRoleFunctions(String pnumber);

    List<Functions> findByIds(String functionsIds);

    int batchDeleteFunctionsByIds(String ids);
}
