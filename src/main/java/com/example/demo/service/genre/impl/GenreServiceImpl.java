package com.example.demo.service.genre.impl;

import com.example.demo.model.Genre;
import com.example.demo.repository.GenreRepository;
import com.example.demo.service.genre.GenreService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;


    @Override
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre consult(String uuid) {
        Optional<Genre> genreSearch = genreRepository.findById(uuid);
        return genreSearch.orElse(null);
    }

    @Override
    public List<Genre> consultAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre modify(String uuid, Genre genre) {
        Genre genreSearch = consult(uuid);
        if (genreSearch != null) {
            genre.setUuid(uuid);
            return genreRepository.save(genre);
        }
        return null;
    }

    @Override
    public Genre update(String uuid, Genre genre) {
        Genre genreSearch = consult(uuid);
        if (genreSearch != null) {
            genre.setUuid(uuid);
            Genre genreResponse = mapperUpdate(uuid, genre, genreSearch);
            return genreRepository.save(genreResponse);
        }
        return null;
    }

    @Override
    public void deleteById(String uuid) {
        Genre genre = consult(uuid);
        if (genre != null) {
            genreRepository.deleteById(uuid);
        } else {
            throw new EntityNotFoundException();
        }
    }
    @Override
    public List<Genre> findBySpecificName(String name) {
        return genreRepository.findByNameWithSpecificString(name);
    }

    private Genre mapperUpdate(String uuid, Genre genreRequest, Genre genre) {
        Genre genreResponse = new Genre();
        genreResponse.setUuid(uuid);
        genreResponse.setName(genreRequest.getName() == null ? genre.getName() : genreRequest.getName());
        genreResponse.setCreatedDate(genreRequest.getCreatedDate() == null ? genre.getCreatedDate() : genreRequest.getCreatedDate());
        genreResponse.setLastModifiedDate(genreRequest.getLastModifiedDate() == null ? genre.getLastModifiedDate() : genreRequest.getLastModifiedDate());
        genreResponse.setDescription(genreRequest.getDescription() == null ? genre.getDescription() : genreRequest.getDescription());
        return genreResponse;
    }
}
