package com.home.services;

import com.home.Application;
import com.home.model.Note;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class NoteServiceTest {

    @Autowired
    public NoteService noteService;

    @After
    public void clearDb() {
        noteService.getAll().forEach(elt -> noteService.delete(elt));
    }

    @Test
    public void save() {
        Note note = getTestNote();

        Note savedNote = noteService.save(note);

        assertEquals(note.getAmount(), savedNote.getAmount());
        assertEquals(note.getDescription(), savedNote.getDescription());
        assertEquals(note.getnDate(), savedNote.getnDate());
    }

    @Test
    public void delete() {
        for(int i = 0; i < 3; i++){
            Note note = getTestNote();
            note = noteService.save(note);
        }
        List<Note> notes = noteService.getAll();
        assertEquals(3, noteService.getAll().size());
        noteService.delete(notes.get(0));
        assertEquals(noteService.getAll().size(), 2);

    }

    @Test
    public void getById() {
        Note note = getTestNote();
        note = noteService.save(note);

        Note foundNote = noteService.getById(note.getId());

        assertEquals(foundNote, note);
    }

    @Test
    public void getAll() {
        for(int i = 0; i < 3; i++){
            Note note = getTestNote();
            note = noteService.save(note);
        }
        List<Note> notes = noteService.getAll();
        assertEquals(3, noteService.getAll().size());

    }

    private Note getTestNote() {
        Note note = new Note();
        note.setAmount(new BigDecimal(200));
        note.setDescription("this is test note");
        note.setnDate(LocalDate.of(2000, 10, 20));

        return note;
    }
}