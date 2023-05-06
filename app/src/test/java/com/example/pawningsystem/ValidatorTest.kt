package com.example.pawningsystem


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)

class ValidatorTest{

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

}