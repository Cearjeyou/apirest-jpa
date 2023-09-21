package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Genre;
import com.example.demo.service.author.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Author> consultGenre(@PathVariable String uuid) {
        Author author = authorService.consult(uuid);
        if (author != null){
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Author> createGenre(@RequestBody Author author) {
        return ResponseEntity.ok(authorService.create(author));
    }

    @GetMapping
    public ResponseEntity<List<Author>> consultGenres() {
        return ResponseEntity.ok(authorService.consultAll());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Author> modifyGenre(@PathVariable(value = "uuid") String uuid, @RequestBody Author author) {
        Author authorModify = authorService.modify(uuid, author);
        if (authorModify != null) {
            return ResponseEntity.ok(authorModify);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<Author> updateGenre(@PathVariable(value = "uuid") String uuid, @RequestBody Author author) {
        Author genreModify = authorService.update(uuid, author);
        if (genreModify != null) {
            return ResponseEntity.ok(genreModify);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> deleteGenre(@PathVariable(value = "uuid") String uuid) {
        try {
            authorService.delete(uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
