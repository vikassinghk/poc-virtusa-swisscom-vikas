package org.vikas.service;

import org.vikas.model.Message;

import java.util.List;

public interface MessageService {

    public Message saveMsg(Message message);

    public List<Message> getMsgByUserId(String userId);
}
