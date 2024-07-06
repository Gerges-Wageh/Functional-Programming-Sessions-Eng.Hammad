package Session_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    private static final List<Double> myList = new ArrayList<>(Arrays.asList(3.0 , 5.0, 7.0, 8.0));

    private static final Function<Double, Double> myComposedFunction =
            composeFunction(Main::addOne, Main::square, Main::subtractTen);


    public static void main(String[] args) {

//        Function<Double, Double> myTestReturned = test();
//        Supplier<Function<Double, Double>> myTest = Main::test;
//        double res = myTestReturned.apply(5.0);
//        System.out.println(res);


        myList.stream().map(Main::addOne).map(Main::square).map(Main::subtractTen)
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------");

        myList.stream().map(x -> subtractTen(square(addOne(x)))).forEach(System.out::println);
        System.out.println("----------------------------------------------------");


        myList.stream().map(myComposedFunction).forEach(System.out::println);
        System.out.println("----------------------------------------------------");

        // Pay attention to the () after the method name as we need the returned function not the function body
        // Remember our first example of test, test()
        myList.stream().map(addOneSquareSubtractTen()).forEach(System.out::println);
        System.out.println("----------------------------------------------------");



    }

    // Smart composer
    private static Function<Double, Double> addOneSquareSubtractTen(){
        Function<Double, Double> q1 = Main::addOne;
        Function<Double, Double> q2 = Main::square;
        Function<Double, Double> q3 =Main::subtractTen;

        return q1.andThen(q2).andThen(q3);
    }




    private static Function<Double, Double>  composeFunction
            (Function<Double, Double> f1, Function<Double,Double> f2, Function<Double,Double> f3){
        return
                x -> f3.apply(f2.apply(f1.apply(x)));
    }

    private static double addOne(double x){
        return x + 1;
    }

    private static double square(double x){
        return Math.pow(x, 2);
    }
    private static double subtractTen(double x){
        return x - 10;
    }

    private static Function<Double, Double> test(){
        return x -> x + 1;
    }


}
