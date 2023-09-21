package com.example.demo.controller;

import com.example.demo.model.Genre;
import com.example.demo.service.genre.GenreService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Genre> consultGenre(@PathVariable String uuid) {
        Genre genre = genreService.consult(uuid);
        if (genre != null){
            return ResponseEntity.ok(genre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.create(genre));
    }

    @GetMapping
    public ResponseEntity<List<Genre>> consultGenres() {
        return ResponseEntity.ok(genreService.consultAll());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Genre> modifyGenre(@PathVariable(value = "uuid") String uuid, @RequestBody Genre genre) {
        Genre genreModify = genreService.modify(uuid, genre);
        if (genreModify != null) {
            return ResponseEntity.ok(genreModify);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<Genre> updateGenre(@PathVariable(value = "uuid") String uuid, @RequestBody Genre genre) {
        Genre genreModify = genreService.update(uuid, genre);
        if (genreModify != null) {
            return ResponseEntity.ok(genreModify);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> deleteGenre(@PathVariable(value = "uuid") String uuid) {
        try {
            genreService.deleteById(uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{nameGenre}")
    public ResponseEntity<List<Genre>> findAllSpecificName(@PathVariable(value = "nameGenre") String nameGenre) {
        return ResponseEntity.ok(genreService.findBySpecificName(nameGenre));
    }

}
