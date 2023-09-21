package com.example.demo.dto.author;

import com.example.demo.dto.genre.GenreDto;
import com.example.demo.dto.mapper.MapperDto;
import com.example.demo.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorMapper implements MapperDto<AuthorDto, Author> {
    @Override
    public AuthorDto toDto(Author t) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setUuid(t.getUuid());
        authorDto.setName(t.getName());
        return null;
    }
}
