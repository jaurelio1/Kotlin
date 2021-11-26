package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    /*A palavra-chave lateinit é nova.
    Ela é uma promessa de que seu código inicializará a variável
    antes de usá-la. Se você não fizer isso, o app falhará.
    */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Essa linha inicializa o objeto binding que você usará para
        acessar as Views no layout activity_main.xml.*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        /*Defina a visualização de conteúdo da atividade.
        Em vez de transmitir o ID de recurso do layout,
        R.layout.activity_main, esse código especifica a raiz
        da hierarquia de visualizações no app, binding.root.*/
        setContentView(binding.root)

        /*Agora, quando você precisar de uma referência a uma View
        no app, poderá acessá-la no objeto binding, em vez de chamar
        o método findViewById(). */

        val calculateButton = binding.calculateButton
        calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip(){
        /*Lembre-se de que é possível acessar o elemento de IU
        usando o objeto binding e fazendo referência ao elemento
        da IU com base no nome do ID de recurso em letras concatenadas.*/
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null){
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}