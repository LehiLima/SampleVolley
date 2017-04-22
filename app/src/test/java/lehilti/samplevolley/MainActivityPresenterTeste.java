package lehilti.samplevolley;

import android.content.Context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import lehilti.samplevolley.repositories.remote.City;
import lehilti.samplevolley.Main.MainActivityContract;
import lehilti.samplevolley.Main.MainActivityPresenter;
import lehilti.samplevolley.repositories.remote.CityDataSource;
import lehilti.samplevolley.repositories.remote.CityRepository;

import static java.util.Collections.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainActivityPresenterTeste {

    @Rule
    public MockitoRule mockitoRule  = MockitoJUnit.rule();

    @Mock
    CityRepository cityRepository;

    @Mock
    MainActivityContract view;

    @Mock
    Context context;

    @Captor
    private ArgumentCaptor<CityDataSource.ListCityServerCallBack> CallbackArgumentCaptor;

    @Captor
    private ArgumentCaptor<CityDataSource.PointsCityServerCallBack> CallbackPointArgumentCaptor;


    private MainActivityPresenter presenter;

    private final List<City> MANY_CITIES = Arrays.asList(new City(), new City(), new City());

    @Before
    public void setUp() {
        presenter = new MainActivityPresenter(view, cityRepository, context);
    }

    @Test
    public void shouldPassCitiesToView(){

        presenter.LoadCities();

        verify(cityRepository, times(1)).getCities(CallbackArgumentCaptor.capture());

        assertThat(presenter.getResult().isEmpty(), is(true));

        // Call back implemented in View
        CallbackArgumentCaptor.getValue().onSuccess(MANY_CITIES);

        assertThat(presenter.getResult(), is(equalTo(MANY_CITIES)));

    }

    @Test
    public void shouldPassNoCitiesToView(){
        presenter.LoadCities();

        verify(cityRepository, times(1)).getCities(CallbackArgumentCaptor.capture());
        // Let's call the callback. ArgumentCaptor.capture() works like a matcher.

        // Some assertion about the state before the callback is called
        assertThat(presenter.getResult().isEmpty(), is(true));

        CallbackArgumentCaptor.getValue().onNoSuccess();

        assertThat(presenter.getResult(), is(equalTo(EMPTY_LIST)));
    }

//    @Test
//    public void shouldPassCitiesPointsToView(){
//
//        presenter.LoadPoints();
//
//        verify(cityRepository, times(1)).getCityPoint(CallbackPointArgumentCaptor.capture());
//
//        assertThat(presenter.getResultPoints().isEmpty(), is(true));
//
//        CallbackPointArgumentCaptor.getValue().onSuccess("10000");
//
//        assertThat(presenter.getResultPoints(), is(equalTo("10000")));
//
//    }
//
//    @Test
//    public void shouldPassNoCitiesPointsToView(){
//
//        presenter.LoadPoints();
//
//        verify(cityRepository, times(1)).getCityPoint(CallbackPointArgumentCaptor.capture());
//
//        assertThat(presenter.getResultPoints().isEmpty(), is(true));
//
//        CallbackPointArgumentCaptor.getValue().onSuccess("");
//
//        assertThat(presenter.getResultPoints(), is(equalTo("")));
//
//    }
//    @Test
//    public void shouldPassNoCitiesToView(){
//        // given
//        when(cityRepository.getCities()).thenReturn(EMPTY_LIST);
//
//        // when
//        presenter.LoadCities();
//
//        // then
//        verify(view).displayNoCities();
//    }
}
