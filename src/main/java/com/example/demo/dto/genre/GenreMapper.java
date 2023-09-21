package com.example.demo.dto.genre;

import com.example.demo.dto.mapper.MapperDto;
import com.example.demo.model.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class GenreMapper implements MapperDto<GenreDto, Genre> {

    @Override
    public GenreDto toDto(Genre t) {
        GenreDto genreDto = new GenreDto();
        genreDto.setUuid(t.getUuid());
        genreDto.setName(t.getName());
        return genreDto;
    }
}
