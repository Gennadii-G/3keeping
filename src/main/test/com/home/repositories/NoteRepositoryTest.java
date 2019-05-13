package com.home.repositories;

import com.home.model.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void whenSave() {
        Note note = getTestNote();

        Note savedNote = noteRepository.save(note);

        assertEquals(note.getAmount(), savedNote.getAmount());
        assertEquals(note.getDescription(), savedNote.getDescription());
        assertEquals(note.getNoteDate(), savedNote.getNoteDate());
    }

    @Test
    public void whenGetById() {
        Note note = getTestNote();
        note = noteRepository.save(note);

        Note foundNote = noteRepository.getOne(note.getId());

        assertEquals(foundNote, note);
    }

    @Test
    public void whenDelete() {
        for(int i = 0; i < 3; i++){
            Note note = getTestNote();
            note = noteRepository.save(note);
        }
        List<Note> notes = noteRepository.findAll();
        assertEquals(3, noteRepository.findAll().size());
        noteRepository.delete(notes.get(0));
        assertEquals(noteRepository.findAll().size(), 2);

    }

    private Note getTestNote() {
        Note note = new Note();
        note.setAmount(new BigDecimal(200));
        note.setDescription("this is test note");
        note.setNoteDate(LocalDate.of(2000, 10, 20));

        return note;
    }

}
