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
}