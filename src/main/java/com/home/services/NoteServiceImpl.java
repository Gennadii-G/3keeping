package com.home.services;

import com.home.model.Note;
import com.home.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }

    public Note getById(Long id) {
        return noteRepository.getOne(id);
    }

    public List<Note> getAll() {
        return noteRepository.findAll();
    }
}
