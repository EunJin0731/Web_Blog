package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseFormat addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseFormat deleteUser(@PathVariable Long userId){
        return this.userService.deleteUser(userId);
    }

    @GetMapping("/user")
    public ResponseFormat getUser(){
        return this.userService.getUser();
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseFormat updateUser(@PathVariable Long userId, @RequestBody User user){
        return this.userService.updateUser(userId, user);
    }

    @GetMapping("/findUser/{account}")
    public ResponseFormat findUser(@PathVariable String account){
        return this.userService.findUser(account);
    }
}
