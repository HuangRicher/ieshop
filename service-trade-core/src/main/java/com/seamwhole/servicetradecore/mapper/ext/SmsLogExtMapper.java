package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.model.SmsLog;
import org.apache.ibatis.annotations.Param;

public interface SmsLogExtMapper {

    SmsLog querySmsCodeByUserId(@Param("userId") Integer userId);
}
