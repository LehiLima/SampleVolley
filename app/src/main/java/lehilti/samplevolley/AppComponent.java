package lehilti.samplevolley;

import javax.inject.Singleton;

import dagger.Component;
import lehilti.samplevolley.Main.MainActivity;
import lehilti.samplevolley.repositories.remote.CityRepository;

/**
 * Created by Lehi on 22/04/2017.
 */

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {

   void inject(MainActivity mainActivity);

}
