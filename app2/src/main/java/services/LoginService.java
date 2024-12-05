package services;

import org.example.classes.Login;

public interface LoginService {
    public void saveLogin(Login login);
    public Login getLoginByUsernameAndPassword(String username,String password);
}
