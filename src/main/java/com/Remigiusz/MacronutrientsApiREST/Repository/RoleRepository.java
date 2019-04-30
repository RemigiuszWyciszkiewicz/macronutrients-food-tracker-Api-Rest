package com.Remigiusz.MacronutrientsApiREST.Repository;


import com.Remigiusz.MacronutrientsApiREST.DAO.Role;
import com.Remigiusz.MacronutrientsApiREST.DAO.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}