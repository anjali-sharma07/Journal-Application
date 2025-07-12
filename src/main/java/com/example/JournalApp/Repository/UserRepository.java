package com.example.JournalApp.Repository;
import com.example.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
    public interface UserRepository extends MongoRepository<User,ObjectId>{
         User findByUsername(String username);
    void deleteById(ObjectId id);

    void deleteByUsername(String username);

}
//    #spring.data.mongodb.uri = mongodb+srv://techanjali07:hYBZQZ9igNhJnyQ9@cluster0.bu8mhn8.mongodb.net/journalsys?retryWrites=true&w=majority
//            #
//            #spring.data.mongodb.database = journalsys