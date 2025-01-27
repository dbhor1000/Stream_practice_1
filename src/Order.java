import java.util.List;
import java.util.Objects;

class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(cost, order.cost) == 0 && Objects.equals(product, order.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, cost);
    }
}