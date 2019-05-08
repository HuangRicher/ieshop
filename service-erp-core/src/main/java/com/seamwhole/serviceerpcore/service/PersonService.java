package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.Person;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface PersonService {


    Person getPerson(long id)throws Exception;

    List<Person> getPerson()throws Exception;

    List<Person> select(String name, String type, int offset, int rows)throws Exception;

    Long countPerson(String name, String type)throws Exception;

    int insertPerson(String beanJson, HttpServletRequest request)throws Exception;

    int updatePerson(String beanJson, Long id)throws Exception;

    int deletePerson(Long id)throws Exception;

    int batchDeletePerson(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    String getPersonByIds(String personIDs)throws Exception;

    List<Person> getPersonByType(String type)throws Exception;

    int batchDeletePersonByIds(String ids)throws Exception ;
    
    /**
     *  正常删除，要考虑数据完整性，进行完整性校验
     */
    int batchDeletePersonByIdsNormal(String ids) throws Exception;
}
