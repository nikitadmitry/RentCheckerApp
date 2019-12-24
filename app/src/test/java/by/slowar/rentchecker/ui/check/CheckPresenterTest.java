package by.slowar.rentchecker.ui.check;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by SlowAR on 20.12.2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class CheckPresenterTest {

    private CheckMvp.Presenter presenter;

    @Mock
    CheckMvp.View mockView;

    @Mock
    CheckMvp.Router mockRouter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CheckPresenter(mockRouter);
        presenter.attachView(mockView);
    }

    @Test
    public void parametersButtonClicked() {
        presenter.parametersButtonClicked();
        Mockito.verify(mockRouter).showParametersScreen();
    }
}