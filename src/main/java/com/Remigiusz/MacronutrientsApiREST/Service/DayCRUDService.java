
package com.Remigiusz.MacronutrientsApiREST.Service;

import com.Remigiusz.MacronutrientsApiREST.DAO.Day;
import com.Remigiusz.MacronutrientsApiREST.DAO.DayProductsConnection;
import com.Remigiusz.MacronutrientsApiREST.DAO.Product;
import com.Remigiusz.MacronutrientsApiREST.DAO.User;
import com.Remigiusz.MacronutrientsApiREST.Repository.ConnectionRepository;
import com.Remigiusz.MacronutrientsApiREST.Repository.DayRepository;
import com.Remigiusz.MacronutrientsApiREST.Utility.JsonGenerator;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class DayCRUDService {

    JsonGenerator jsonGenerator=new JsonGenerator();

    @Autowired DayRepository dayRepository;
    @Autowired ConnectionRepository connectionRepository;
    @Autowired ProductCRUDService productCRUDService;
    @Autowired UserService userService;

    @Transactional
    public void addNewDay(long id,String date)
    {
        Day dayORM=new Day();
        dayORM.setDate(addOneDayToDate(date));

        User userORM1=userService.findUserById(id);
        dayORM.setUserORM(userORM1);
        dayRepository.save(dayORM);
    }

    private Date addOneDayToDate(String date)
    {
        Calendar  calendar=Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE,1);
        Date dateWithDayAdded=calendar.getTime();
        return dateWithDayAdded;
    }



    @Transactional
    public void saveConnection(JSONObject s,int id) throws ParseException {

        DayProductsConnection connection = new DayProductsConnection();
           Day dayORM=dayRepository
                   .findDayORMByDateAndAndUserORM(addOneDayToDate(s.getAsString("day")),userService.findUserById(id));

        connection.setAmount((int)s.getAsNumber("amount"));
        connection.setProduct(productCRUDService.getProductbyName(s.getAsString("product")));
        connection.setDay(dayORM);

        connectionRepository.save(connection);
    }

    @Transactional
    public JSONArray getProductsOfDay(String date,int id)
    {
        Day dayORM = dayRepository.findDayORMByDateAndAndUserORM(addOneDayToDate(date),userService.findUserById(id));
        return jsonGenerator.getArray(dayORM.getListOfConnections());
    }
    @Transactional
    public void deleteConnection(String product,String date,int amount,int id)
    {
        Day dayORM=dayRepository.findDayORMByDateAndAndUserORM(addOneDayToDate(date),userService.findUserById(id));

        Product productORM=productCRUDService.getProductbyName(product);
        DayProductsConnection connectionORM=connectionRepository.getDayProductsConnectionORMByProductAndDayAndAmount(productORM,dayORM,amount);
            connectionORM.setProduct(null);
            connectionORM.setDay(null);

            connectionRepository.delete(connectionORM);
    }

}

