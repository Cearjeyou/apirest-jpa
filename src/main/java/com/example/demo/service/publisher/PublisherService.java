package com.example.demo.service.publisher;

import com.example.demo.model.Genre;
import com.example.demo.model.Publisher;

import java.util.List;

public interface PublisherService {
    Publisher create(Publisher publisher);
    Publisher consult(String uuid);
    List<Publisher> consultAll();
    Publisher modify(String uuid, Publisher publisher);
    Publisher update(String uuid, Publisher publisher);
    void deleteById(String uuid);
}
