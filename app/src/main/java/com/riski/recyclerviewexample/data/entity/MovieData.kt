package com.riski.recyclerviewexample.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieData (
    var id : Int,
    var title : String,
    var sinopsis : String,
    var date : String,
    var poster : Int,
    val budget: Int? = null,
    val revenue: Int? = null
) : Parcelable