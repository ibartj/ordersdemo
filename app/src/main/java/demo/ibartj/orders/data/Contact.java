package demo.inloop.orders.data;

import java.io.Serializable;

/**
 * Single contact data.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class Contact implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String pictureUrl;

    public String getId() {
        return id;
    }

    public Contact setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Contact setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    @SuppressWarnings("unused")
    public Contact setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }
}
