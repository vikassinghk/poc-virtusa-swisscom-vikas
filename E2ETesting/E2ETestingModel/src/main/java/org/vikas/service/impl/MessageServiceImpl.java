package org.vikas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vikas.jpa.repository.MessageRepository;
import org.vikas.model.Message;
import org.vikas.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    public Message saveMsg(Message message) {
        return messageRepository.saveAndFlush(message);
    }

    public List<Message> getMsgByUserId(String userId) {
        return messageRepository.findByUserId(userId);
    }
}
