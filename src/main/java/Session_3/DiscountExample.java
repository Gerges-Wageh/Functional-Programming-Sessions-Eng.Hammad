package Session_3;

import javafx.util.Pair;
import java.util.function.Function;

public class DiscountExample {

    private static Function<Integer, Pair<Double, Double>> A = DiscountExample::productParametersFood;
    private static Function<Integer, Pair<Double, Double>> B = DiscountExample::productParametersBeverage;
    private static Function<Integer, Pair<Double, Double>> C = DiscountExample::productParametersRawMaterial;
    private static final Order order = new Order(10,100,20,4);


    public static void main(String[] args) {

        ProductType product = ProductType.Food;
        Function<Integer, Pair<Double, Double>> P = product == ProductType.Food ? A : (product ==  ProductType.Beverage ? B: C);

        double discount = calculateDiscount(P, order);
        System.out.println(discount);

    }

    public static double calculateDiscount(Function<Integer, Pair<Double, Double>> productParameterCalc, Order Order) {
        Pair<Double, Double> parameters = productParameterCalc.apply(Order.ProductIndex);
        return parameters.getKey() * Order.Quantity + parameters.getValue() * Order.UnitPrice;
    }

    public static Pair<Double, Double> productParametersFood(int ProductIndex) {
        return new Pair(ProductIndex / (double) (ProductIndex + 100),
                ProductIndex / (double) (ProductIndex + 300));
    }

    public static Pair<Double, Double> productParametersBeverage(int ProductIndex) {
        return new Pair(ProductIndex / (double) (ProductIndex + 300)
                , ProductIndex / (double) (ProductIndex + 400));
    }

    public static Pair<Double, Double> productParametersRawMaterial(int ProductIndex) {
        return new Pair(ProductIndex / (double) (ProductIndex + 400),
                ProductIndex / (double) (ProductIndex + 700));
    }

}

class Order{
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

enum ProductType{
    Food,
    Beverage,
    RawMaterial
}