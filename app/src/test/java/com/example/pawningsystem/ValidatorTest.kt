package com.example.pawningsystem


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)

class ValidatorTest{

    //Pawning function testing
    @Test
    fun whenPawningInputIsValid(){
        val name = "Teshan"
        val email = "teshan@gmail.com"
        val teleNo = 774838439
        val bank = "HSBC, 485868594"
        val address = "123, Dehiwala"
        val item = "Gold"
        val estimatedValue = 200000

        val result = Validator.validatePawningInput(name, email, teleNo, bank, address, item, estimatedValue)

        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenPawningInputIsInvalidZero(){
        val name = ""
        val email = ""
        val teleNo = 0
        val bank = "HSBC, 485868594"
        val address = "123, Dehiwala"
        val item = "Gold"
        val estimatedValue = 0

        val result = Validator.validatePawningInput(name, email, teleNo, bank, address, item, estimatedValue)

        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenPawningInputIsInvalidNegative(){
        val name = "Teshan"
        val email = "teshan@gmail.com"
        val teleNo = -3736
        val bank = ""
        val address = ""
        val item = ""
        val estimatedValue = -20000

        val result = Validator.validatePawningInput(name, email, teleNo, bank, address, item, estimatedValue)

        assertThat(result).isEqualTo(false)
    }

    //User Validation
    @Test
    fun whenUserInputValid(){
        val fullname = "Bihesha"
        val email = "dilshan@gmail.com"
        val nic = "562410325V"
        val address = "145, Kottawa"
        val phone = 714562147
        val username = "bihesha"
        val pass = "dil123"

        val result = Validator.validateUserInput(fullname,email,nic,address,phone,username,pass)

        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenUserInputIsInvalidZero(){
        val fullname = ""
        val email = ""
        val nic = ""
        val address = "145, Kottawa"
        val phone = 0
        val username = ""
        val pass = ""

        val result = Validator.validateUserInput(fullname,email,nic,address,phone,username,pass)

        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenUserInputIsInvalidNegative(){
        val fullname = "Bihesha"
        val email = "dilshan@gmail.com"
        val nic = ""
        val address = "145, Kottawa"
        val phone = -1245
        val username = ""
        val pass = ""

        val result = Validator.validateUserInput(fullname,email,nic,address,phone,username,pass)

        assertThat(result).isEqualTo(false)
    }

    //user inquiry testing
    @Test
    fun whenInquiryInputValid(){
        val fullname = "Kalindu"
        val email = "kalindu@gmail.com"
        val telephone = 71134568
        val subject = "A payment error"
        val message = "I've tried to complete the payment"

        val result = Validator.validateInquiryInput(fullname,email,telephone,subject,message)

        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInquiryInputIsInvalidZero(){
        val fullname = ""
        val email = ""
        val telephone = 0
        val subject = ""
        val message = ""

        val result = Validator.validateInquiryInput(fullname,email,telephone,subject,message)

        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenInquiryInquiryInputIsInvalidNegative(){
        val fullname = ""
        val email = ""
        val telephone = -5678
        val subject = ""
        val message = ""

        val result = Validator.validateInquiryInput(fullname,email,telephone,subject,message)

        assertThat(result).isEqualTo(false)
    }
    @Test
    fun whenCashReturnInputIsValid(){
        val csNIC = "2020202V"
        val fullName = "tesha"
        val email = "tesh@gmail.com"
        val  phone= 288121212
        val  cashAmount= 2333
        val dateToCollect = "2022/2/21"

        val result = Validator.validateCashReturn(csNIC, fullName, email, phone, cashAmount, dateToCollect)

        assertThat(result).isEqualTo(true)
    }
    @Test
    fun whenCashReturnInputIsInValidZero(){
        val csNIC = ""
        val fullName = "tesha"
        val email = "tesh@gmail.com"
        val  phone= 0
        val  cashAmount= 0
        val dateToCollect = ""

        val result = Validator.validateCashReturn(csNIC, fullName, email, phone, cashAmount, dateToCollect)

        assertThat(result).isEqualTo(false)
    }
    @Test
    fun whenCashReturnInputIsInValidNegative(){
        val csNIC = ""
        val fullName = "tesha"
        val email = "tesh@gmail.com"
        val  phone= -2
        val  cashAmount= -44
        val dateToCollect = ""

        val result = Validator.validateCashReturn(csNIC, fullName, email, phone, cashAmount, dateToCollect)

        assertThat(result).isEqualTo(false)
    }


}