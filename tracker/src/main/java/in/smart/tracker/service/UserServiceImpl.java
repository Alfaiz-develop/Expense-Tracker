package in.smart.tracker.service;


import in.smart.tracker.entity.User;
import in.smart.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean regUser(User user) {
        try {
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User loginUser(String email, String password) {
         User validUser = userRepository.findByEmail(email);

         if(validUser != null && validUser.getPassword().equals(password)){
             return validUser;
         }
        return null;
    }
}
