package com.seamwhole.nettyserver.protocol.response;


import com.seamwhole.nettyserver.protocol.Packet;
import com.seamwhole.nettyserver.session.Session;

import java.util.List;

import static com.seamwhole.nettyserver.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;


public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
