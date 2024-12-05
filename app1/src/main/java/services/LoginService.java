package services;

import org.example.classes.Login;

public interface LoginService {
    public Login getLoginByloginAndPassword(String login,String password);
}
