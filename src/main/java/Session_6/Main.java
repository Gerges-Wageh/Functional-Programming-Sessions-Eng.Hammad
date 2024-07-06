package Session_6;

import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;


public class Main {
    public static void main(String[] args) {

        InvoicePath InvoicePath = new InvoicePath();
        AvailabilityPath AvailabilityPath = new AvailabilityPath();
        Pair<Order, ProcessConfiguration> p = setConfiguration();
        Function<Order, Double> costOfOrder = calcAdjustedCostOfOrder(p.getValue(), InvoicePath, AvailabilityPath);
        System.out.println(costOfOrder.apply(p.getKey()));

        
    }

    //Setup of the Process Configuration and Data
    public static Pair<Order, ProcessConfiguration> setConfiguration() {
        ProcessConfiguration processConfiguration = new ProcessConfiguration();
        Customer customer = new Customer();
        Order order = new Order();
        processConfiguration.invoiceChoice = InvoiceChoice.INVOICE_CHOICE_3;
        processConfiguration.shippingChoice = ShippingChoice.SHIPPING_CHOICE_3;
        processConfiguration.freightChoice = FreightChoice.FREIGHT_CHOICE_3;
        processConfiguration.availabilityChoice = AvailabilityChoice.AVAILABILITY_CHOICE_2;
        processConfiguration.shippingDateChoice = ShippingDateChoice.SHIPPING_DATE_CHOICE_2;
        order.customer = customer;
        order.date = LocalDate.of(2024, 7, 5);    ;
        order.cost = 2000;
        return new Pair<>(order, processConfiguration);
    }

    // Adjusted cost for order
    public static Function<Order, Double> calcAdjustedCostOfOrder
    (ProcessConfiguration c, InvoicePath InvoicePath, AvailabilityPath AvailabilityPath) {
        return
                (x) -> adjustCost(x, invoicePathFunc(c, InvoicePath), availabilityPathFunc(c, AvailabilityPath));
    }



    // Construction of adjustCost function
    public static double adjustCost (Order r, Function<Order,Freight> calcFreight
            , Function<Order, ShippingDate> calcShippingDate){

        Freight f = calcFreight.apply(r);
        ShippingDate s = calcShippingDate.apply(r);
        System.out.println("\n\nDay of Shipping: " + s.date.getDayOfWeek().toString() + "\n");


        double cost = (Objects.equals(s.date.getDayOfWeek().toString(), "Monday")) ? f.cost + 1000 : f.cost + 500;

        ///Final Cost
        return cost;
    }

    // Construction of the invoicePath composed function
    public static Function<Order, Freight> invoicePathFunc(ProcessConfiguration c, InvoicePath ip){
        Function<Order, Invoice> invoiceFunction = ip.invoiceFunctions.stream()
                .filter(x -> x.getKey() == c.invoiceChoice)
                .map(Pair::getValue)
                .findFirst().get();

        Function<Invoice, Shipping> shippingFunction = ip.shippingFunctions.stream()
                .filter(x -> x.getKey() == c.shippingChoice)
                .map(Pair::getValue)
                .findFirst().get();

        Function<Shipping, Freight> freightFunction = ip.freightFunctions.stream()
                .filter(x -> x.getKey() == c.freightChoice)
                .map(Pair::getValue)
                .findFirst().get();

        return invoiceFunction.andThen(shippingFunction).andThen(freightFunction);
    }


    // Construction of the availabilityPath composed function
    public static Function<Order, ShippingDate> availabilityPathFunc (ProcessConfiguration c, AvailabilityPath ap){
        Function<Order, Availability> availabilityFunction = ap.availabilityFunctions.stream()
                .filter(x -> x.getKey() == c.availabilityChoice)
                .map(Pair::getValue)
                .findFirst().get();

        Function<Availability, ShippingDate> shippingDateFunction = ap.shippingDateFunctions.stream()
                .filter(x -> x.getKey() == c.shippingDateChoice)
                .map(Pair::getValue)
                .findFirst().get();

        return availabilityFunction.andThen(shippingDateFunction);

    }

    // Invoice path data
    public static class InvoicePath {
        public List<Pair<InvoiceChoice , Function<Order, Invoice>>> invoiceFunctions;
        public List<Pair<ShippingChoice, Function<Invoice, Shipping>>> shippingFunctions;
        public List<Pair<FreightChoice, Function<Shipping, Freight>>> freightFunctions;

        public InvoicePath(){
            invoiceFunctions = new ArrayList<>(Arrays.asList(
                    new Pair<>(InvoiceChoice.INVOICE_CHOICE_1, Main::calcInvoice1),
                    new Pair<>(InvoiceChoice.INVOICE_CHOICE_2, Main::calcInvoice2),
                    new Pair<>(InvoiceChoice.INVOICE_CHOICE_3, Main::calcInvoice3),
                    new Pair<>(InvoiceChoice.INVOICE_CHOICE_4, Main::calcInvoice4),
                    new Pair<>(InvoiceChoice.INVOICE_CHOICE_5, Main::calcInvoice5)
            ));
            shippingFunctions = new ArrayList<>(Arrays.asList(
                    new Pair<>(ShippingChoice.SHIPPING_CHOICE_1, Main::calcShipping1),
                    new Pair<>(ShippingChoice.SHIPPING_CHOICE_2, Main::calcShipping2),
                    new Pair<>(ShippingChoice.SHIPPING_CHOICE_3, Main::calcShipping3)
            ));
            freightFunctions = new ArrayList<>(Arrays.asList(
                    new Pair<>(FreightChoice.FREIGHT_CHOICE_1, Main::calcFreightCost1),
                    new Pair<>(FreightChoice.FREIGHT_CHOICE_2, Main::calcFreightCost2),
                    new Pair<>(FreightChoice.FREIGHT_CHOICE_3, Main::calcFreightCost3),
                    new Pair<>(FreightChoice.FREIGHT_CHOICE_4, Main::calcFreightCost4),
                    new Pair<>(FreightChoice.FREIGHT_CHOICE_5, Main::calcFreightCost5),
                    new Pair<>(FreightChoice.FREIGHT_CHOICE_6, Main::calcFreightCost6)
            ));
        }
    }


    // Availability path data
    public static class AvailabilityPath{

        public List<Pair<AvailabilityChoice, Function<Order, Availability>>> availabilityFunctions;
        public List<Pair<ShippingDateChoice, Function<Availability, ShippingDate>>> shippingDateFunctions;

        public AvailabilityPath() {
            availabilityFunctions = new ArrayList<>(Arrays.asList(
                    new Pair<>(AvailabilityChoice.AVAILABILITY_CHOICE_1, Main::calcAvailability1),
                    new Pair<>(AvailabilityChoice.AVAILABILITY_CHOICE_2, Main::calcAvailability2),
                    new Pair<>(AvailabilityChoice.AVAILABILITY_CHOICE_3, Main::calcAvailability3),
                    new Pair<>(AvailabilityChoice.AVAILABILITY_CHOICE_4, Main::calcAvailability4)
            ));
            shippingDateFunctions = new ArrayList<>(Arrays.asList(
                    new Pair<>(ShippingDateChoice.SHIPPING_DATE_CHOICE_1, Main::calcShippingDate1),
                    new Pair<>(ShippingDateChoice.SHIPPING_DATE_CHOICE_2, Main::calcShippingDate2),
                    new Pair<>(ShippingDateChoice.SHIPPING_DATE_CHOICE_3, Main::calcShippingDate3),
                    new Pair<>(ShippingDateChoice.SHIPPING_DATE_CHOICE_4, Main::calcShippingDate4),
                    new Pair<>(ShippingDateChoice.SHIPPING_DATE_CHOICE_5, Main::calcShippingDate5)
            ));
        }
    }




    // Business choices to generate Invoice document
    public static Invoice calcInvoice1(Order o) {
        System.out.println("Invoice 1");
        Invoice invoice = new Invoice();
        invoice.cost = o.cost * 1.1;
        return invoice;
    }
    public static Invoice calcInvoice2(Order o) {
        System.out.println("Invoice 2");
        Invoice invoice = new Invoice();
        invoice.cost = o.cost * 1.2;
        return invoice;
    }
    public static Invoice calcInvoice3(Order o) {
        System.out.println("Invoice 3");
        Invoice invoice = new Invoice();
        invoice.cost = o.cost * 1.3;
        return invoice;
    }
    public static Invoice calcInvoice4(Order o) {
        System.out.println("Invoice 4");
        Invoice invoice = new Invoice();
        invoice.cost = o.cost * 1.4;
        return invoice;

    }
    public static Invoice calcInvoice5(Order o) {
        System.out.println("Invoice 5");
        Invoice invoice = new Invoice();
        invoice.cost = o.cost * 1.5;
        return invoice;
    }



    // Business choices to generate Shipping document
    public static Shipping calcShipping1(Invoice i) {
        System.out.println("Shipping 1");
        Shipping s = new Shipping();
        s.shipperID = (i.cost > 1000) ? 1 : 2;
        s.cost = i.cost;

        return s;
    }
    public static Shipping calcShipping2(Invoice i) {
        System.out.println("Shipping 2");
        Shipping s = new Shipping();
        s.shipperID = (i.cost > 1100) ? 1 : 2;
        s.cost = i.cost;

        return s;
    }
    public static Shipping calcShipping3(Invoice i) {
        System.out.println("Shipping 3");
        Shipping s = new Shipping();
        s.shipperID = (i.cost > 1200) ? 1 : 2;
        s.cost = i.cost;

        return s;
    }



    // Business choices to generate FreightCost document
    public static Freight calcFreightCost1(Shipping s) {
        System.out.println("Freight 1");
        Freight f = new Freight();
        f.cost = (s.shipperID == 1) ? s.cost * 0.25 : s.cost * 0.5;
        return f;
    }
    public static Freight calcFreightCost2(Shipping s) {
        System.out.println("Freight 2");
        Freight f = new Freight();
        f.cost = (s.shipperID == 1) ? s.cost * 0.28 : s.cost * 0.52;
        return f;
    }
    public static Freight calcFreightCost3(Shipping s) {
        System.out.println("Freight 3");
        Freight f = new Freight();
        f.cost = (s.shipperID == 1) ? s.cost * 0.3 : s.cost * 0.6;
        return f;
    }
    public static Freight calcFreightCost4(Shipping s) {
        System.out.println("Freight 4");
        Freight f = new Freight();
        f.cost = (s.shipperID == 1) ? s.cost * 0.35 : s.cost * 0.65;
        return f;
    }
    public static Freight calcFreightCost5(Shipping s) {
        System.out.println("Freight 5");
        Freight f = new Freight();
        f.cost = (s.shipperID == 1) ? s.cost * 0.15 : s.cost * 0.2;
        return f;
    }
    public static Freight calcFreightCost6(Shipping s) {
        System.out.println("Freight 6");
        Freight f = new Freight();
        f.cost = (s.shipperID == 1) ? s.cost * 0.1 : s.cost * 0.15;
        return f;
    }



    // Business choices to generate Availability document
    public static Availability calcAvailability1(Order o) {
        System.out.println("Availability 1");
        Availability a = new Availability();
        a.date = o.date.withDayOfMonth(3);

        return a;
    }
    public static Availability calcAvailability2(Order o) {
        System.out.println("Availability 2");
        Availability a = new Availability();
        a.date = o.date.withDayOfMonth(2);

        return a;
    }
    public static Availability calcAvailability3(Order o) {
        System.out.println("Availability 3");
        Availability a = new Availability();
        a.date = o.date.withDayOfMonth(1);
        return a;
    }
    public static Availability calcAvailability4(Order o) {
        System.out.println("Availability 4");
        Availability a = new Availability();
        a.date = o.date.withDayOfMonth(4);

        return a;
    }



    // Business choices to generate ShippingDate document
    public static ShippingDate calcShippingDate1(Availability o) {
        System.out.println("ShippingDate 1");
        ShippingDate a = new ShippingDate();
        a.date = o.date.withDayOfMonth(1);

        return a;
    }
    public static ShippingDate calcShippingDate2(Availability o) {
        System.out.println("ShippingDate 2");
        ShippingDate a = new ShippingDate();
        a.date = o.date.withDayOfMonth(2);

        return a;
    }
    public static ShippingDate calcShippingDate3(Availability o) {
        System.out.println("ShippingDate 3");
        ShippingDate a = new ShippingDate();
        a.date = o.date.withDayOfMonth(14);

        return a;
    }
    public static ShippingDate calcShippingDate4(Availability o) {
        System.out.println("ShippingDate 4");
        ShippingDate a = new ShippingDate();
        a.date = o.date.withDayOfMonth(20);

        return a;
    }
    public static ShippingDate calcShippingDate5(Availability o) {
        System.out.println("ShippingDate 5");
        ShippingDate a = new ShippingDate();
        a.date = o.date.withDayOfMonth(10);
        return a;
    }


}


// Datatypes
class ProcessConfiguration {
    public InvoiceChoice invoiceChoice;
    public ShippingChoice shippingChoice;
    public FreightChoice freightChoice;
    public AvailabilityChoice availabilityChoice;
    public ShippingDateChoice shippingDateChoice;
}

class Customer {}

class Order {
    public Customer customer;
    public LocalDate date;
    public double cost;
}

// Invoice path datatypes
class Invoice {
    public double cost;
    public Invoice() {
        cost = 0;
    }
}

 class Shipping {
    public double cost;
    public int shipperID;
    public Shipping(){
        cost = 0;
    }
}

class Freight {
    public double cost;
    public Freight(){
        cost = 0;
    }

}

// Availability path datatypes
class Availability {
    public LocalDate date;
}

class ShippingDate {
    public LocalDate date;
}


// System configuration choices
 enum InvoiceChoice {
    INVOICE_CHOICE_1 ,
    INVOICE_CHOICE_2 ,
    INVOICE_CHOICE_3 ,
    INVOICE_CHOICE_4 ,
    INVOICE_CHOICE_5
}

 enum ShippingChoice {
    SHIPPING_CHOICE_1,
    SHIPPING_CHOICE_2,
    SHIPPING_CHOICE_3,
}

 enum FreightChoice {
    FREIGHT_CHOICE_1,
    FREIGHT_CHOICE_2,
    FREIGHT_CHOICE_3,
    FREIGHT_CHOICE_4,
    FREIGHT_CHOICE_5,
    FREIGHT_CHOICE_6
}

 enum AvailabilityChoice {
    AVAILABILITY_CHOICE_1,
    AVAILABILITY_CHOICE_2,
    AVAILABILITY_CHOICE_3,
    AVAILABILITY_CHOICE_4
}

 enum ShippingDateChoice {
    SHIPPING_DATE_CHOICE_1,
    SHIPPING_DATE_CHOICE_2,
    SHIPPING_DATE_CHOICE_3,
    SHIPPING_DATE_CHOICE_4,
    SHIPPING_DATE_CHOICE_5
}