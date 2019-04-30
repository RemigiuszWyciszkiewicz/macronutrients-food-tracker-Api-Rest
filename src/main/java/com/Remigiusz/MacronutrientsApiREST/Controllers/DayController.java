/*
package com.Remigiusz.MacronutrientsApiREST.Controllers;

import com.Remigiusz.MacronutrientsApiREST.DAO.DayORM;
import com.Remigiusz.MacronutrientsApiREST.DAO.DayProductsConnectionORM;
import com.Remigiusz.MacronutrientsApiREST.DAO.ProductORM;

import com.Remigiusz.MacronutrientsApiREST.Service.ProductCRUDService;
import com.Remigiusz.MacronutrientsApiREST.Service.UserService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController("/")
public class DayController {

    @Autowired
    DayCRUDService dayCRUDService;
    @Autowired
    ProductCRUDService productCRUDService;
    @Autowired
    UserService userService;

    @PostMapping("/day/{id}")
    public void createDayForSpecificUser(@PathVariable(name = "id", required = true) int userId,@RequestParam(name = "date",required = true) String s)
    {
        System.out.println(s);

        dayCRUDService.addNewDay(userId,s);
    }

    @GetMapping("/days/{id}")
    List<DayORM> getDays(@PathVariable(name = "id", required = true) int userId)
    {
        List<DayORM> dayORMS=userService.getDaysByUser(userId);
        return dayORMS;
    }

    @PostMapping("/productOfDay/{id}")
    void createConnection(@RequestBody JSONObject s,@PathVariable(name = "id",required = true) int userId) throws ParseException {

        System.out.println(s);
        dayCRUDService.saveConnection(s,userId);
    }

    @GetMapping("/productOfDay/{id}")
    JSONArray getProducts(@RequestParam(name = "date") String s,@PathVariable(name = "id",required = true) int userId)
    {
        return dayCRUDService.getProductsOfDay(s,userId);
    }

    @DeleteMapping("/productsOfDay/{id}")
    void deleteProduct(@RequestParam(name = "name") String productName,@RequestParam(name = "date") String date,@RequestParam(name = "amount") int amount,@PathVariable(name = "id",required = true) int userId)
    {

        dayCRUDService.deleteConnection(productName,date,amount,userId);
    }
}
*/
