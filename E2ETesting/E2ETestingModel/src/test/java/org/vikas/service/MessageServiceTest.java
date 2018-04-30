package org.vikas.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vikas.jpa.repository.MessageRepository;
import org.vikas.model.Message;
import org.vikas.service.impl.MessageServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTestMessagessByUserId() {
        Message message = new Message("TestUser", "Test text", "Test summary");
        List<Message> messageList = new ArrayList<Message>();
        messageList.add(message);
        when(messageRepository.findByUserId("TestUser")).thenReturn(messageList);
        List<Message> result = messageService.getMsgByUserId("TestUser");
        assertEquals("TestUser", result.get(0).getUserId());
        assertEquals("Test text", result.get(0).getText());
        assertEquals("Test summary", result.get(0).getSummary());
    }

    @Test
    public void saveTestMessage() {
        Message message = new Message("TestUser", "Test text", "Test summary");
        when(messageRepository.saveAndFlush(message)).thenReturn(message);
        Message result = messageService.saveMsg(message);
        assertEquals("TestUser", result.getUserId());
        assertEquals("Test text", result.getText());
        assertEquals("Test summary", result.getSummary());
    }

}