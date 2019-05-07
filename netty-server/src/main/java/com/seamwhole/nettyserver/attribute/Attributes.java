package com.seamwhole.nettyserver.attribute;

import io.netty.util.AttributeKey;
import com.seamwhole.nettyserver.session.Session;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
