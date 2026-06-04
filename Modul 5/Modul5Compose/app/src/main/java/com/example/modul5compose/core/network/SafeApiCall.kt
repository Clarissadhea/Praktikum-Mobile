package com.example.modul5compose.core.network

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.Success(apiCall())
    } catch (e: IOException) {
        ApiResult.Error("Tidak ada koneksi internet. Cek jaringan Anda.")
    } catch (e: HttpException) {
        ApiResult.Error("Terjadi kesalahan pada server: ${e.code()}")
    } catch (e: Exception) {
        ApiResult.Error("Terjadi kesalahan tidak terduga: ${e.localizedMessage}")
    }
}