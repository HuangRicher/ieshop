package com.seamwhole.serviceerpcore.mapper.ext;

import com.seamwhole.serviceerpcore.model.Person;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PersonExtMapper {

    List<Person> selectByConditionPerson(
            @Param("name") String name,
            @Param("type") String type,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long countsByPerson(
            @Param("name") String name,
            @Param("type") String type);

    int batchDeletePersonByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);
}