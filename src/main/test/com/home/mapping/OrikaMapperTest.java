package com.home.mapping;

import com.home.dto.NoteDto;
import com.home.model.Note;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class OrikaMapperTest {

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @Test
    public void testMapping() {

        mapperFactory.classMap(Note.class, NoteDto.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Note src = Note.builder()
                .amount(new BigDecimal(123))
                .description("hallo description")
                .noteDate(LocalDate.now())
                .build();

        NoteDto dest = mapper.map(src, NoteDto.class);

        assertEquals(dest.getAmount(), src.getAmount());
        assertEquals(dest.getDescription(), src.getDescription());
        assertEquals(dest.getNoteDate(), src.getNoteDate());

    }
}