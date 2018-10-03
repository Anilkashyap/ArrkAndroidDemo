package com.adi.arrk_demo.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.adi.arrk_demo.service.model.ApiResponse;
import com.adi.arrk_demo.utility.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private CharactersService charactersService;
    private static ApiClient projectRepository;

    private ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        charactersService = retrofit.create(CharactersService.class);
    }

    public synchronized static ApiClient getInstance() {
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new ApiClient();
            }
        }
        return projectRepository;
    }

    final MutableLiveData<ApiResponse> apiResponseMutableLiveData = new MutableLiveData<>();

    public LiveData<ApiResponse> getSarWarsResponse(int pageNumber) {

        charactersService.getCharacters(pageNumber).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                apiResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                apiResponseMutableLiveData.setValue(null);
            }
        });
        return apiResponseMutableLiveData;
    }

    public LiveData<ApiResponse> getNetworkData() {
        return apiResponseMutableLiveData;
    }


}
