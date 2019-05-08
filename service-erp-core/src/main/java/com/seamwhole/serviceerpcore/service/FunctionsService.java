package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.Functions;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface FunctionsService {


    Functions getFunctions(long id)throws Exception;

    List<Functions> getFunctions() throws Exception;

    List<Functions> select(String name, String type, int offset, int rows)throws Exception;

    Long countFunctions(String name, String type)throws Exception;

    int insertFunctions(String beanJson, HttpServletRequest request)throws Exception;

    int updateFunctions(String beanJson, Long id)throws Exception;

    int deleteFunctions(Long id)throws Exception;

    int batchDeleteFunctions(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    List<Functions> getRoleFunctions(String pNumber)throws Exception;

    List<Functions> findRoleFunctions(String pnumber)throws Exception;

    List<Functions> findByIds(String functionsIds)throws Exception;

    int batchDeleteFunctionsByIds(String ids)throws Exception;
}
