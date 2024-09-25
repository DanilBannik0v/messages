package com.example.jpa.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name="messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column("text")
    private String text;
    @Column("sender_name")
    private String senderName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(text, message.text) && Objects.equals(senderName, message.senderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, senderName);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
