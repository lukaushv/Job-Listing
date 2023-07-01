package com.ushver.joblisting;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImplement implements SearchRepository{

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter mongoConverter;
    @Override
    public List<Post> findByText(String search) {

        final List<Post> postList = new  ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("job-api-db");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                                new Document("query", search)
                                        .append("path", Arrays.asList("desc", "Profile", "techs")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 5L)));

        result.forEach(document -> postList.add(mongoConverter.read(Post.class, document)));

        return postList;
    }
}
