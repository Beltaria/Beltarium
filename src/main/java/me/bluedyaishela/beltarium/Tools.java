package me.bluedyaishela.beltarium;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Tools {
    public static List<String> getAllDeclaredFields;

    public static void init()
    {
        initAllDeclaredFields();
    }

    private static void initAllDeclaredFields()
    {
        Field[] fields = ItemManager.class.getDeclaredFields();
        List<String> availableAttribute = new ArrayList<>();
        for (Field field : fields) {
            availableAttribute.add(field.getName());
        }
        getAllDeclaredFields = availableAttribute;
    }
}
