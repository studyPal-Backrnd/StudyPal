package project.capstone.studyPal.service.myTokenService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.capstone.studyPal.data.models.MyToken;
import project.capstone.studyPal.data.repository.MyTokenRepository;
import project.capstone.studyPal.exception.LogicException;

@Service
@AllArgsConstructor
public class MyTokenServiceImpl implements MyTokenService {
    private final MyTokenRepository myTokenRepository;
    @Override
    public MyToken findMyTokenByToken(String token) {
        return myTokenRepository.findByToken(token).orElseThrow(
                ()-> new LogicException("Token is expired"));
    }

    @Override
    public MyToken save(MyToken myToken) {
        return myTokenRepository.save(myToken);
    }
}
