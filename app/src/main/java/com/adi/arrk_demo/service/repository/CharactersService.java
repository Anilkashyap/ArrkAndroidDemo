package com.adi.arrk_demo.service.repository;

import com.adi.arrk_demo.service.model.ApiResponse;
import com.adi.arrk_demo.utility.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharactersService {
    String URL = Constants.API_URL;

    @GET("people")
    Call<ApiResponse> getCharacters(@Query("page") int pageNumber);
}
