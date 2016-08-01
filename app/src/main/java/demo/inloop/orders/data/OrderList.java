package demo.inloop.orders.data;

/**
 * A list of orders.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class OrderList {
    private Order[] items;

    public Order[] getItems() {
        return items;
    }

    @SuppressWarnings("unused")
    public OrderList setItems(Order[] items) {
        this.items = items;
        return this;
    }
}
