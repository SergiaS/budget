package com.budget.util;

import java.util.List;
import java.util.Map;

public class CategoryMap {
    public static Map<String, List<String>> mapCategoriesENG = Map.of(
            "Sport", List.of("Bicycle", "Bodybuilding", "Walking", "Other"),
            "Health", List.of("Pharmacy", "Doctor", "Dietary supplements", "Treatment", "Other"),
            "Food", List.of("Groceries", "Catering Operations", "Yummy", "Other"),
            "Finance", List.of("Bonds", "Deposit", "Gift", "Salary", "Sale")
    );

    public static Map<String, List<String>> mapCategoriesRUS = Map.of(
            "Спорт", List.of("Велосипед", "Бодибилдинг", "Ходьба", "Другое"),
            "Здоровье", List.of("Аптека", "Врач", "БАДы", "Лечение", "Другое"),
            "Питание", List.of("Продукты", "Общественное питание", "Нямки"),
            "Финансы", List.of("Облигации", "Депозит", "Подарок", "Зарплата", "Продажа")
    );

    public static void main(String[] args) {
        System.out.println(mapCategoriesENG.get("Food"));
    }
}
