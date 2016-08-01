package demo.ibartj.orders.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import demo.ibartj.orders.AppContext;
import demo.ibartj.orders.R;
import demo.ibartj.orders.data.Order;

/**
 * A RecyclerViewAdapter for the order list.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class OrdersRecyclerViewAdapter extends RecyclerView.Adapter<OrdersRecyclerViewAdapter.ViewHolder> {

    private final List<Order> mValues;

    public OrdersRecyclerViewAdapter(List<Order> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(
                mValues.get(position).getName()
        );
        holder.mCountView.setText(
                AppContext.getContext().getString(
                        R.string.order_pieces,
                        mValues.get(position).getCount()
                )
        );
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mCountView;
        public Order mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.order_name);
            mCountView = (TextView) view.findViewById(R.id.order_count);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
