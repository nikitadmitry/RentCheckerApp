package by.slowar.rentchecker.ui.check;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import by.slowar.rentchecker.util.Utils;

/**
 * Created by SlowAR on 20.12.2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class CheckPresenterTest {

    CheckPresenter presenter;

    @Mock
    CheckMvp.View mockView;

    @Mock
    CheckMvp.Router mockRouter;

    @Before
    public void setUp() throws Exception {
        System.out.println("utilslog setUp");
        MockitoAnnotations.initMocks(this);
        presenter = new CheckPresenter(mockRouter);
        presenter.attachView(mockView);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("utilslog tearDown");
    }

    @Test
    public void parametersButtonClicked() {
        System.out.println("utilslog parametersButtonClicked");
    }
}