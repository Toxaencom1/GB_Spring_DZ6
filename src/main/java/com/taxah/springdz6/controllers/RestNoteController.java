package com.taxah.springdz6.controllers;

import com.taxah.springdz6.model.Note;
import com.taxah.springdz6.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * RestNoteController Class
 * <p>
 * This class serves as a RESTful controller for managing notes.
 * It provides endpoints for retrieving, adding, updating, and deleting notes via RESTful API.
 * <p>
 * Example Usage:
 * Accessing all notes: GET request to "/api"
 * Accessing a note by ID: GET request to "/api/{id}"
 * Adding a note: POST request to "/api"
 * Updating a note: PUT request to "/api"
 * Deleting a note: DELETE request to "/api/{id}"
 * <p>
 * Annotations:
 * - @RestController: Indicates that this class is a RESTful controller.
 * - @RequestMapping: Maps HTTP requests to handler methods.
 * - @RequiredArgsConstructor: Lombok annotation to generate a constructor with all required fields.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RestNoteController {
    private final NoteService service;

    /**
     * Get All Notes
     * <p>
     * Handles GET requests to "/api" and retrieves all notes.
     *
     * @return ResponseEntity<List < Note>>: A response entity containing a list of all notes (HTTP status: OK).
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(service.getAllNotes(), HttpStatus.OK);
    }

    /**
     * Get Note by ID
     * <p>
     * Handles GET requests to "/api/{id}" and retrieves a note by its ID.
     *
     * @param id Long: The ID of the note to retrieve.
     * @return ResponseEntity<Note>: A response entity containing the retrieved note (HTTP status: OK),
     * or a not-found response entity if the note does not exist (HTTP status: NOT_FOUND).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> noteById = service.getNoteById(id);
        return noteById.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Add Note
     * <p>
     * Handles POST requests to "/api" and adds a new note.
     *
     * @param note Note: The note to add.
     * @return ResponseEntity<Note>: A response entity containing the added note (HTTP status: OK),
     * or a bad-request response entity if the note is not added successfully (HTTP status: BAD_REQUEST).
     */
    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        Optional<Note> optional = service.addNote(note);
        return optional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
/*     // эквивалентная альтернатива
        if (optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.badRequest().build();
        }
*/

    /**
     * Update Note
     * <p>
     * Handles PUT requests to "/api" and updates a note.
     *
     * @param bodyNote Note: The updated note.
     * @return ResponseEntity<Note>: A response entity containing the updated note (HTTP status: OK),
     * or a bad-request response entity if the update is unsuccessful (HTTP status: BAD_REQUEST).
     */
    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note bodyNote) {
        if (bodyNote.getId() != null && checkNote(bodyNote)) {
            Optional<Note> noteById = service.getNoteById(bodyNote.getId());
            if (noteById.isPresent()) {
                return new ResponseEntity<>(service.updateNote(bodyNote), HttpStatus.OK);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Delete Note
     * <p>
     * Handles DELETE requests to "/api/{id}" and deletes a note by its ID.
     *
     * @param id Long: The ID of the note to delete.
     * @return ResponseEntity<Void>: A response entity with no content (HTTP status: OK)
     * if the deletion is successful, or a not-found response entity (HTTP status: NOT_FOUND)
     * if the note with the given ID does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        Optional<Note> noteById = service.getNoteById(id);
        if (noteById.isPresent()) {
            service.deleteNote(id);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
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
