package demo.inloop.orders.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Arrays;

import demo.inloop.orders.AppContext;
import demo.inloop.orders.R;
import demo.inloop.orders.data.Contact;
import demo.inloop.orders.data.Order;
import demo.inloop.orders.data.OrderList;
import demo.inloop.orders.utils.GsonRequest;
import demo.inloop.orders.utils.Network;

/**
 * Contains a list of orders for given contact.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class OrderListFragment extends Fragment {
    private static final String ARG_CONTACT = "contact";

    private Contact contact;
    private OrdersRecyclerViewAdapter recyclerViewAdapter;

    public OrderListFragment() {
    }

    /**
     * Creates new fragment instance.
     *
     * @param contact
     * @return
     */
    public static OrderListFragment newInstance(Contact contact) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contact = (Contact) getArguments().getSerializable(ARG_CONTACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        if (view instanceof RecyclerView) {
            recyclerViewAdapter = new OrdersRecyclerViewAdapter(new ArrayList<Order>());
            ((RecyclerView) view).setAdapter(recyclerViewAdapter);
            getData((RecyclerView) view);
        }
        return view;
    }

    /**
     * Downloads order data from the server. JSON format:
     * {"items": [{"name": "Notebook","count": 3},...]}
     *
     * @param recyclerView
     */
    private void getData(final RecyclerView recyclerView) {
        if (contact == null) {
            return;
        }
        if (!Network.isConnected()) {
            Toast.makeText(getContext(), R.string.error_network, Toast.LENGTH_SHORT).show();
            return;
        }
        String url = getString(R.string.url_orders, contact.getId());

        GsonRequest<OrderList> jsonObjReq = new GsonRequest<>(
                url,
                OrderList.class,
                null,
                new Response.Listener<OrderList>() {

                    @Override
                    public void onResponse(OrderList response) {
                        recyclerViewAdapter = new OrdersRecyclerViewAdapter(Arrays.asList(response.getItems()));
                        recyclerView.swapAdapter(recyclerViewAdapter, true);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), R.string.error_response, Toast.LENGTH_SHORT).show();
            }
        });

        AppContext.getInstance().addToVolleyRequestQueue(jsonObjReq);
    }
}
