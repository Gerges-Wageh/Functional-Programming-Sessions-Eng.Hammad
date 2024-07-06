package Session_4;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main {

    private static final List<Order> orders = new ArrayList<>(Arrays.asList(
            new Order(150, 10, 1, 1000),
            new Order(50, 10, 3, 2000),
            new Order(50, 10, 3, 5000)
    ));


    public static void main(String[] args) {

        List<Pair<Function<Order, Boolean>, Function<Order, Double>>> rules =
                new ArrayList<>(Arrays.asList(
                        new Pair<>(Main::isAQualified, Main::A),
                        new Pair<>(Main::isBQualified, Main::B),
                        new Pair<>(Main::isCQualified, Main::C)
                ));

        System.out.println(orders.stream().map(x -> calculateOrderDiscount(x, rules)).toList());

    }

    private static double calculateOrderDiscount
            (Order R, List<Pair<Function<Order, Boolean>, Function<Order, Double>>> rules){

        double discount =  rules.stream().filter(x -> x.getKey().apply(R)).map(x -> x.getValue().apply(R))
                .mapToDouble(Double::doubleValue).sorted().limit(2).average().orElse(0.0);


        return R.UnitPrice - discount;
    }


    // Rules

    // Rule_1
    private static boolean isAQualified(Order R){
        return true;
    }
    private static double A(Order R){
        return 50;
    }

    // Rule_2
    private static boolean isBQualified(Order R){return true;}
    private static double B(Order R){return 60;}

    // Rule_3
    private static boolean isCQualified(Order R){return true;}
    private static double C(Order R){return 80;}

}


class Order {
    public int OrderID;
    public int ProductIndex;
    public double Quantity;
    public double UnitPrice;

    public Order(int OrderID, int ProductIndex, double Quantity, double UnitPrice) {
        this.OrderID = OrderID;
        this.ProductIndex = ProductIndex;
        this.Quantity = Quantity;
        this.UnitPrice = UnitPrice;
    }
}