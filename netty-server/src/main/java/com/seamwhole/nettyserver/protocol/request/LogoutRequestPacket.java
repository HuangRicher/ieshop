package com.seamwhole.nettyserver.protocol.request;

import com.seamwhole.nettyserver.protocol.Packet;

import static com.seamwhole.nettyserver.protocol.command.Command.LOGOUT_REQUEST;


public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}
