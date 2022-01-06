package myWeb.service;


import myWeb.dao.UserDao;
import myWeb.model.Role;
import myWeb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;

    @Autowired
    private RoleService roleService;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public void updateUser(User user, String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : user.getRoleSetTemp()) {
            roleSet.add(roleService.getAuthByName(role));
        }

        user.setRoleSet(roleSet);
        saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id)
                .orElse(null);
    }

    @Override
    public void editUser(User user) {
        userDao.saveAndFlush(user);
    }

}
