package project.capstone.studyPal.service.myTokenService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.capstone.studyPal.data.models.MyToken;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyTokenServiceImplTest {
    @Autowired MyTokenService myTokenService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findMyTokenByToken() {
    }

    @Test
    void save() {
    }

    @Test
    void getTokenById() {
        MyToken foundToken = myTokenService.getTokenById(1L);
        assertThat(foundToken.getToken()).isEqualTo("1873");
    }
}