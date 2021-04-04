package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @GET("todos")
    Call<List<Todo>> getTodos();

    @GET("todos/{id}")
    Call<ObjectResponse<Todo>> getTodo(@Path("id") int id);


}
