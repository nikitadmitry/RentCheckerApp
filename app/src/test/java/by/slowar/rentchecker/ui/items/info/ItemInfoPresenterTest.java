package by.slowar.rentchecker.ui.items.info;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by SlowAR on 20.12.2019.
 */

public class ItemInfoPresenterTest {

    private ItemInfoMvp.Presenter presenter;

    @Mock
    ItemInfoMvp.Router mockRouter;

    @Mock
    ItemInfoMvp.View mockView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new ItemInfoPresenter(mockRouter);
        presenter.attachView(mockView);
    }

    @Test
    public void callButtonClicked() {
        String number = "80291234567";
        presenter.callButtonClicked(number);
        Mockito.verify(mockRouter).showNumberDialScreen(number);
    }
}