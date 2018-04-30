package org.vikas.web;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.vikas.E2EModalApplication;
import org.vikas.jpa.repository.MessageRepository;
import org.vikas.model.Message;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = E2EModalApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageControlerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    MessageRepository messageRepository;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void verifySaveTestMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/message")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\" : \"TestUser\", \"summary\" : \"Test summary\", \"text\" : \"Test text\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.text").exists())
                .andExpect(jsonPath("$.summary").exists())
                .andExpect(jsonPath("$.messageDate").exists())
                .andExpect(jsonPath("$.creationDate").exists())
                .andExpect(jsonPath("$.lastModifiedDate").exists())
                .andDo(print());
    }

    @Test
    public void verifySaveInvalidTestMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/message")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId1\" : \"TestUser\", \"summary1\" : \"Test summary\", \"text1\" : \"Test text\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.userId").doesNotExist())
                .andExpect(jsonPath("$.text").doesNotExist())
                .andExpect(jsonPath("$.summary").doesNotExist())
                .andExpect(jsonPath("$.messageDate").doesNotExist())
                .andExpect(jsonPath("$.creationDate").doesNotExist())
                .andExpect(jsonPath("$.lastModifiedDate").doesNotExist())
                .andDo(print());
    }

    @Test
    public void verifyTestMessageById() throws Exception {
        Message message = new Message("TestUser", "Test text", "Test summary");
        messageRepository.saveAndFlush(message);
        messageRepository.flush();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/messages/TestUser").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").doesNotExist())
                .andExpect(jsonPath("$.text").doesNotExist())
                .andExpect(jsonPath("$.summary").doesNotExist())
                .andDo(print());
    }

    @Test
    public void verifyInvalidTestMessageUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/messagesTest").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").doesNotExist())
                .andDo(print());
    }

}