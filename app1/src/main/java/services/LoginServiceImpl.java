package services;

import org.example.classes.Login;
import org.example.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginRepository loginRepository;

    @Override
    public Login getLoginByloginAndPassword(String login,String password) {
        return loginRepository.getLoginByLoginAndPassword(login, password);
    }
}
