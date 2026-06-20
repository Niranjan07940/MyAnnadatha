package com.example.demo.Repository;

import com.example.demo.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    User save(User user);

    User findByPhoneNumber(String phoneNumber);
    User findByPhoneNumber(Long phoneNumber);
}
