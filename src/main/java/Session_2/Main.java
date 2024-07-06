package Session_2;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final List<Double> myData = Arrays.asList(7d, 4d, 5d, 6d, 3d, 8d, 10d);

    public static void main(String[] args) {

        //region Imperative
        System.out.println("Imperative way of doing things");
        for (double x : myData){
            System.out.println(subtractTen(square(addOne(x))));
        }
        System.out.println('\n');
        //endregion

        //region Declarative
        System.out.println("Declarative, Three Successive Calls");
        myData.stream().map(Main::addOne).map(Main::square).filter(x -> x < 70)
                .sorted().limit(2).map(Main::subtractTen).toList()
                .forEach(System.out::println);
        System.out.println('\n');
        //endregion



    }
    //region Details
    private static double addOne(double x){
        return x +1;
    }

    private static double square(double x){
        return Math.pow(x, 2);
    }

    private static double subtractTen(double x){
        return x - 10;
    }
    //endregion
}
