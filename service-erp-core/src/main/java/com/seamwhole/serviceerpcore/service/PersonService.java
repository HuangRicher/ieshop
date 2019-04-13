package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.Person;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface PersonService {


    Person getPerson(long id);

    List<Person> getPerson();

    List<Person> select(String name, String type, int offset, int rows);

    Long countPerson(String name, String type);

    int insertPerson(String beanJson, HttpServletRequest request);

    int updatePerson(String beanJson, Long id);

    int deletePerson(Long id);

    int batchDeletePerson(String ids);

    int checkIsNameExist(Long id, String name);

    String getPersonByIds(String personIDs);

    List<Person> getPersonByType(String type);

    int batchDeletePersonByIds(String ids) ;
    
    /**
     *  正常删除，要考虑数据完整性，进行完整性校验
     */
    int batchDeletePersonByIdsNormal(String ids) throws Exception;
}
