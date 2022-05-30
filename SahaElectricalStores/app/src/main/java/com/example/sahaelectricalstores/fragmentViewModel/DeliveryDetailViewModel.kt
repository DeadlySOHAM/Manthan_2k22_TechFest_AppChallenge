package com.example.sahaelectricalstores.fragmentViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sahaelectricalstores.fragments.DeliveryDetails
import com.example.sahaelectricalstores.network.Api
import com.example.sahaelectricalstores.network.DataEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class DeliveryDetailViewModel : ViewModel() {

    private val _name = MutableLiveData<String>("error")
    val name : LiveData<String> get() = _name

    private val _address = MutableLiveData<String>("error")
    val address : LiveData<String> get() = _address

    private val _phoneNumber = MutableLiveData<String>("error")
    val phoneNumber : LiveData<String> get() = _phoneNumber

    private val _landmark = MutableLiveData<String>("error")
    val landmark : LiveData<String> get() = _landmark

    private val _pincode = MutableLiveData<String>("error")
    val pincode : LiveData<String> get() = _pincode


    fun setAddress(x:String){
        _address.value = x
    }

    fun setName(x:String){
        _name.value = x
    }

    fun setPhoneNumber(x:String){
        _phoneNumber.value = x
    }

    fun setLandmark(x:String){
        _landmark.value=x
    }

    fun setPincode(x:String){
        _pincode.value=x
    }

    fun isOk():Boolean{
        return when("error"){
            _name.value -> false
            _address.value -> false
            _phoneNumber.value -> false
            _landmark.value -> false
            _pincode.value -> false
            else -> true
        }
    }


    fun placeOrderApi(x: DataEntity, alertBox: MaterialAlertDialogBuilder,parent:DeliveryDetails){
        var status = false
        alertBox.setTitle("Placing Order")
            .setMessage("Your Order is being Placed\nPlease Wait")
            .setCancelable(false)
        viewModelScope.launch{
            val showing = alertBox.show()
            try {
                Log.e("launching","dsjfkl")
                val x = Api.retrofitService.placeOrder(x)
                status = x.status
                showing.dismiss()
                if (status)
                    alertBox.setTitle("Thank You")
                        .setMessage("Your Order is Placed\nYou will recieve the order within 5 working days")
                        .setCancelable(false)
                        .setPositiveButton("Ok"){_,_->parent.goHome()}
                        .show()
                else
                    alertBox.setTitle("Something Went Wrong")
                        .setMessage("Your Order is not Placed\nContact Us : +91 9073129824")
                        .setCancelable(false)
                        .setPositiveButton("Ok"){_,_->parent.goHome()}
            }catch(e:Exception){
                showing.dismiss()
                alertBox.setTitle("Something Went Wrong")
                    .setMessage("Your Order is not Placed\n\nError : ${e.toString()}\n\nContact Us : +91 9073129824")
                    .setCancelable(false)
                    .setPositiveButton("Ok"){_,_->parent.goHome()}
                    .show()
            }
        }
    }
}