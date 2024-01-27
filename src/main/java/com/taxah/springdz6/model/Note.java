package com.taxah.springdz6.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Note Class
 * <p>
 * This class represents a note entity in a note management system.
 * It includes attributes such as ID, header, description, and creation timestamp.
 * <p>
 * Dependencies:
 * - @Data: Lombok annotation to automatically generate getter, setter, toString, etc.
 * - @Entity: Indicates that this class is a JPA entity.
 * - @Table: Specifies the table name for the entity in the database.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String header;
    @Column(nullable = false)
    private String description;
    private LocalDateTime localDateTime = LocalDateTime.now();
    public Note(String header, String description) {
        this.header = header;
        this.description = description;
    }
}



