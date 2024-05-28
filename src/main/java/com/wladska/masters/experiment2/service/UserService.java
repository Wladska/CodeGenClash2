package com.wladska.masters.experiment2.service;

import com.wladska.masters.experiment2.exception.ResourceNotFoundException;
import com.wladska.masters.experiment2.model.User;
import com.wladska.masters.experiment2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Save a new user to the database.
     * @param user The user to save.
     * @return The saved user, with password encrypted.
     */
    public User saveUser(User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Find a user by their ID.
     * @param id The ID of the user.
     * @return An Optional containing the user if found, or an empty Optional if not found.
     */
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Failed to find user: " + id));
    }

    /**
     * Update an existing user's details.
     * @param id The ID of the user to update.
     * @param updatedUser The new details for the user.
     * @return The updated user, or null if the user could not be found.
     */
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(updatedUser.getEmail());
            user.setName(updatedUser.getName());
            user.setRoles(updatedUser.getRoles());  // Assuming a method to set roles
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("Failed to update user: " + id));
    }

    /**
     * Delete a user by their ID.
     * @param id The ID of the user.
     * @return true if the user was deleted, false if the user was not found.
     */
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
