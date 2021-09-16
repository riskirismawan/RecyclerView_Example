package com.riski.recyclerviewexample.data

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
    val revenue: Int? = null,
    val userScore: Double? = null,
    val original_languange: String?= null
) : Parcelable