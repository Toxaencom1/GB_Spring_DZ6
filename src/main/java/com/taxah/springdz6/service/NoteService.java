package com.taxah.springdz6.service;

import com.taxah.springdz6.model.Note;
import com.taxah.springdz6.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * NoteService Class
 * <p>
 * This class provides business logic for managing notes in a note-taking application.
 * It includes methods for retrieving, adding, updating, and deleting notes.
 * <p>
 * Dependencies:
 * - NoteRepository: Interface for accessing and managing note entities in the database.
 * <p>
 * Annotations:
 * - @Service: Indicates that this class contains business logic.
 * - @RequiredArgsConstructor: Lombok annotation to generate a constructor with all required fields.
 */
@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    /**
     * Get All Notes
     * <p>
     * Retrieves all notes from the database.
     *
     * @return List<Note>: A list of all notes.
     */
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    /**
     * Get Note by ID
     * <p>
     * Retrieves a note by its ID from the database.
     *
     * @param id Long: The ID of the note to retrieve.
     * @return Optional<Note>: An optional containing the note, or empty if not found.
     */
    public Optional<Note> getNoteById(Long id) {
        return repository.findById(id);
    }

    /**
     * Add Note
     * <p>
     * Adds a new note to the database if it passes validation.
     *
     * @param note Note: The note to add.
     * @return Optional<Note>: An optional containing the added note, or empty if not added.
     */
    public Optional<Note> addNote(Note note) {
        Optional<Note> optional = Optional.empty();
        if (checkNote(note)) {
            optional = Optional.of(note);
            repository.save(note);
            return optional;
        }
        return optional;
    }

    /**
     * Update Note
     * <p>
     * Updates an existing note in the database.
     *
     * @param note Note: The updated note.
     * @return Note: The updated note.
     */
    public Note updateNote(Note note) {
        return repository.save(note);
    }

    /**
     * Delete Note
     * <p>
     * Deletes a note by its ID from the database.
     *
     * @param id Long: The ID of the note to delete.
     */
    public void deleteNote(Long id) {
        repository.deleteById(id);
    }

    /**
     * Check Note Validity
     * <p>
     * Checks if a note is valid by ensuring it has a non-null description and header.
     *
     * @param note Note: The note to check.
     * @return boolean: True if the note is valid, false otherwise.
     */
    private boolean checkNote(Note note) {
        return note.getDescription() != null && note.getHeader() != null;
    }
}
