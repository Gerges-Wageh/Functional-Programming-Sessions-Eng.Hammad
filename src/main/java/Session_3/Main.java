package Session_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javafx.util.Pair;

public class Main {
    public static void main(String[] args) {

        //region
        Function<Double, Double> dlgTest1 = Main::test1;
        Function<Double, Double> dlgTest2 = Main::test2;

        List<Function<Double, Double>> z = new ArrayList<>(Arrays.asList(
                dlgTest1,
                dlgTest2
        ));

        // The outer method can see the inner as a double value, so it's NOT a higher order function
        System.out.println(test2(test1(5)));
        System.out.println(test1(test2(5)));

        // Normal invocation via function delegates
        System.out.println(z.get(0).apply(5d));
        System.out.println(z.get(1).apply(5d));

        // test3 is a higher order function which takes a method reference as an argument
        System.out.println(test3(Main::test1, 5d));
        System.out.println(test3(Main::test2, 5d));

        //endregion


    }

    public static double test1(double x){
        return x / 2;
    }

    public static double test2(double x){
        return x/4 +1;
    }

    public static double test3(Function<Double,Double> f, double value){
        return f.apply(value) + value;
    }







}




