package com.seamwhole.serviceerpcore.component;

import com.seamwhole.serviceerpcore.service.ResourceInfo;

import java.lang.annotation.*;

@ResourceInfo(value = "app", type = 15)
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AppResource {
}
