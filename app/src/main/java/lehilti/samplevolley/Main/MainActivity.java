package lehilti.samplevolley.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lehilti.samplevolley.MyApplication;
import lehilti.samplevolley.R;
import lehilti.samplevolley.repositories.remote.CityRepository;

public class MainActivity extends AppCompatActivity implements MainActivityContract{

    @Inject CityRepository cityRepository;

    @BindView(R.id.btnBuscar) Button btnBuscar;
    @BindView(R.id.busca_cidade) EditText mCidade;
    @BindView(R.id.busca_estado) EditText mEstado;

    private MainActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getComponent().inject(this);

//        // Instancia o presenter
        presenter = new MainActivityPresenter(this,cityRepository,this);
        presenter.LoadCities();

        btnBuscar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
             presenter.setFiltering(mCidade.getText().toString(),
                                                          mEstado.getText().toString(),
                                                          presenter.getResult());
            }
        });

    }


    @Override
    public void displayNoCities() {
        Toast.makeText(this, "Não foram encontradas cidades", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.LoadCities();
    }
}
