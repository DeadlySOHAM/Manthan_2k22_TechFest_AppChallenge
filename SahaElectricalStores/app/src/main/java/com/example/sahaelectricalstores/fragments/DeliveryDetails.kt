package com.example.sahaelectricalstores.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sahaelectricalstores.R
import com.example.sahaelectricalstores.dataSource.RangeOfDeliveryByPincode
import com.example.sahaelectricalstores.databinding.FragmentDeleviryDetailsBinding
import com.example.sahaelectricalstores.fragmentViewModel.DeliveryDetailViewModel
import com.example.sahaelectricalstores.fragmentViewModel.OrderViewModel
import com.example.sahaelectricalstores.network.DataEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class DeliveryDetails : Fragment() {

    private val sharedViewModel : OrderViewModel by activityViewModels()
    private val deliveryDetailsViewModel : DeliveryDetailViewModel by viewModels()
    private var binding : FragmentDeleviryDetailsBinding?= null
    private val deliveryRange = RangeOfDeliveryByPincode.data

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_deleviry_details,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventHandlers()
        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragmentDeliveryDetail = this@DeliveryDetails
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun eventHandlers(){
        binding?.nameInput?.doOnTextChanged{text ,_ ,_ ,_ -> nameEventHandler(text.toString()) }
        binding?.adressInput?.doOnTextChanged{text,_,_,_->  addressEventHandler(text.toString()) }
        binding?.phoneNumberInput?.doOnTextChanged{text,_,_,_->  phoneEventHandler(text.toString()) }
        binding?.landmarkInput?.doOnTextChanged{text,_,_,_->  landmarkEventHandler(text.toString()) }
        binding?.pincodeInput?.doOnTextChanged{text,_,_,_->  pincodeEventHandler(text.toString()) }
    }

/**
 *      Event Handlers
 */
    private fun nameEventHandler(x : String){
        if(isNameValid(x))   binding?.nameInput?.error = "This Will be used\nin the Bill\nand to identify You."
        deliveryDetailsViewModel.setName(x)
    }

    private fun addressEventHandler(x : String){
        if(x.length<10)    binding?.adressInput?.error = "Make Sure to give right address ."
        deliveryDetailsViewModel.setAddress(x)
    }

    private fun phoneEventHandler(x:String){
        if(!isPhoneNumberValid(x)){
            binding?.phoneNumberInputLayout?.isErrorEnabled = true
            binding?.phoneNumberInputLayout?.error = "Wrong Number"
            deliveryDetailsViewModel.setPhoneNumber("error")
            return
        }
        binding?.phoneNumberInputLayout?.isErrorEnabled = false
        deliveryDetailsViewModel.setPhoneNumber(x)
    }

    private fun landmarkEventHandler(x:String){
        if(x.length<5) {
            binding?.landmarkInputLayout?.isErrorEnabled = true
            binding?.landmarkInputLayout?.error="Wrong landmark could\nDelay Delivery"
        }
        else        binding?.landmarkInputLayout?.isErrorEnabled = false
        deliveryDetailsViewModel.setLandmark(x)
    }

    private fun pincodeEventHandler(x:String){
        if (x.length>7) {
            binding?.pincodeInputLayout?.isErrorEnabled = true
            binding?.pincodeInputLayout?.error = "Out Of Range"
            deliveryDetailsViewModel.setPincode("error")
        }
        else if(x!="") if(isPincodeInRange(x.toInt())) {
                binding?.pincodeInputLayout?.isErrorEnabled = true
                binding?.pincodeInputLayout?.error = "Out Of Range"
                deliveryDetailsViewModel.setPincode("error")
                return
            }
        else    binding?.pincodeInputLayout?.isErrorEnabled = false
        deliveryDetailsViewModel.setPincode(x)
    }

    /**  all validation functions     */
    private fun isPincodeInRange(x:Int):Boolean{
        if(deliveryRange.contains(x))    return false
        return true
    }

    private fun isPhoneNumberValid(x: String):Boolean{
        if(x.length!=10) return false
        return true
    }

    private fun isNameValid(x:String):Boolean{
        if(x.length<5) return true
        return false
    }

    fun cancel(){
        sharedViewModel.reset()
        goHome()
    }

    public fun goHome(){
        findNavController().navigate(R.id.action_deleviryDetails_to_product)
    }

    fun goToNext()
    {
        if(!deliveryDetailsViewModel.isOk())
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Detail Error")
                .setMessage("There might be some wrong details is given .\nPlease check and Try again")
                .setCancelable(false)
                .setPositiveButton("ok"){_,_->return@setPositiveButton}
                .show()
        else
            placeOrder()
    }

    private fun placeOrder(){

        /** Product Detail */
        /**
        var message = sharedViewModel.productName.value.plus("\n")
        var product_details = ""
        if(sharedViewModel.productId.value?.equals(4)==true)
            product_details += getString(
                R.string.wire_product_desc,
                sharedViewModel.colorName.value,
                sharedViewModel.watt.value,
                sharedViewModel.company.value
            ) +"\n"+ getString(
                R.string.wire_quantity_value,
                sharedViewModel.length.value,
                sharedViewModel.quantity.value
            )
        else
            product_details += getString(
                R.string.product_desc,
                sharedViewModel.watt.value,
                sharedViewModel.company.value
            ) +"\n"+"Quantity : "+ getString(
                R.string.quantity_value,
                sharedViewModel.quantity.value
            )
        message += product_details
        message += "\n\nDelivery Detail :\n"
        message += "Name : " + deliveryDetailsViewModel.name.value.plus("\n")
        message += "Phone Number : " + deliveryDetailsViewModel.phoneNumber.value.plus("\n")
        message += "Address : " + deliveryDetailsViewModel.address.value.plus("\n")
        message += "Kolkata : " + deliveryDetailsViewModel.pincode.value.plus("\n")
        message += "Landmark : " + deliveryDetailsViewModel.landmark.value.plus("\n")
        message += "\n"
        message += "This mail is just for testing the app and no such order is required by any consumer . No product should be delivered or no charges should apply ."

        val subject = "Order Id : "+ SimpleDateFormat("ddMMyyyy-HH.mm.ss", Locale.getDefault()).format(Calendar.getInstance().time)
*/

        val data = DataEntity(
            SimpleDateFormat("ddMMyyyy-HH.mm.ss", Locale.getDefault()).format(Calendar.getInstance().time) ,
            sharedViewModel.productId.value as Int ,
            sharedViewModel.productName.value as String ,
            sharedViewModel.watt.value as Double ,
            sharedViewModel.company.value as String ,
            sharedViewModel.price.value as Double ,
            sharedViewModel.quantity.value as Int ,
            sharedViewModel.length.value as Double ,
            sharedViewModel.colorName.value as String ,
            deliveryDetailsViewModel.name.value as String ,
            deliveryDetailsViewModel.phoneNumber.value as String ,
            deliveryDetailsViewModel.address.value as String ,
            deliveryDetailsViewModel.pincode.value as String ,
            deliveryDetailsViewModel.landmark.value as String ,
        )
        deliveryDetailsViewModel.placeOrderApi(data, MaterialAlertDialogBuilder(requireContext()),this)
        /** ------------   Starting Mail Intent     -----------

        val x : Array<String> = arrayOf("companymail@email.com")

        val intent = Intent(Intent.ACTION_SEND).apply{
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL,x)
            putExtra(Intent.EXTRA_SUBJECT,subject)
            putExtra(Intent.EXTRA_TEXT,message)
        }

//        Starting Activity
        if(activity?.packageManager?.resolveActivity(intent,0)!=null)    startActivity(intent)
        */
    }
}