import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //Предположим, у нас есть список заказов, и каждый заказ представляет собой продукт и его стоимость.
        //Задача состоит в использовании Stream API и коллекторов для решения следующих задач:

        //1) Создайте список заказов с разными продуктами и их стоимостями.
        //2) Группируйте заказы по продуктам.
        //3) Для каждого продукта найдите общую стоимость всех заказов.
        //4) Отсортируйте продукты по убыванию общей стоимости.
        //5) Выберите три самых дорогих продукта.
        //6)Выведите результат: список трех самых дорогих продуктов и их общая стоимость.

        List<Order> order1 = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1700.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        List<Order> order2 = List.of(
                new Order("Headphones", 500.0),
                new Order("Keyboard", 800.0),
                new Order("Laptop", 3000.0),
                new Order("Tablet", 700.0),
                new Order("Smartphone", 1100.0)
        );

        List<Order> order3 = List.of(
                new Order("Headphones", 900.0),
                new Order("Keyboard", 2000.0),
                new Order("Mouse", 1500.0),
                new Order("Monitor", 500.0)
        );

        List<Order> order4 = List.of(
                new Order("Headphones", 300.0),
                new Order("Microphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Smartphone", 900.0)
        );

        List<List<Order>> allOrders = List.of(order1, order2, order3, order4);

        //2.

        Map<String, List<Order>> groupedOrders = allOrders.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.groupingBy(order -> order.getProduct()));

        System.out.println("Заказы, сгруппированные по продуктам: ");
        groupedOrders.forEach((product, orders) -> {
            System.out.println("Продукт: " + product);
            orders.forEach(order ->
                    System.out.println(" Заказ: " + order.getProduct() + " - " + order.getCost()));
            System.out.println();
        });

        //3.

        Map<String, Double> totalCostPerProduct = allOrders.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.groupingBy(
                        order -> order.getProduct(),
                        Collectors.summingDouble(order -> order.getCost())
                ));

        System.out.println("Общая стоимость заказанных продуктов: " + totalCostPerProduct);

        //4.

        List<Map.Entry<String, Double>> sortedProductsByCost = totalCostPerProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .toList();

        System.out.println("Спиок продуктов, упорядоченный по стоимости: " + sortedProductsByCost);

        //5.

        List<Map.Entry<String, Double>> topThreeExpensiveProducts = sortedProductsByCost.stream()
                .limit(3)
                .toList();

        System.out.println("Три самых дорогих продукта и их общая стоимость:" + topThreeExpensiveProducts);
    }
}