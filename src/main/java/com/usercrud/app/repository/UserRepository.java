package com.usercrud.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usercrud.app.domains.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
