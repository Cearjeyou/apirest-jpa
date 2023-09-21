package com.example.demo.controller;

import com.example.demo.model.Genre;
import com.example.demo.model.Publisher;
import com.example.demo.service.publisher.PublisherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publishers")
@AllArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping("/{uuid}")
    public ResponseEntity<Publisher> consultGenre(@PathVariable String uuid) {
        Publisher publisher = publisherService.consult(uuid);
        if (publisher != null){
            return ResponseEntity.ok(publisher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Publisher> createGenre(@RequestBody Publisher publisher) {
        return ResponseEntity.ok(publisherService.create(publisher));
    }

    @GetMapping
    public ResponseEntity<List<Publisher>> consultGenres() {
        return ResponseEntity.ok(publisherService.consultAll());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Publisher> modifyGenre(@PathVariable(value = "uuid") String uuid, @RequestBody Publisher publisher) {
        Publisher genreModify = publisherService.modify(uuid, publisher);
        if (genreModify != null) {
            return ResponseEntity.ok(genreModify);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<Publisher> updateGenre(@PathVariable(value = "uuid") String uuid, @RequestBody Publisher publisher) {
        Publisher genreModify = publisherService.update(uuid, publisher);
        if (genreModify != null) {
            return ResponseEntity.ok(genreModify);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> deleteGenre(@PathVariable(value = "uuid") String uuid) {
        try {
            publisherService.deleteById(uuid);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
