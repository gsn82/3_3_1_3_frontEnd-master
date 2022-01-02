package myWeb;

import myWeb.model.Role;
import myWeb.model.User;
import myWeb.service.RoleService;
import myWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public Application(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) throws Exception {

        roleService.saveRole(new Role(1L,"Admin"));
        roleService.saveRole(new Role(2L,"User"));

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.getAuthByName("Admin"));
        roleSet.add(roleService.getAuthByName("User"));

        Set<Role> roleSetUser = new HashSet<>();
        roleSetUser.add(roleService.getAuthByName("User"));

        User admin = new User("Admin","Man", (byte) 19,"Admin@mail","root",roleSet);
        admin.setRoleSetTemp(new String[]{"Admin", "User"});
        User user = new User("User", "Guy", (byte) 17, "User@mail","user", roleSetUser);
        user.setRoleSetTemp(new String[]{"User"});

        userService.saveUser(admin);
        userService.saveUser(user);
    }
}
