package com.example.tubemate.repository

import android.util.Log
import com.example.tubemate.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ) : Resource<T> {
        return withContext(Dispatchers.IO){
            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable : Throwable){
                Log.d("ApiCheck", throwable.message.toString())
                when(throwable){
                    is HttpException ->{
                        Resource.Failure(false,
                            throwable.code(),
                            throwable.message(),
                            throwable.response()?.errorBody())
                    }
                    is IOException ->{
                        Resource.Failure(true,
                            null,
                            "No Internet please check your internet connection and try again",
                            null
                        )
                    }

                    else ->{
                        Resource.Failure(true, null, throwable.localizedMessage, null)
                    }
                }
            }
        }
    }
}