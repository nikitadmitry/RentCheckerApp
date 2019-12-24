package by.slowar.rentchecker.ui.check.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Set;
import java.util.TreeSet;

import by.slowar.rentchecker.data.items.RentItemsHelper;
import by.slowar.rentchecker.data.model.Item;

import static by.slowar.rentchecker.data.items.RentItemsHelper.OnNewItemListener;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by SlowAR on 20.12.2019.
 */

public class CheckServicePresenterTest {

    private CheckServiceMvp.Presenter presenter;

    @Mock
    RentItemsHelper mockRentItemsHelper;

    @Mock
    CheckServiceMvp.View mockView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CheckServicePresenter(mockRentItemsHelper);
        presenter.attachView(mockView);
    }

    @Test
    public void onServiceReady() {
        presenter.onServiceReady();
        Mockito.verify(mockRentItemsHelper).subscribeOnNewItems((OnNewItemListener) presenter);
    }

    @Test
    public void onItemsRequest() {
        Set<Item> fakeItems = getFakeItemsSet();
        Mockito.when(presenter.onItemsRequest()).thenReturn(fakeItems);
        Set<Item> items = presenter.onItemsRequest();
        Set<Item> items2 = Mockito.verify(mockRentItemsHelper).getAllItems();
    }

    private Set<Item> getFakeItemsSet() {
        Set<Item> fakeItems = new TreeSet<>();
        fakeItems.add(Mockito.mock(Item.class));
        fakeItems.add(Mockito.mock(Item.class));
        fakeItems.add(Mockito.mock(Item.class));
        return fakeItems;
    }

    @Test
    public void onParametersChanged() {
        presenter.onParametersChanged();
        Mockito.verify(mockRentItemsHelper).updateDataToCurrentParameters();
    }

    @Test
    public void onDestroy() {
        presenter.onDestroy();
        Mockito.verify(mockRentItemsHelper).dispose();
    }

    @Test
    public void onNewItemsSendWithoutNotify() {
        boolean isJustAdded = false;
        assertThat(presenter, instanceOf(OnNewItemListener.class));
        OnNewItemListener presenterListener = (OnNewItemListener) presenter;
        Set<Item> fakeItemsSet = getFakeItemsSet();
        presenterListener.onNewItems(fakeItemsSet, isJustAdded);

        Mockito.verify(mockView).sendNewItems(fakeItemsSet);
        Mockito.verifyNoMoreInteractions(mockView);
    }

    @Test
    public void onNewItemsSendNotify() {
        boolean isJustAdded = true;
        assertThat(presenter, instanceOf(OnNewItemListener.class));
        OnNewItemListener presenterListener = (OnNewItemListener) presenter;
        Set<Item> fakeItemsSet = getFakeItemsSet();
        presenterListener.onNewItems(fakeItemsSet, isJustAdded);

        Mockito.verify(mockView).sendNewItems(fakeItemsSet);
        Mockito.verify(mockView).notifyNewItem();
    }
}