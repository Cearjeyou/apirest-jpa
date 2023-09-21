package com.example.demo.dto.mapper;

public interface MapperDto<EntityDto, Entity> {
    EntityDto toDto(Entity t);
}
