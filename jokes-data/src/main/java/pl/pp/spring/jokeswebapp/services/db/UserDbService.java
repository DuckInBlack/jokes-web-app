package pl.pp.spring.jokeswebapp.services.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.repositories.UserRepository;
import pl.pp.spring.jokeswebapp.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@Profile("db")
public class UserDbService implements UserService {

    private Logger log = LoggerFactory.getLogger(UserDbService.class);
    private final UserRepository userRepository;

    public UserDbService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        log.info("finding all");

        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findById(Long id) {
        log.info("finding by id: {}", id);

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        log.info("saving user: {}", user.getUsername());

        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        log.info("delete user by id: {}", id);
        userRepository.deleteById(id);
    }


}
