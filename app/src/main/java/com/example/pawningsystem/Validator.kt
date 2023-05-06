package com.example.pawningsystem

object Validator {

    //validating the create pawning request function
    fun validatePawningInput(
        name: String,
        email: String,
        telephoneNo: Int,
        bankDeets: String,
        pickAdd: String,
        item: String,
        estValue: Int
    ):Boolean{
        return!(name.isEmpty() || email.isEmpty() || telephoneNo <=0 ||
                bankDeets.isEmpty() || pickAdd.isEmpty() || item.isEmpty() || estValue <=0)

    }

    //validating the create user request function

    fun validateUserInput(
        username: String,
        useremail: String,
        usernic: String,
        useraddress: String,
        userphone: Int,
        userusername: String,
        userpass: String,
    ):Boolean{
        return!(username.isEmpty() || useremail.isEmpty() || usernic.isEmpty() || useraddress.isEmpty() ||
                userphone <=0 || userusername.isEmpty() || userpass.isEmpty())
    }

    //validating the inquiry create function
    fun validateInquiryInput(
        iqfullname: String,
        iqemail: String,
        iqtelephone: Int,
        iqsubject: String,
        iqmessage: String,
    ):Boolean{
        return!(iqfullname.isEmpty() || iqemail.isEmpty() || iqtelephone <=0 || iqsubject.isEmpty() || iqmessage.isEmpty())
    }
    fun validateCashReturn(
        csNIC:String,
        fullName:String,
        email:String,
         phone:Int,
         cashAmount:Int,
         dateToCollect:String,
    ):Boolean{
        return!(csNIC.isEmpty() || fullName.isEmpty()||email.isEmpty()|| phone<=0||cashAmount<=0||dateToCollect.isEmpty())

    }
}