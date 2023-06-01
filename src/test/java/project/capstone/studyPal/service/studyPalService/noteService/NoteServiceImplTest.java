package project.capstone.studyPal.service.studyPalService.noteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.dto.request.CreateNoteRequest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NoteServiceImplTest {
    @Autowired NoteService noteService;
    private CreateNoteRequest createNoteRequest1;
    private CreateNoteRequest createNoteRequest2;


    @Test
    void createNote() {
        createNoteRequest1 = new CreateNoteRequest();
//        createNoteRequest1.setUserId();
        createNoteRequest2 = new CreateNoteRequest();
    }

    @Test
    void getNoteById() {
    }

    @Test
    void getAllNotes() {
    }

    @Test
    void noteCount() {
    }

    @Test
    void updateNote() {
    }

    @Test
    void deleteNote() {
    }
}