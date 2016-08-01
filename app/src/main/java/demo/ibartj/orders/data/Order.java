package demo.inloop.orders.data;

import java.io.Serializable;

/**
 * Single order data.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class Order implements Serializable {
    private String name;
    private int count;

    public String getName() {
        return name;
    }

    public Order setName(String name) {
        this.name = name;
        return this;
    }

    public int getCount() {
        return count;
    }

    @SuppressWarnings("unused")
    public Order setCount(int count) {
        this.count = count;
        return this;
    }
}
