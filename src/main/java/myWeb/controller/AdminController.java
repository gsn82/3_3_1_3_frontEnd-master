package myWeb.controller;

import myWeb.model.Role;
import myWeb.model.User;
import myWeb.service.RoleService;
import myWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Ignore
@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    @Autowired
    private UserService userService;

   // @Autowired
  //  private RoleService roleService;

    //Начальная страница админа
    @GetMapping
    public ModelAndView allUsers() {
        User admin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/admin");
        modelAndView.addObject("admin",admin);
        return modelAndView;
    }

    //api отображения пользователей
    @GetMapping(value = "/api/users")
    public ResponseEntity<List<User>> apiAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //api отображения пользователей
    @GetMapping(value = "/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    //api создания нового пользователя
    @PostMapping(value = "/api/users")
    public ResponseEntity<User> create(@RequestBody User user){
        userService.updateUser(user,user.getRoleSetTemp());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //api редактирования
    @PutMapping("/api/users")
    public ResponseEntity<User> edit(@RequestBody User user){
        userService.updateUser(user,user.getRoleSetTemp());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //функция где должны вставляться роли пользователю и где сохраняется пользователь
  /*  private void userManipulation(User user) {
        Set<Role> roleSet = new HashSet<>();
        // все роли


        for (String role : user.getRoleSetTemp()) {
            roleSet.add(roleService.getAuthByName(role));
        }

        user.setRoleSet(roleSet);
        userService.saveUser(user);
    }/**/

    //api удаления
    @DeleteMapping(value = "/api/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(userService.getUserById(id));
        return new ResponseEntity(HttpStatus.OK);
    }

}
