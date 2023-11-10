package com.morpion.valorant.util

import android.util.Log
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface RequestSafe {
    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<BaseResponse<T>>
    ): RestResult<T> {
        return try {
            val response = call.invoke()
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                if (responseBody.status == 200) {
                    RestResult.Success(responseBody.data)
                } else {
                    RestResult.Error(IOException("LUTFEN TEKRAR DENEYINIZ"))
                }
            } else {
                val responseError = response.errorBody()
                if (responseError != null) {
                    RestResult.Error(Exception(responseError.toString()))
                } else {
                    RestResult.Error(IOException("Unknown error"))
                }
            }
        } catch (e: IOException) {
            RestResult.Error(IOException("İnternet bağlantınızla alakalı olabilir, lütfen bağlantınızı kontrol edin ve ardından tekrar deneyin.1"))
        } catch (e: UnknownHostException) {
            RestResult.Error(IOException("İnternet bağlantınızla alakalı olabilir, lütfen bağlantınızı kontrol edin ve ardından tekrar deneyin.2"))
        } catch (e: SocketTimeoutException) {
            RestResult.Error(IOException("İnternet bağlantınızla alakalı olabilir, lütfen bağlantınızı kontrol edin ve ardından tekrar deneyin.3"))
        } catch (e: Exception) {
            RestResult.Error(Exception(e))
        }
    }
}
