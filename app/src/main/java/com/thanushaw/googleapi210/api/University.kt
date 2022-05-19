package com.thanushaw.googleapi210.api

import com.google.android.gms.maps.model.LatLng

data class University(
    val id:Int,
    val name:String,
    val latlng: LatLng
    )