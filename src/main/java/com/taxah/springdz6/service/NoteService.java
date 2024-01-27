package com.taxah.springdz6.service;

import com.taxah.springdz6.model.Note;
import com.taxah.springdz6.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return repository.findById(id);
    }

    public Optional<Note> addNote(Note note) {
        Optional<Note> optional = Optional.empty();
        boolean checkNote = checkNote(note);
        if (checkNote) {
            optional = optional.or(() -> Optional.of(note));
            repository.save(note);
            return optional;
        }
        return optional;
    }

    public Note updateNote(Note note) {
        return repository.save(note);
    }

    public void deleteNote(Long id) {
        repository.deleteById(id);
    }

    private boolean checkNote(Note note) {
        return note.getDescription() != null && note.getHeader() != null;
    }
}
