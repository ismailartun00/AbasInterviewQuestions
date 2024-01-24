import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1000, 2000, 12, 100.51));
        orders.add(new Order(1000, 2001, 31, 200));
        orders.add(new Order(1000, 2002, 22, 150.86));
        orders.add(new Order(1000, 2003, 41, 250));
        orders.add(new Order(1000, 2004, 55, 244));
        orders.add(new Order(1001, 2001, 88, 44.531));
        orders.add(new Order(1001, 2002, 121, 88.11));
        orders.add(new Order(1001, 2004, 74, 211));
        orders.add(new Order(1001, 2002, 14, 88.11));
        orders.add(new Order(1002, 2003, 2, 12.1));
        orders.add(new Order(1002, 2004, 3, 22.3));
        orders.add(new Order(1002, 2003, 8, 12.1));
        orders.add(new Order(1002, 2002, 16, 94));
        orders.add(new Order(1002, 2005, 9, 44.1));
        orders.add(new Order(1002, 2006, 19, 90));

        System.out.println("Total amount of all orders: " + calculateTotalAmount(orders));
        System.out.println("Average price of all orders: " + calculateAveragePrice(orders));
        System.out.println("Product-wise average price of all orders: " + calculateProductWiseAveragePrice(orders));
        System.out.println("Number of products in each order: " + calculateProductCountPerOrder(orders));
    }

    public static double calculateTotalAmount(List<Order> orders) {
        double totalAmount = 0;
        for (Order order : orders) {
            totalAmount += order.getQuantity() * order.getUnitPrice();
        }
        return totalAmount;
    }

    public static double calculateAveragePrice(List<Order> orders) {
        double totalAmount = calculateTotalAmount(orders);
        double totalQuantity = 0;
        for (Order order : orders) {
            totalQuantity += order.getQuantity();
        }
        return totalAmount / totalQuantity;
    }

    public static Map<Integer, Double> calculateProductWiseAveragePrice(List<Order> orders) {
        Map<Integer, Double> productAveragePrices = new HashMap<Integer, Double>();
        Map<Integer, Integer> productQuantities = new HashMap<Integer, Integer>();
        Map<Integer, Double> productTotalPrices = new HashMap<Integer, Double>();

        for (Order order : orders) {
            int productNumber = order.getProductNumber();
            int quantity = order.getQuantity();
            double unitPrice = order.getUnitPrice();

            if (!productQuantities.containsKey(productNumber)) {
                productQuantities.put(productNumber, 0);
                productTotalPrices.put(productNumber, 0.0);
            }

            productQuantities.put(productNumber, productQuantities.get(productNumber) + quantity);
            productTotalPrices.put(productNumber, productTotalPrices.get(productNumber) + quantity * unitPrice);
        }

        for (Map.Entry<Integer, Double> entry : productTotalPrices.entrySet()) {
            int productNumber = entry.getKey();
            double totalValue = entry.getValue();
            int totalQuantity = productQuantities.get(productNumber);

            double averageUnitPrice = totalValue / totalQuantity;
            productAveragePrices.put(productNumber, averageUnitPrice);
        }

        return productAveragePrices;
    }

    public static Map<Integer, Map<Integer, Integer>> calculateProductCountPerOrder(List<Order> orders) {
        Map<Integer, Map<Integer, Integer>> productCountPerOrder = new HashMap<Integer, Map<Integer, Integer>>();

        for (Order order : orders) {
            int orderNumber = order.getOrderNumber();
            int productNumber = order.getProductNumber();

            if (!productCountPerOrder.containsKey(orderNumber)) {
                productCountPerOrder.put(orderNumber, new HashMap<Integer, Integer>());
            }

            if (!productCountPerOrder.get(orderNumber).containsKey(productNumber)) {
                productCountPerOrder.get(orderNumber).put(productNumber, 0);
            }

            productCountPerOrder.get(orderNumber).put(productNumber, productCountPerOrder.get(orderNumber).get(productNumber) + order.getQuantity());
        }

        return productCountPerOrder;
    }
}

class Order {
    private int orderNumber;
    private int productNumber;
    private int quantity;
    private double unitPrice;

    public Order(int orderNumber, int productNumber, int quantity, double unitPrice) {
        this.orderNumber = orderNumber;
        this.productNumber = productNumber;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}