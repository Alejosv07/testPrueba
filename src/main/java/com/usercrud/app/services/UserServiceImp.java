package com.usercrud.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usercrud.app.domains.User;
import com.usercrud.app.errors.UserErrors;
import com.usercrud.app.repository.UserRepository;

@Service
public class UserServiceImp implements IUserService{

	@Autowired
	UserRepository repository;
	
	@Override
	public User saveUser(User user) {
		return repository.save(user);
	}

	@Override
	public List<User> fetchUserList() {
		return repository.findAll();
	}

	@Override
	public User updateUser(User user) {
		return repository
				.save(user);
	}

	@Override
	public void deleteUserById(Long userId) {
		// TODO Auto-generated method stub
		repository.deleteById(userId);

	}

	@Override
	public User finById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElseThrow(() -> new UserErrors("We can't find this object"));
	}

}
