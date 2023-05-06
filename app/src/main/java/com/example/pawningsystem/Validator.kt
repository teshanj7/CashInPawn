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
}