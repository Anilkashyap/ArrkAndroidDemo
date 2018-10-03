package com.adi.arrk_demo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.adi.arrk_demo.service.model.ApiResponse;
import com.adi.arrk_demo.service.repository.ApiClient;


public class StarWarsViewModel extends AndroidViewModel {
    private LiveData<ApiResponse> starWarResponseLiveData;

    public StarWarsViewModel(@NonNull Application application) {
        super(application);
        starWarResponseLiveData = ApiClient.getInstance().getNetworkData();
    }

    public void callApi(int pageNumber) {
        ApiClient.getInstance().getSarWarsResponse(pageNumber);
    }

    public LiveData<ApiResponse> getObservableProject() {
        return starWarResponseLiveData;
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new StarWarsViewModel(application);
        }
    }

}
