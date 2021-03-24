package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDaoImp;
import web.model.Role;
import web.model.User;
import web.service.UserServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Controller
@RequestMapping("admin/users")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping
    public String userList(ModelMap model) {
        List<User> userList = userServiceImp.listUsers();
        model.addAttribute("users", userList);
        model.addAttribute("new_user", new User());
        model.addAttribute("roles_list", userServiceImp.getRolesList());
        return "userList";
    }


    @PostMapping
    public String createUser(@ModelAttribute("new_user") User user, ModelMap model,
                              @RequestParam("userRoles") String rolesString) {
        userServiceImp.add(user, rolesString);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userServiceImp.deleteById(id);
        return "redirect:/admin/users";
    }

    @PatchMapping("/update")
    public String update(@RequestParam("userId") Long id,
                         @RequestParam("newName") String name,
                         @RequestParam("newPassword") String pass,
                         @RequestParam("newAge") int age,
                         @RequestParam("newPhone") long phone,
                         @RequestParam("newRoles") String rolesString) {
        userServiceImp.update(id, name, age, phone, pass, rolesString);
        return "redirect:/admin/users";
    }


}
