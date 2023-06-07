package project.capstone.studyPal.service.studyPalService.resourceMaterialService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.data.models.ResourceMaterial;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceMaterialServiceImplTest {

    @Autowired
    private ResourceMaterialService resourceMaterialService;
    List<ResourceMaterial> books;

    @BeforeEach
    void setUp() {
    }

    @Test
    void searchResourceMaterial() {
        try {
           books = resourceMaterialService.searchResourceMaterials("home");
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        System.out.println(books);
    }


}