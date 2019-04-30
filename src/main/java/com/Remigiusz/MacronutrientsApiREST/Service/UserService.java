package com.Remigiusz.MacronutrientsApiREST.Service;

import com.Remigiusz.MacronutrientsApiREST.DAO.DayORM;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception400_BAD_REQUEST;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception401_UNAUTHORIZED;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception404_NOT_FOUND;
import com.Remigiusz.MacronutrientsApiREST.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


/*
    @Transactional
    public void saveUser(UserORM userORM)
    {
        if(findUserbyEmail(userORM.getEmail())== null) userRepository.save(userORM);
        else throw new Exception400_BAD_REQUEST("this email is already taken");
    }

    @Transactional
    UserORM findUserbyEmail(String email)
    {
       return userRepository.findByEmail(email).orElseGet(() -> null);
    }

    @Transactional
    public UserORM findUserById(int id)
    {
       UserORM userORM=userRepository.findById(id)
               .orElseThrow(() -> {throw new Exception404_NOT_FOUND("User with id - "+id+" does not exists");});

         return userORM;
    }
    @Transactional
    public List<DayORM> getDaysByUser(int id)
    {
    return findUserById(id).getDayORMS();
    }

    @Transactional
    public UserORM authentication(String pass,String emial)
    {
       return userRepository.findByPasswordAndEmail(pass,emial).orElseThrow(() -> new Exception401_UNAUTHORIZED());
    }
*/



}
