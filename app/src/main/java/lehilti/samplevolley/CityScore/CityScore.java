package lehilti.samplevolley.CityScore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lehilti.samplevolley.R;
import lehilti.samplevolley.repositories.remote.CityRepository;

public class CityScore extends AppCompatActivity implements CityScoreContract{

    private CityScorePresenter presenter;

    @BindView(R.id.txtPoint)
    TextView mPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_score);

        ButterKnife.bind(this);

        String cidade = getIntent().getStringExtra("cidade");

        presenter = new CityScorePresenter(this,new CityRepository(this),this);
        presenter.LoadPoints(cidade);

    }

    @Override
    public void displayPointCities(String point) {
        mPoint.setText(point);
    }

    @Override
    public void displayNoPointCities() {
        mPoint.setText("Não foram encontrados pontos para esta cidade");
    }
}
