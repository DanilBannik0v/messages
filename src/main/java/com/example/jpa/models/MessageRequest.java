package com.example.jpa.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MessageRequest {
    private int id;
    private String text;
    private String senderName;
}
