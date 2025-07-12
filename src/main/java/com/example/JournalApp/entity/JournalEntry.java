package com.example.JournalApp.entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import java.util.*;
import java.time.LocalDateTime;
@Document(collection = "journal_entries")
@Data
@NoArgsConstructor     //converts json to pojo (used for post)  ----->deserialization..
public class JournalEntry {
    @Id
    public ObjectId id;
    private LocalDateTime date;
    public String heading;
    public String content;


}