package com.ita.myapp.classes.data.model.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import com.ita.myapp.classes.data.model.ServiceModel
import com.ita.myapp.classes.data.model.network.RetrofitClient

class ServiceViewModel : ViewModel() {

    private val api = RetrofitClient.api

    fun getServices(onResult: (Response<List<ServiceModel>>) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.getService()
                onResult(response)
            } catch (exception: Exception) {
                exception.printStackTrace()
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
