package org.vikas.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vikas.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vikas.service.MessageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageControler {

    @Autowired
    private MessageService messageService;
    private static final Logger logger = LoggerFactory.getLogger(MessageControler.class);

    @PostMapping("/message")
    public Message saveMessage(@Valid @RequestBody Message message) {
        return messageService.saveMsg(message);
    }

    @GetMapping("/messages/{userId}")
    public List<Message> getMessageByUserId(@PathVariable(value = "userId") String userId) {
        return messageService.getMsgByUserId(userId);
    }
}
