package com.example.whowroteitretrofit2.remote;
import com.example.whowroteitretrofit2.model.BookInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService{
    @GET("volumes")
    Call<BookInfo> getBook(@Query("q") String query,
                           @Query("maxResults") int maxResults,
                           @Query("printType") String printType);
}
