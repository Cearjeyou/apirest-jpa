package com.example.demo.service.genre;

import com.example.demo.model.Genre;

import java.util.List;

public interface GenreService {
    Genre create(Genre genre);
    Genre consult(String uuid);
    List<Genre> consultAll();
    Genre modify(String uuid, Genre genre);
    Genre update(String uuid, Genre genre);
    void deleteById(String uuid);
    List<Genre> findBySpecificName(String name);
}
