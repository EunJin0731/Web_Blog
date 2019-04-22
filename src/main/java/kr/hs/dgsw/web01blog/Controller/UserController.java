package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable Long id){
        return this.userService.deleteUser(id);
    }

    @GetMapping("/user")
    public List<User> listUser(){
        return this.userService.listUser();
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return this.userService.updateUser(id, user);
    }
}
