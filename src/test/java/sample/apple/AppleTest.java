package sample.apple;

import org.junit.Before;
import org.junit.Test;
import sample.entity.Apple;
import sample.enums.AppleColorEnums;
import sample.utils.AppleUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AppleTest {
    List<Apple> apples;
    @Before
    public void before() {
        apples = Arrays.asList(new Apple(AppleColorEnums.GREEN.COLOR, 12.), new Apple(AppleColorEnums.GREEN.COLOR, 160.1), new Apple(AppleColorEnums.RED.COLOR, 12.2), new Apple(AppleColorEnums.RED.COLOR, 160.3));
    }
    @Test
    public void test() {
        List<Apple> appleList = AppleUtil.filterApples(apples, AppleUtil::isGreenApple);
        System.out.println(appleList);
        appleList = AppleUtil.filterApples(apples, AppleUtil::isHeavyApple);
        System.out.println(appleList);
    }
    @Test
    public void test1() {
        List<Apple> heavyApples = apples.stream().filter((Apple a) -> a.getWeight() > 150).collect(Collectors.toList());
        System.out.println(heavyApples);
        heavyApples = apples.parallelStream().filter((Apple a) -> a.getWeight() > 150).collect(Collectors.toList());
        System.out.println(heavyApples);
    }
    @Test
    public void test2() {
//        apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) );
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(apples);
    }

}
