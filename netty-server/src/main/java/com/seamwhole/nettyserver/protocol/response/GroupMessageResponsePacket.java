package com.seamwhole.nettyserver.protocol.response;

import com.seamwhole.nettyserver.protocol.Packet;
import com.seamwhole.nettyserver.session.Session;

import static com.seamwhole.nettyserver.protocol.command.Command.GROUP_MESSAGE_RESPONSE;


public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    public String getFromGroupId() {
        return fromGroupId;
    }

    public void setFromGroupId(String fromGroupId) {
        this.fromGroupId = fromGroupId;
    }

    public Session getFromUser() {
        return fromUser;
    }

    public void setFromUser(Session fromUser) {
        this.fromUser = fromUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {

        return GROUP_MESSAGE_RESPONSE;
    }
}
