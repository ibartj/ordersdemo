package demo.inloop.orders;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import demo.inloop.orders.utils.GsonRequest;

/**
 * Application context singleton.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class AppContext {
    private static AppContext instance = null;
    public static final String LOG_TAG = "OrdersDemo";
    protected Context context = null;
    private RequestQueue volleyRequestQueue;

    private AppContext(Context context) {
        this.context = context;
    }

    public static void initialize(Context context) {
        if (instance == null) {
            instance = new AppContext(context);
        }
    }

    public static synchronized AppContext getInstance() {
        return instance;
    }

    public static Context getContext() {
        return getInstance().context;
    }

    private RequestQueue getVolleyRequestQueue() {
        if (volleyRequestQueue == null) {
            Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            volleyRequestQueue = new RequestQueue(cache, network);
            volleyRequestQueue.start();
        }
        return volleyRequestQueue;
    }

    /**
     * Adds a JsonObjectRequest to the VolleyRequestQueue.
     * @param request
     */
    public void addToVolleyRequestQueue(JsonObjectRequest request) {
        getVolleyRequestQueue().add(request);
    }

    /**
     * Adds a GsonRequest to the VolleyRequestQueue.
     * @param request
     */
    public void addToVolleyRequestQueue(GsonRequest request) {
        getVolleyRequestQueue().add(request);
    }
}
