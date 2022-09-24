package pl.cd.project.gamesstore.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import pl.cd.project.gamesstore.model.common.TypeOfUserEnum;
import pl.cd.project.gamesstore.model.pg.UserApp;
import pl.cd.project.gamesstore.repository.UserAppRepository;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(UserApp user) {
        String email = user.getEmail();
        if (userAppRepository.findByEmail(email) != null) {
            return false;
        } else {
            user.setActive(true);
            if (userAppRepository.findAll().isEmpty()) {
                user.getTypeOfUser().add(TypeOfUserEnum.ADMIN);
            } else {
                user.getTypeOfUser().add(TypeOfUserEnum.BUYER);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            log.info("Saving new user with email: {}", email);
            userAppRepository.save(user);
            return true;
        }
    }

    public List<UserApp> list() {
        return userAppRepository.findAll();
    }

    @PostMapping("/user/ban/{id}")
    public void banUser(Long id) {
        UserApp user = userAppRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("BANNED user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("UNBANNED user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userAppRepository.save(user);
    }

    public void changeUserRoles(UserApp user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(TypeOfUserEnum.values())
                .map(TypeOfUserEnum::name)
                .collect(Collectors.toSet());
        user.getTypeOfUser().clear();
        for (String key : form.keySet()) {
            if(roles.contains(key)) {
                user.getTypeOfUser().add(TypeOfUserEnum.valueOf(key));
            }
        }
        userAppRepository.save(user);
    }

    public UserApp getUserByPrincipal(Principal principal) {
        if (principal == null) return new UserApp();
        return userAppRepository.findByEmail(principal.getName());
    }

    public UserApp getUserByid(Long id) {
        UserApp user = userAppRepository.findById(id).orElse(null);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }


    public UserApp findByEmail(String email) {
        return userAppRepository.findByEmail(email);
    }

}
