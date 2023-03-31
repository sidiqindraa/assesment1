package org.d3if0108.dendagajih

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.util.Log
import org.d3if0108.dendagajih.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

binding.button.setOnClickListener {
    hitung()
}
    }

    private fun hitung(){
        val text = binding.editNominal.text.toString()

        val nominal = text.toDouble()
        val selected = binding.radioGroup.checkedRadioButtonId
        val denda = when (selected){
            R.id.radioButton -> 0.5
            R.id.radioButton2 -> 0.4
            R.id.radioButton3 -> 0.3
            R.id.radioButton4 -> 0.2
            else -> 0.1
        }

        val result = nominal * denda
        val totalgajih = result.toString()
        val totalText = "Rp$totalgajih"
        binding.bmiTextView.text = totalText
    }
}