package com.library.service;

import com.library.dao.UserRepository;
import com.library.entity.User;
import com.library.exception.EntityNotFoundException;
import com.library.exception.ResourceIsAssignedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById (long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " is not found"));
    }

    public User editUserById (long id, User user) {
        User userFromDb = getUserById(id);
        userFromDb.copyValuesFrom(user); //not sure, that current solution is the best one; other way here - copy parameters in service, but current variant looks more accurate for me
        return userRepository.save(userFromDb);
    }

    public void deleteUserById(long id) {
        User user = getUserById(id);
        if (user.getBookList().size() <= 0) {
            userRepository.delete(user);
        } else {
            throw new ResourceIsAssignedException("User with id " + id + " cannot be removed cause it has not returned books");
        }
    }

    public Page<User> getAllUsers (int page) {
        return userRepository.findAll(PageRequest.of(page, 10)); //it is possible to return content only here, added pageable parameters just to provide more info
    }

}
