package org.vikas.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "messages")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate", "lastModifiedDate"},
        allowGetters = true)

public class Message extends BaseEntity implements Serializable {


    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String userId;
    @NotNull
    private String text;
    @NotNull
    private String summary;
    private String messageDate;
    private static final Logger logger = LoggerFactory.getLogger(Message.class);

    public Message() {
    }

    public Message(String userId, String text, String summary) {
        this.userId = userId;
        this.text = text;
        this.summary = summary;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    @PrePersist
    void onMessage() {
        Locale locale = new Locale("ch", "CH");
        String pattern = "YYYY MM dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("ch", "CH"));
        this.setMessageDate(simpleDateFormat.format(new Date()));
    }

}
