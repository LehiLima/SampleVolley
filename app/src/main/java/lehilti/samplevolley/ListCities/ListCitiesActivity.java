package lehilti.samplevolley.ListCities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lehilti.samplevolley.R;
import lehilti.samplevolley.repositories.remote.City;

public class ListCitiesActivity extends AppCompatActivity {

    @BindView(R.id.list_resultado) RecyclerView mRecyclerView;

    ListCitiesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lis_cities);

        ButterKnife.bind(this);

        // Recebe a Lista Filtrada
        ArrayList<City> cities = new ArrayList<City>();
        cities = (ArrayList<City>) getIntent().getSerializableExtra("list_cidade");

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListCitiesAdapter(cities,this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
