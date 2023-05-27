package project.capstone.studyPal.service.myTokenService;


import project.capstone.studyPal.data.models.MyToken;

public interface MyTokenService {
    MyToken findMyTokenByToken(String token);
    MyToken save(MyToken myToken);
}
