package lehilti.samplevolley.CityScore;

import android.content.Context;

import lehilti.samplevolley.Main.MainActivityContract;
import lehilti.samplevolley.repositories.remote.CityDataSource;
import lehilti.samplevolley.repositories.remote.CityRepository;

/**
 * Created by Lehi on 19/04/2017.
 */

public class CityScorePresenter {

    private CityScoreContract view;
    private CityRepository cityRepository;
    private Context context;

    private String points = "";

    public CityScorePresenter(CityScoreContract view, CityRepository cityRepository, Context context) {
        this.view = view;
        this.cityRepository = cityRepository;
        this.context = context;
    }

    public void LoadPoints(String city){
        cityRepository.getCityPoint(city, new CityDataSource.PointsCityServerCallBack() {
            @Override
            public void onSuccess(String result) {
                points = result;
                view.displayPointCities(points);
            }
            @Override
            public void onNoSuccess() {
                view.displayNoPointCities();
            }
        });
    }

    public String getResultPoints() {
        return this.points;
    }
}
