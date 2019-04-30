
package com.Remigiusz.MacronutrientsApiREST.Repository;

import com.Remigiusz.MacronutrientsApiREST.DAO.Day;
import com.Remigiusz.MacronutrientsApiREST.DAO.Product;

import com.Remigiusz.MacronutrientsApiREST.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day,Integer> {



    List<Day> findAllByDate(Date date);
    Day findDayORMByDate(Date date);
    Day findDayORMByDateAndAndUserORM(Date date, User userORM);


}

