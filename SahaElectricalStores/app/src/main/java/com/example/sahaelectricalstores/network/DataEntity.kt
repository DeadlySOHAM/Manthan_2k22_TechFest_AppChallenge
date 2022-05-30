package com.example.sahaelectricalstores.network

data class DataEntity(
    val order_id : String ,
    val product_id : Int ,
    val product_name : String ,
    val watt : Double ,
    val company : String ,
    val amount : Double ,
    val quantity : Int ,
    val wireLength : Double ,
    val wireColor : String ,
    val customerName :String ,
    val customerPhNumber : String ,
    val customerAddress : String ,
    val customerPinCode : String ,
    val customerLandMark : String ,
)


data class ResponseEntity(
    val status : Boolean
)