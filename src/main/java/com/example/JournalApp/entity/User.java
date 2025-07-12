package com.example.JournalApp.entity;
import lombok.Getter;
import lombok.NonNull;
import org.bson.types.ObjectId;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.index.Indexed;
@Document(collection = " Users")
@Getter
@Setter
public class User {
    @Id
    public ObjectId id;
    @Indexed(unique = true)      // we have to set the index automatically we must write it in application properties
    @NonNull
    public String username;
    @NonNull
    public String password;

    @DBRef     // creating references of JournalEntry in Users collection ( reference between these two collections)
    public List<JournalEntry> journalEntries = new ArrayList<>();
    public  List<String> Roles;

}
