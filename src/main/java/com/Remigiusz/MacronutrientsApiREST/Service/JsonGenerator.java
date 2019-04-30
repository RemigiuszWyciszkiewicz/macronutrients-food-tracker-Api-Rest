package com.Remigiusz.MacronutrientsApiREST.Service;

import com.Remigiusz.MacronutrientsApiREST.DAO.DayProductsConnection;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.List;

public class JsonGenerator {

    JSONArray getArray(List<DayProductsConnection> list)
    {
        JSONArray calculatedProductsReadyToSend=new JSONArray();
        for(DayProductsConnection a:list)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("name",a.getProduct().getName());
            jsonObject.put("calories",MacrosCalculator.calculate(a.getAmount(),a.getProduct().getCalories()));
            jsonObject.put("protein",MacrosCalculator.calculate(a.getAmount(),a.getProduct().getProtein()));
            jsonObject.put("fats",MacrosCalculator.calculate(a.getAmount(),a.getProduct().getFats()));
            jsonObject.put("carbohydrates",MacrosCalculator.calculate(a.getAmount(),a.getProduct().getCarbohydrates()));
            jsonObject.put("amount",a.getAmount());
            calculatedProductsReadyToSend.add(jsonObject);
        }
        return calculatedProductsReadyToSend;
    }

}
