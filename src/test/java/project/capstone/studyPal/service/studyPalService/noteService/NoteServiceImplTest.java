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
    void setUp() {
        createNoteRequest1 = new CreateNoteRequest();
        createNoteRequest1.setUserId(1L);
        createNoteRequest1.setTitle("Read Java");
        createNoteRequest1.setBody("Java is a programming language.");

        createNoteRequest2 = new CreateNoteRequest();
        createNoteRequest2.setUserId(1L);
        createNoteRequest2.setTitle("Read Python");
        createNoteRequest2.setBody("Java is a also an object oriented programming language.");

        updateNoteRequest = new UpdateNoteRequest();
        updateNoteRequest.setNoteId(3L);
        updateNoteRequest.setUpdateTitle("Writing new go code");
        updateNoteRequest.setUpdateBody("I think i will switch to java now cos i hate java");
    }

    @Test
    void createNote() {
        String response1 = noteService.createNote(createNoteRequest1);
        String response2 = noteService.createNote(createNoteRequest2);
        assertThat(response1).isNotNull();
        assertThat(response1).isEqualTo("New note created");
        assertThat(response2).isNotNull();
        assertThat(response2).isEqualTo("New note created");
    }

    @Test
    void getNoteById() {
        Note foundNote = noteService.getNoteById(3L);
        assertThat(foundNote.getTitle()).isEqualTo(createNoteRequest1.getTitle());
    }

    @Test
    void getAllNotes() {
        List<Note> foundNotes = noteService.getAllNotes();
        assertThat(foundNotes).isNotNull();
        assertThat(noteService.noteCount()).isEqualTo(2L);
    }

    @Test
    void updateNote() {
        String response = noteService.updateNote(updateNoteRequest);
        assertThat(response).isEqualTo("Note is updated");
    }

    @Test
    void deleteNote() {
        String response = noteService.deleteNote(5L);
        String response2 = noteService.deleteNote(6L);
        assertThat(response).isEqualTo("Note deleted");
        assertThat(response2).isEqualTo("Note deleted");
    }
}