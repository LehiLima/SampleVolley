package lehilti.samplevolley.repositories.remote;

import android.content.Context;

import java.util.List;

/**
 * Created by Lehi on 18/04/2017.
 */

public interface CityDataSource {


    void getCities(ListCityServerCallBack serverCallBack);

    void getCityPoint(String cidade, PointsCityServerCallBack serverCallBack);

    interface ListCityServerCallBack {
        void onSuccess(List<City> result);

        void onNoSuccess();
    }

    interface PointsCityServerCallBack {
        void onSuccess(String result);

        void onNoSuccess();
    }
}
