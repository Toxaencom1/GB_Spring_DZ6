package com.taxah.springdz6.repository;


import com.taxah.springdz6.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * NoteRepository Interface
 * <p>
 * This interface extends JpaRepository to provide basic CRUD operations for Note entities.
 * It inherits methods for saving, updating, deleting, and querying notes in the database.
 * <p>
 * Dependencies:
 * - JpaRepository: Interface providing basic CRUD operations for entities.
 * <p>
 * Methods:
 * - No additional methods are defined in this interface. It inherits all methods from JpaRepository.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
