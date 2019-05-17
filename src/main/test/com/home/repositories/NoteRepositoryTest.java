package com.home.repositories;

import com.home.model.Category;
import com.home.model.Note;
import com.home.model.Owner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
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

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private Category category;
    private Owner owner;

    @Before
    public void init() {
        Owner owner = Owner.builder()
                .name("testOwner")
                .build();
        log.info(owner.toString());
        this.owner = ownerRepository.save(owner);

        this.category = categoryRepository.save(Category
                .builder()
                .id(12L)
                .code("CODE")
                .name("test")
                .priority(new BigDecimal(70))
                .build());

        log.info("success init");
    }

    @Test
    public void whenSave() {
        Note note = getTestNote();
        log.info("not saved note : {}", note.toString());

        Note savedNote = noteRepository.save(note);
        log.info("saved note : {}", savedNote.toString());

        assertEquals(note.getAmount(), savedNote.getAmount());
        assertEquals(note.getDescription(), savedNote.getDescription());
        assertEquals(note.getNoteDate(), savedNote.getNoteDate());
        assertEquals(note.getOwner(), owner);
        assertEquals(note.getCategory(), category);
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
        note.setOwner(owner);
        note.setCategory(category);

        return note;
    }

}
