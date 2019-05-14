package com.seamwhole.serviceerpcore.component;

import com.seamwhole.serviceerpcore.service.ResourceInfo;

import java.lang.annotation.*;


@ResourceInfo(value = "serialNumber", type = 105)
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SerialNumberResource {
}
