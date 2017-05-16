package com.app.task;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BaseService {
  @GET("users")
  Call<ResponseBody> userDetails();
}