package com.jbgcomposer.imccalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isGone
import com.jbgcomposer.imccalculator.databinding.ActivityMainBinding
import java.math.RoundingMode
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.apply {

            btnCalcular.setOnClickListener {

                var peso: Double? = null
                var altura: Double? = null
                var resultadoImc: Double? = null

                if(edtPeso.text.isEmpty()) {
                    edtPeso.error = "Digite seu peso!"
                } else {
                    peso = edtPeso.text.toString().toDouble()
                }

                if(edtAltura.text.isEmpty()) {
                    edtAltura.error = "Digite sua altura!"
                } else {
                    altura = edtAltura.text.toString().toDouble()
                }

                if(peso != null || altura != null) {
                    resultadoImc = peso?.div(altura!! * altura)
                }

                if(resultadoImc == null) {
                    tvResultado.text = "Você não preencheu os dados corretamente"
                } else {

                    tvResultado.text = "Seu IMC é ${resultadoImc?.toBigDecimal()?.setScale(2, RoundingMode.FLOOR)}"
                }

                closeKeyboard()
            }

            btnLimpar.setOnClickListener {
                edtPeso.text.clear()
                edtAltura.text.clear()
                tvResultado.text = "Resultado"
                closeKeyboard()
            }
        }
    }

    private fun closeKeyboard() {
        val view: View? = currentFocus
        view?.let {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
