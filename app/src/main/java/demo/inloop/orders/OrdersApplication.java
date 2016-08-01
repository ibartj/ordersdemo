package demo.inloop.orders;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * The application object. Initializes an AppContext and Fresco image loader library.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class OrdersApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
				.penaltyFlashScreen()
				.penaltyLog()
				.build();
		StrictMode.setThreadPolicy(policy);*/
        AppContext.initialize(getApplicationContext());
        Fresco.initialize(this);
    }
}
