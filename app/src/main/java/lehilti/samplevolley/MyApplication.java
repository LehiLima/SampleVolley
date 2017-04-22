package lehilti.samplevolley;

/**
 * Created by Lehi on 22/04/2017.
 */

public class MyApplication extends android.app.Application {

    private AppComponent Component;

    @Override
    public void onCreate() {
        super.onCreate();

        Component = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }

    public AppComponent getComponent() {
        return Component;
    }
}
