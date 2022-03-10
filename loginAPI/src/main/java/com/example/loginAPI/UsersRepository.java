package com.example.loginAPI;

import com.example.loginAPI.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository    //indicate that the class provides the mechanism for storage, retrieval, update, delete and search operation on objects
public interface UsersRepository extends JpaRepository<Users, Long> { // esta interfaz puede ir vacía hasta aquí
    @Query("SELECT u FROM Users u WHERE u.email=?1") // @Query ponemos el SQL que queremos que se ejecute
    Optional<Users> findByEmail(String email); //Optional es una clase que permite regresar null
                                               // findByEmail --> es un método que creamos que busca lo del paréntesis
} //interface UsersRepository
