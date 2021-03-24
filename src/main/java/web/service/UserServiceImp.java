package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Override
    public void add(User user, String rolesString) {
        user.setRoles(parseRolesString(rolesString));
        userDao.add(user);
    }

    @Override
    public void update(Long id, String name, int age, long phone, String password,String rolesString) {
        User user = new User(name, age, phone, password, parseRolesString(rolesString));
        userDao.update(user, id);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userDao.getUserByName(name);
    }

    public List<Role> getRolesList() {
        return userDao.getRolesList();
    }

    private Set<Role> parseRolesString(String rolesString) {
        return Arrays.stream(rolesString.split(","))
                .map(string -> roleDao.getByRoleName(string))
                .collect(Collectors.toSet());
    }
}
