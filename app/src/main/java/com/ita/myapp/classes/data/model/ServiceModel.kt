package com.ita.myapp.classes.data.model

data class ServiceModel(
    var id: Int = 0,
    var name: String = "",
    var username: String = "",
    var password: String = "",
    var description: String = "",
    var imageURL: String? = null
){}
