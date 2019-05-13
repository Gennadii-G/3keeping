package com.home.services;

import com.home.model.Note;

import java.util.List;

public interface NoteService {

    public Note save(Note note);

    public void delete(Note note);

    public Note getById(Long id);

    public List<Note> getAll();
}
