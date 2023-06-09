package project.capstone.studyPal.service.studyPalService.resourceMaterialService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.data.models.ResourceMaterial;
import project.capstone.studyPal.dto.request.ResourceMaterialRequest;
import project.capstone.studyPal.dto.response.BookItem;
import project.capstone.studyPal.dto.response.VolumeInfo;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceMaterialServiceImplTest {

    @Autowired
    private ResourceMaterialService resourceMaterialService;
    List<BookItem> books;
    BookItem book = new BookItem();
    VolumeInfo volumeInfo = new VolumeInfo();

    @BeforeEach
    void setUp() {
        volumeInfo.setInfoLink("http://books.google.com/books?id=zYnhnQEACAAJ&dq=intitle:Dreams&hl=&source=gbs_api");
        volumeInfo.setDescription("The great dreams of a man");
        volumeInfo.setTitle("Dreams");
        volumeInfo.setAuthors(new ArrayList<>(Collections.singleton("Martin Luther King Jr")));
        volumeInfo.setReadingModes(HashMap.newHashMap(1));
        book.setId("zYnhnQEACAAJ");
        book.setVolumeInfo(volumeInfo);
    }

    @Test
    void searchResourceMaterial() {

        try {
            books = resourceMaterialService.searchResourceMaterials("I have a dream");
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        assertTrue(books.size() >= 1);

    }

    @Test
    void addBookToDatabase(){
        try {
            resourceMaterialService.addResourceMaterial(new ResourceMaterialRequest(1L, book));
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
        assertTrue(resourceMaterialService.getResourceMaterialById(1L).isPresent());
    }

    @Test
    void removeResourceMaterial() {
        resourceMaterialService.removeResourceMaterial(1L, 2L);
        assertTrue(resourceMaterialService.getResourceMaterialById(2L).isEmpty());
    }

    @Test
    void getResourceBrId() {
        assertNotNull(resourceMaterialService.getResourceMaterialById(3L));
    }
}