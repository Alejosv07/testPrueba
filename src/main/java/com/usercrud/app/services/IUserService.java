package com.usercrud.app.services;
import java.util.List;

import com.usercrud.app.domains.User;

public interface IUserService {
    // Save operation 
	User saveUser(User user); 
  
    // Read operation 
    List<User> fetchUserList(); 
  
    // Update operation 
    User updateUser(User user);
    
    // Find by user name
    User finById(Long id);
    
    // Delete operation 
    void deleteUserById(Long userId);
}
