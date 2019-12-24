package by.slowar.rentchecker.ui.map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import by.slowar.rentchecker.data.model.Item;

/**
 * Created by SlowAR on 20.12.2019.
 */

public class MapPresenterTest {

    private MapMvp.Presenter presenter;

    @Mock
    MapMvp.Router mockRouter;

    @Mock
    MapMvp.View mockView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MapPresenter(mockRouter);
        presenter.attachView(mockView);
    }

    @Test
    public void itemClicked() {
        Item item = ArgumentMatchers.any(Item.class);
        presenter.itemClicked(item);
        Mockito.verify(mockRouter).showItemInfo(item);
    }
}