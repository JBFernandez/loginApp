package com.example.loginAPI;


import com.example.loginAPI.model.Users;
import com.example.loginAPI.utils.SHAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service // used to mark the class as a service provider, used with classes that provide some business functionalities
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired // Marks a constructor, field, setter method or config method as to be autowired by Spring's dependency injection facilities.
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    } //Constructor

    public List<Users> getAllUsers() {   // getAllUUsers que regresa un objeto List con objetos tipo Users (una lista de usuarios)
        return usersRepository.findAll();
    } //getAllUsers()

    public Users getUser(Long id) {
        return usersRepository.findById(id).orElseThrow(()->new IllegalStateException("The user with id: " + id + " does not exist"));
    } //getUser

    public void deleteUser(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
        }//if
        else {
            throw new IllegalStateException("The user with id: " + id + " does not exist");
        }//else
    } //deleteUser

    public void addUsers (Users users) {
        Optional<Users> userByEmail = usersRepository.findByEmail(users.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("El usuario con el email: " + users.getEmail() + " Ya existe");
        }//if
        usersRepository.save(users);
    }//addUsers

    @Transactional
    public void updateUser(Long id, String originalPassword, String newPassword, String name, String email){
        Users user = usersRepository.findById(id).orElseThrow(()-> new IllegalStateException("El usuario con el id" + id + "no existe"));
        if (name !=null)
            if ((!name.isEmpty()) && (! name.equals(user.getName()))){
                user.setName(name);
            }//updateNombre
        if( email !=null)
            if ((!email.isEmpty()) && (! email.equals(user.getEmail()) ) ){
                user.getEmail();
            }//updateEmail

        if ((originalPassword == null) || (newPassword==null)){
            throw new IllegalStateException("El usuario con el id [" + id +
                    "] no existe");
        }//if updatePassword

        if (! SHAUtil.verifyHash(originalPassword, user.getPassword())){
            throw new IllegalStateException("La contraseña del Usuario con el id [" + id + "[ no coincide");
        }//verifyHash
        user.setPassword(newPassword);
    }//updateUsuario

}//Class UsersService
