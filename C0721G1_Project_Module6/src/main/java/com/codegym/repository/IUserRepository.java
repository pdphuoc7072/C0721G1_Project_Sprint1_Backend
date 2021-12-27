package com.codegym.repository;

import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, Long> {

    //    edit password TinhBT
//    @Modifying
//    @Query(value = "update `user` set `password` = ?2 WHERE id = ?1;", nativeQuery = true)
//    void editPassword(Long id ,String password);
}
