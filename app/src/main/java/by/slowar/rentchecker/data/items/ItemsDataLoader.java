package by.slowar.rentchecker.data.items;

import java.util.ArrayList;
import java.util.List;

import by.slowar.rentchecker.data.items.loaders.DataLoader;
import by.slowar.rentchecker.data.items.loaders.OnlinerDataLoader;
import by.slowar.rentchecker.data.local.ParametersPreferences;
import by.slowar.rentchecker.util.SchedulersUtil;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by SlowAR on 06.12.2019.
 */

public class ItemsDataLoader {

    private ParametersPreferences parametersPreferences;
    private SchedulersUtil schedulersUtil;
    private CompositeDisposable rentSitesApisDisposable;
    private List<DataLoader> dataLoaderList;

    public ItemsDataLoader(ParametersPreferences parametersPreferences, SchedulersUtil schedulersUtil) {
        this.parametersPreferences = parametersPreferences;
        this.schedulersUtil = schedulersUtil;
        rentSitesApisDisposable = new CompositeDisposable();
        setupDataLoaders();
    }

    public void updateDataToCurrentParameters() {
        setupDataLoaders();
    }

    private void setupDataLoaders() {
        dataLoaderList = new ArrayList<>();
        for (RentItemsHelper.Site site : RentItemsHelper.Site.values()) {
            if (parametersPreferences.getSite(site)) {
                dataLoaderList.add(getDataLoader(site));
            }
        }
    }

    private DataLoader getDataLoader(RentItemsHelper.Site site) {
        switch (site) {
            case Onliner:
                return new OnlinerDataLoader(parametersPreferences, schedulersUtil);
            default:
                throw new IllegalStateException("Site is not supported by application: " + site.name());
        }
    }

    public void loadSitesData(OnSiteDataLoadedListener listener) {
        for (DataLoader dataLoader : dataLoaderList) {
            dataLoader.loadSiteData(listener);
        }
    }

    public void dispose() {
        rentSitesApisDisposable.dispose();
        rentSitesApisDisposable.clear();
    }

    public interface OnSiteDataLoadedListener {

        void onSiteDataLoaded(ItemsPojo itemsPojo, RentItemsHelper.Site site);
    }
}