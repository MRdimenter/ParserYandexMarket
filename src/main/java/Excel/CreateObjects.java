package Excel;

import API.ApiMethods;
import Data.Data;
import Data.MainInformation;
import com.google.gson.Gson;

/**
 * Класс отвечающий за создание объектов при помощи GSON
 */

public class CreateObjects {
    ApiMethods apiMethods = new ApiMethods();


    /**
     * -- Создание объекта Data при помощи Gson --
     * Отвечает за вывод характеристик товаров
     */
    public Data getData(String id) {
        return new Gson().fromJson(apiMethods.getJsonByApi("https://ymscanner.com/api/specs", id), Data.class);
    }


    /**
     * -- Создание объекта MainInformation при помощи Gson --
     * Отвечает за вывод главной информации о товаре
     */

    public MainInformation getMainInformation(String id) {
        return new Gson().fromJson(apiMethods.getJsonByApi("https://ymscanner.com/api/info", id), MainInformation.class);

    }


}
