package com.seamwhole.serviceerpcore.component;

import com.seamwhole.serviceerpcore.service.ResourceInfo;

import java.lang.annotation.*;

@ResourceInfo(value = "unit", type = 40)
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UnitResource {
}
