package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    boolean deleteUser(Long id);

    List<User> listUser();

    User updateUser(Long id, User user);
}
