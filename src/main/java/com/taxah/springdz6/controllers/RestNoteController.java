package com.taxah.springdz6.controllers;

import com.taxah.springdz6.model.Note;
import com.taxah.springdz6.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RestNoteController {
    private final NoteService service;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(service.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> noteById = service.getNoteById(id);
        return noteById.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

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

    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note note) {
        if (note.getId()!=null && checkNote(note)) {
            Optional<Note> noteById = service.getNoteById(note.getId());
            if (noteById.isPresent()) {
                return new ResponseEntity<>(service.updateNote(note), HttpStatus.OK);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        Optional<Note> noteById = service.getNoteById(id);
        if (noteById.isPresent()) {
            service.deleteNote(id);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

    private boolean checkNote(Note note) {
        return note.getDescription() != null && note.getHeader() != null;
    }
}
