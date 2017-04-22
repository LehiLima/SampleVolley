package lehilti.samplevolley;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lehilti.samplevolley.Main.MainActivity;
import lehilti.samplevolley.Main.MainActivityPresenter;
import lehilti.samplevolley.repositories.remote.CityRepository;

/**
 * Created by Lehi on 22/04/2017.
 */
@Module
public class AppModule {
    Application mApplication;

    private CityRepository cityRepository;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public CityRepository providesCityRepositoty(){
        return new CityRepository(mApplication);
    }
}
