package com.Remigiusz.MacronutrientsApiREST.Repository;

import com.Remigiusz.MacronutrientsApiREST.DAO.DayORM;
import com.Remigiusz.MacronutrientsApiREST.DAO.User;
import com.Remigiusz.MacronutrientsApiREST.Service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

/*    Optional<UserORM> findByEmail(String emial);
    Optional<UserORM> findByPasswordAndEmail(String password,String email);*/

}
