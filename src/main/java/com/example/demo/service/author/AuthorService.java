package com.example.demo.service.author;

import com.example.demo.model.Author;

import java.util.List;

public interface AuthorService {
    Author create(Author author);
    Author consult(String uuid);
    List<Author> consultAll();
    Author modify(String uuid, Author author);
    Author update(String uuid, Author author);
    void delete(String uuid);
}
