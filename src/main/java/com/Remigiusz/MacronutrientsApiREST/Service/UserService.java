package com.Remigiusz.MacronutrientsApiREST.Service;

import com.Remigiusz.MacronutrientsApiREST.DAO.Day;
import com.Remigiusz.MacronutrientsApiREST.DAO.Product;
import com.Remigiusz.MacronutrientsApiREST.DAO.User;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception400_BAD_REQUEST;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception404_NOT_FOUND;
import com.Remigiusz.MacronutrientsApiREST.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    @Transactional
    public void saveUser(User userORM)
    {
        if(findUserbyEmail(userORM.getEmail())== null) userRepository.save(userORM);
        else throw new Exception400_BAD_REQUEST("this email is already taken");
    }

    @Transactional
    User findUserbyEmail(String email)
    {
       return userRepository.findByEmail(email).orElseGet(() -> null);
    }

    @Transactional
    public User findUserById(long id)
    {
        User userORM=userRepository.findById(id)
               .orElseThrow(() -> {throw new Exception404_NOT_FOUND("User with id - "+id+" does not exists");});

         return userORM;
    }
    @Transactional
    public List<Day> getDaysByUser(int id)
    {
    return findUserById(id).getDays();
    }



}
