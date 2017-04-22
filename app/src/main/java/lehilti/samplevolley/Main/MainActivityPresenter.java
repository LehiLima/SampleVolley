package lehilti.samplevolley.Main;



import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lehilti.samplevolley.ListCities.ListCitiesActivity;
import lehilti.samplevolley.repositories.remote.City;
import lehilti.samplevolley.repositories.remote.CityDataSource;
import lehilti.samplevolley.repositories.remote.CityRepository;

/**
 * Created by Lehi on 18/04/2017.
 */

public class MainActivityPresenter {

    private MainActivityContract view;
    private CityRepository cityRepository;
    private Context context;

    private  List<City> listCities = Collections.EMPTY_LIST;


    public MainActivityPresenter(MainActivityContract view, CityRepository cityRepository,Context context) {
        this.view = view;
        this.cityRepository = cityRepository;
        this.context = context;
    }



    public List<City> getResult() {
        return this.listCities;
    }




    public void LoadCities() {
            cityRepository.getCities(new CityDataSource.ListCityServerCallBack() {
                @Override
                public void onSuccess(List<City> result) {
                    listCities = result;
                }

                @Override
                public void onNoSuccess() {
                    Toast.makeText(context, "Lista de cidades indisponivel", Toast.LENGTH_SHORT).show();
                }
            });
    }

    public void setFiltering(String cidade, String Estado,List<City> cidades) {

        // Faz a busca no arraylist
        String query =  cidade + Estado;
        query = query.toString().toLowerCase();

        final ArrayList<City> filteredList = new ArrayList<>();

        for (int i = 0; i < cidades.size(); i++) {

            String text = cidades.get(i).getCidade() + cidades.get(i).getEstado();
            text = text.toLowerCase();

            if (text.contains(query)) {
                City city = new City(cidades.get(i).getCidade(),cidades.get(i).getEstado());
                filteredList.add(city);
            }
        }

        Intent intent = new Intent(context, ListCitiesActivity.class);
        intent.putExtra("list_cidade",filteredList);
        context.startActivity(intent);

    }


}
