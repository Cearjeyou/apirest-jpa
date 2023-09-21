package com.example.demo.model;

import com.example.demo.model.abstracts.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class Author extends BaseModel {
    private String name;
    private String documentNumber;
    private String email;
}
