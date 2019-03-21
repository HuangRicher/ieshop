package com.seamwhole.serviceuaa.mapper.ext;

import com.seamwhole.serviceuaa.domain.UserDetail;
import org.apache.ibatis.annotations.Param;

public interface UserExtMapper {

    UserDetail getByUserSerial(@Param("userSerial")Integer userSerial);
}
