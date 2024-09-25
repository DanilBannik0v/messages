package com.example.jpa.services;

import com.example.jpa.models.MessageRequest;
import com.example.jpa.models.MessageResponse;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public interface MessageService {
    @NotNull
    List<MessageResponse> findAll();

    @NotNull
    MessageResponse findById(@NotNull Integer messageId);

    @NotNull
    List<MessageResponse> findBySenderName(@NotNull String senderName);

    @NotNull
    MessageResponse createMessage(@NotNull MessageRequest request);

    @NotNull
    MessageResponse update(@NotNull Integer messageId, @NotNull MessageRequest request);

    void delete(@NotNull Integer messageId);
}
