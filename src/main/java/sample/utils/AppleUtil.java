package sample.utils;

import sample.entity.Apple;
import sample.enums.AppleColorEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class AppleUtil {
    /**
     * 过滤苹果
     * @param apples
     * @param predicate
     * @return
     */
    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> appleList = new ArrayList<>();
        for (Apple apple: apples ) {
            if (predicate.test(apple)) {
                appleList.add(apple);
            }
        }
        return appleList;
    }
    public static boolean isGreenApple(Apple apple) {
        return AppleColorEnums.GREEN.COLOR.equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }
}
