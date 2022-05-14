package com.taisiia.shop.service;

import com.taisiia.shop.domain.dao.User;
import org.mapstruct.control.MappingControl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    User save(User user);

    User update(User user, Long id);

    User findById(Long id);

    void delete(Long id);

    Page<User> getPage(Pageable pageable);
    //function for password

    User getCurrentUser();

    User findUserByEmail(String email);

}
