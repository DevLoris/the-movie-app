package com.gmail.eamosse.idbdata.data
 
data class Actor(
    val id:Int,
    val name:String,
    var profile_path:String,
    val popularity: Int,
    val birthday: String,
    val place_of_birth: String,
    val biography: String 
)