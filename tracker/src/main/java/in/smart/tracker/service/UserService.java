package in.smart.tracker.service;


import in.smart.tracker.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {

    public boolean regUser(User user);
    public User loginUser(String email, String password);
}
