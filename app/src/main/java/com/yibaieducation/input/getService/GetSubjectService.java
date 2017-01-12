package com.yibaieducation.input.getService;

import com.yibaieducation.input.bean.ItemConfigBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * YiBaiInputExams
 * Create   2017/1/11 10:51;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public interface GetSubjectService {

    @GET("sub")
    Call<Object> getTopMovie();
}
