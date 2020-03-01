package top.bowentu.service;

public interface ILoginService {
    boolean login(String username, String password);

    public boolean register(String username, String password);
}
