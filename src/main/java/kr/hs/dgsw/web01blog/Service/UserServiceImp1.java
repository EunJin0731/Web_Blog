package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp1 implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        Optional<User> found = this.userRepository.findByAccount(user.getAccount());
        if(found.isPresent()) return null;
        return this.userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        try{
            this.userRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public List<User> listUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        return this.userRepository.findById(id)
                .map(found -> {
                    found.setAccount(Optional.ofNullable(user.getAccount()).orElse(found.getAccount()));
                    found.setEmail(Optional.ofNullable(user.getEmail()).orElse(found.getEmail()));
                    found.setName(Optional.ofNullable(user.getName()).orElse(found.getName()));
                    found.setPassword(Optional.ofNullable(user.getPassword()).orElse(found.getPassword()));
                    found.setPhone(Optional.ofNullable(user.getPhone()).orElse(found.getPhone()));
                    found.setProfilePath(Optional.ofNullable(user.getProfilePath()).orElse(found.getProfilePath()));
                    return this.userRepository.save(found);
                })
                .orElse(null);
    }
}
