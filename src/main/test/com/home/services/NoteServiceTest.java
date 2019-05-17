package com.home.services;

import com.home.model.Category;
import com.home.model.Note;
import com.home.model.Owner;
import com.home.repositories.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {


    @Mock
    private NoteRepository noteRepository;
    private NoteService noteService;

    @After
    public void clearDb() {
        noteService.getAll().forEach(elt -> noteService.delete(elt));
    }

    private Note noteStub = buildTestNote(20L);

    @Before
    public void init() {
        when(noteRepository.save(noteStub)).thenReturn(noteStub);
        when(noteRepository.findAll())
                .thenReturn(Lists.list(buildTestNote(0L), buildTestNote(1L), buildTestNote(2L)));
        when(noteRepository.getOne(noteStub.getId())).thenReturn(noteStub);
        noteService = new NoteServiceImpl(noteRepository);
        log.info("success init");
    }

    private Category category;
    private Owner owner;

    @Test
    public void save() {
        when(noteRepository.save(noteStub)).thenReturn(noteStub);

        Note savedNote = noteService.save(noteStub);

        assertEquals(noteStub.getAmount(), savedNote.getAmount());
        assertEquals(noteStub.getDescription(), savedNote.getDescription());
        assertEquals(noteStub.getNoteDate(), savedNote.getNoteDate());
        assertEquals(noteStub.getOwner(), owner);
        assertEquals(noteStub.getCategory(), category);
    }

    @Test
    public void delete() {

        List<Note> notes = noteService.getAll();
        assertEquals(3, noteService.getAll().size());
        noteService.delete(notes.get(0));
    }

    @Test
    public void getById() {
        Note note = noteStub;
        note = noteService.save(note);

        Note foundNote = noteService.getById(note.getId());

        assertEquals(foundNote, note);
    }

    @Test
    public void getAll() {
        List<Note> notes = noteService.getAll();
        assertEquals(3, noteService.getAll().size());

    }

    private Note buildTestNote(Long id) {
        Note note = Note.builder()
                .id(id)
                .amount(new BigDecimal(200))
                .description("this is test note")
                .noteDate(LocalDate.of(2000, 10, 20))
                .build();

        this.owner = Owner.builder()
                .name("testOwner")
                .build();

        this.category = Category
                .builder()
                .code("CODE")
                .name("test")
                .priority(new BigDecimal(70))
                .build();

        note.setOwner(owner);
        note.setCategory(category);

        return note;
    }
}