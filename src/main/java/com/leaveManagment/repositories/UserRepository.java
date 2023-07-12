package com.leaveManagment.repositories;

import com.leaveManagment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByMatricule(String matricule);
    void deleteUserByMatricule(String matricule);
    User findUserByMatriculeAndPassword(String matricule , String password);

    @Query("select u from User u where u.archiveDate < ?1")
    List<User> findUsersByArchiveDateBefore(LocalDate date);
    @Query("select u from User u where u.isArchive = false")
    List<User> findAllByIsArchiveFalse();
}