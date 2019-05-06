package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;

import java.util.List;

public interface UserService {

    ResponseFormat getUser();

    ResponseFormat addUser(User user);

    ResponseFormat deleteUser(Long userId);

//    List<User> listUser();

    ResponseFormat updateUser(Long userId, User user);

    ResponseFormat findUser(String account);
}
