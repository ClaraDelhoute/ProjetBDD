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
    public void saveLogin(Login login) {
        loginRepository.save(login);
    }

    @Override
    public Login getLoginByUsernameAndPassword(String username,String password) {
        return loginRepository.getLoginByUsernameAndPassword(username, password);
    }
}
