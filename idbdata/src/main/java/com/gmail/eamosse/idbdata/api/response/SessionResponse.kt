
package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.local.entities.TokenEntity
import com.google.gson.annotations.SerializedName

internal data class SessionResponse(
    @SerializedName("session_id")
    val session_id: String? = null
)
