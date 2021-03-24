package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(User user, String rolesString);
    void update(Long id, String name, int age, long phone, String password,String rolesString);
    void deleteById(Long id);
    List<User> listUsers();
}
