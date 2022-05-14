package com.taisiia.shop.service.impl;

import com.taisiia.shop.domain.dao.User;
import com.taisiia.shop.repository.RoleRepository;
import com.taisiia.shop.repository.UserRepository;
import com.taisiia.shop.security.SecurityUtils;
import com.taisiia.shop.service.MailService;
import com.taisiia.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
   // private final MailService mailService;

    @Override
    public User save(User user) {
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(Set.of(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
//        mailService.sendMail(Map.ofEntries(Map.entry("firstName", user.getFirstName())
//                , Map.entry("lastName", user.getLastName())), user.getEmail(), "welcome");
        return user;
    }

    @Override
    @Transactional
    public User update(User user, Long id) {
        User userDb = findById(id);
        userDb.setEmail(user.getEmail());
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        return userDb;
    }

    @Override
    public User findById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityUtils.getCurrentUserName())
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }


}
