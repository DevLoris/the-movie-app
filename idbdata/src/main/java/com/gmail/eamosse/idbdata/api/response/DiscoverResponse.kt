package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Discover
import com.gmail.eamosse.idbdata.data.Token
import com.google.gson.annotations.SerializedName

internal data class DiscoverResponse(
    @SerializedName("results")
    val results: List<DiscoverItem>
) {
    data class DiscoverItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("poster_path")
        val poster_path: String ?
    )
}


internal fun DiscoverResponse.DiscoverItem.toDiscover() = Discover(
    id = id,
    title = title,
    overview = overview,
    poster_path = poster_path ?: ""
)
