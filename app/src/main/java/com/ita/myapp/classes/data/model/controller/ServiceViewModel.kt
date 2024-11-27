package com.ita.myapp.classes.data.model.controller

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import com.ita.myapp.classes.data.model.ServiceModel
import com.ita.myapp.classes.data.model.database.AppDatabase
import com.ita.myapp.classes.data.model.network.RetrofitClient
import com.ita.myapp.classes.data.model.toServiceEntityList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ServiceViewModel : ViewModel() {

    private val api = RetrofitClient.api

    fun getServices(db: AppDatabase) {
        val serviceDao = db.serviceDao()
        viewModelScope.launch {
            try {
                val response = api.getServices()
                if (response.body()?.count()!! > 0) {
                    val serviceEntities = response.body()?.toServiceEntityList()
                    if (serviceEntities != null) {
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                serviceDao.insertAll(serviceEntities)
                            } catch (exception: Exception) {
                                Log.d("error", exception.toString())
                            }
                        }
                    }
                }
            } catch (exception: Exception) {
                print(exception)
            }
        }
    }


    fun showService(id: Int, onResult: (Response<ServiceModel>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.getService(id)
                onResult(response)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun createService(service: ServiceModel, onResult: (Response<ServiceModel>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.createService(service)
                onResult(response)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun updateService(id: Int, service: ServiceModel, onResult: (Response<ServiceModel>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.updateService(id, service)
                onResult(response)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun deleteService(id: Int, onResult: (Response<ServiceModel>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.deleteService(id)
                onResult(response)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}
