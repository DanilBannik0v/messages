package com.example.jpa.services;

import com.example.jpa.domain.Message;
import com.example.jpa.models.MessageRequest;
import com.example.jpa.models.MessageResponse;

import com.example.jpa.repositories.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MessageResponse> findAll() {
        return messageRepository.findAll()
                .stream()
                .map(this::buildMessageResponse)
                .collect(Collectors.toList());
    }

    @NotNull
    private MessageResponse buildMessageResponse(@NotNull Message message) {
        return new MessageResponse()
                .setId(message.getId())
                .setText(message.getText())
                .setSenderName(message.getSenderName());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<MessageResponse> findBySenderName(@NotNull String senderName) {
        return messageRepository.findAll()
                .stream()
                .map(this::buildMessageResponse)
                .filter(messageResponse -> messageResponse.getSenderName().equals(senderName))
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public MessageResponse findById(@NotNull Integer messageId) {
        return messageRepository.findById(messageId)
                .map(this::buildMessageResponse)
                .orElseThrow(() -> new EntityNotFoundException("Message " + messageId + " is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public MessageResponse createMessage(@NotNull MessageRequest request) {
        Message message = buildMessageRequest(request);
        return buildMessageResponse(messageRepository.save(message));
    }

    @NotNull
    private Message buildMessageRequest(@NotNull MessageRequest request) {
        return new Message()
                .setId(request.getId())
                .setText(request.getText())
                .setSenderName(request.getSenderName());
    }

    @NotNull
    @Override
    @Transactional
    public MessageResponse update(@NotNull Integer messageId, @NotNull MessageRequest request) {
        Message message =  messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message " + messageId + " is not found"));
        messageUpdate(message, request);
        return buildMessageResponse(messageRepository.save(message));
    }

    private void messageUpdate(@NotNull Message message, @NotNull MessageRequest request) {
        ofNullable(request.getId()).map(message::setId);
        ofNullable(request.getText()).map(message::setText);
        ofNullable(request.getSenderName()).map(message::setSenderName);
    }

    @Override
    @Transactional
    public void delete(@NotNull Integer messageId) {
        messageRepository.deleteById(messageId);
    }
}
