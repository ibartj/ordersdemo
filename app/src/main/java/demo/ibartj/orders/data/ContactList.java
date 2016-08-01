package demo.ibartj.orders.data;

import java.io.Serializable;

/**
 * A list of contacts.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class ContactList implements Serializable {
    private Contact[] items;

    public Contact[] getItems() {
        return items;
    }

    public ContactList setItems(Contact[] items) {
        this.items = items;
        return this;
    }
}
