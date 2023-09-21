package com.example.demo.service.author.impl;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.author.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author consult(String uuid) {
        Optional<Author> author = authorRepository.findById(uuid);
        return author.orElse(null);
    }

    @Override
    public List<Author> consultAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author modify(String uuid, Author author) {
        Author authorSearch = consult(uuid);
        if (authorSearch != null) {
            author.setUuid(uuid);
            return authorRepository.save(author);
        }
        return null;
    }

    @Override
    public Author update(String uuid, Author author) {
        Author authorSearch = consult(uuid);
        if (authorSearch != null) {
            author.setUuid(uuid);
            Author author1 = mapperUpdate(uuid, author, authorSearch);
            return authorRepository.save(author1);
        }
        return null;
    }

    @Override
    public void delete(String uuid) {
        Author author = consult(uuid);
        if (author != null) {
            authorRepository.deleteById(uuid);
        } else {
            throw new EntityNotFoundException();
        }
    }

    private Author mapperUpdate(String uuid, Author authorRequest, Author author) {
        Author author1 = new Author();
        author1.setUuid(uuid);
        author1.setName(authorRequest.getName() == null ? author.getName() : authorRequest.getName());
        author1.setCreatedDate(authorRequest.getCreatedDate() == null ? author.getCreatedDate() : authorRequest.getCreatedDate());
        author1.setLastModifiedDate(authorRequest.getLastModifiedDate() == null ? author.getLastModifiedDate() : authorRequest.getLastModifiedDate());
        author1.setDocumentNumber(authorRequest.getDocumentNumber() == null ? author.getDocumentNumber() : authorRequest.getDocumentNumber());
        author1.setEmail(authorRequest.getEmail() == null ? author.getEmail() : authorRequest.getEmail());
        return author1;
    }
}
