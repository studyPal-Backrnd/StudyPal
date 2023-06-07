package project.capstone.studyPal.service.studyPalService.noteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.data.models.Note;
import project.capstone.studyPal.dto.request.CreateNoteRequest;
import project.capstone.studyPal.dto.request.UpdateNoteRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class NoteServiceImplTest {
    @Autowired NoteService noteService;
    private CreateNoteRequest createNoteRequest1;
    private CreateNoteRequest createNoteRequest2;
    private CreateNoteRequest createNoteRequest3;
    private UpdateNoteRequest updateNoteRequest;

    @BeforeEach
    public void setUp(){
        createNoteRequest1 = new CreateNoteRequest();
        createNoteRequest1.setUserId(1L);
        createNoteRequest1.setTitle("My first note in study pal");
        createNoteRequest1.setBody("This is my first note in study pal web application");

        createNoteRequest2 = new CreateNoteRequest();
        createNoteRequest2.setUserId(1L);
        createNoteRequest2.setTitle("My second nto in study pal");
        createNoteRequest2.setBody("This is the second note in study pal web application");

        createNoteRequest3 = new CreateNoteRequest();
        createNoteRequest3.setUserId(1L);
        createNoteRequest3.setTitle("My second nto in study pal");
        createNoteRequest3.setBody("This is the second note in study pal web application");

        updateNoteRequest = new UpdateNoteRequest();
        updateNoteRequest.setUserId(1L);
        updateNoteRequest.setNoteId(1L);
        updateNoteRequest.setUpdateTitle("updating second note in study pal");
        updateNoteRequest.setUpdateBody("This is the second updated note in study pal");
    }


    @Test
    void createNote() {
        String response = noteService.createNote(createNoteRequest1);
        assertThat(response).isEqualTo("New note created");
        String response2 = noteService.createNote(createNoteRequest2);
        assertThat(response2).isEqualTo("New note created");
        String response3 = noteService.createNote(createNoteRequest3);
        assertThat(response3).isEqualTo("New note created");
    }

    @Test
    void getNoteById() {
        Note foundNote = noteService.getNoteById(1L, 1L);
        assertThat(foundNote.getTitle()).isEqualTo(createNoteRequest1.getTitle());
        assertThat(foundNote.getBody()).isEqualTo(createNoteRequest1.getBody());
    }

    @Test
    void noteCount() {
        Long noteCount = noteService.noteCount();
        assertThat(noteCount).isEqualTo(3);
    }
    @Test
    void getAllNotes() {
        System.out.println(noteService.getAllNotes(1L));
    }


    @Test
    void updateNote() {
        String response = noteService.updateNote(updateNoteRequest);
        assertThat(response).isEqualTo("Note is updated");
    }

    @Test
    void deleteNote() {
//        noteService.deleteNote(3L);
//        assertThat(noteService.noteCount()).isEqualTo(2);
    }
}