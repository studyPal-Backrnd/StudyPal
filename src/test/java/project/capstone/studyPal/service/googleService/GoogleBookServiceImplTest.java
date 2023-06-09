package project.capstone.studyPal.service.googleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.dto.response.BookItem;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class GoogleBookServiceImplTest {
    @Autowired
    private GoogleBookService googleBookService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void searchBookItems() {
        List<BookItem> bookItems = googleBookService.searchBooksByTitle("Lord of the Rings");

        System.out.println(bookItems);
    }
}