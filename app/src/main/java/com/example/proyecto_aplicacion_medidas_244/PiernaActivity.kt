package com.example.proyecto_aplicacion_medidas_244

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PiernaActivity : AppCompatActivity() {

    private lateinit var textValorMedida: TextView
    private lateinit var seekbarMedida: SeekBar
    private lateinit var btnGuardar: Button

    private val MEDIDA_MIN = 30
    private val MEDIDA_MAX = 80

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pierna)

        textValorMedida = findViewById(R.id.text_valor_medida)
        seekbarMedida = findViewById(R.id.seekbar_medida)
        btnGuardar = findViewById(R.id.btn_guardar)

        seekbarMedida.max = MEDIDA_MAX - MEDIDA_MIN
        seekbarMedida.progress = 15 // 45 cm por defecto
        actualizarTextoMedida(seekbarMedida.progress + MEDIDA_MIN)

        seekbarMedida.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                actualizarTextoMedida(progress + MEDIDA_MIN)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btnGuardar.setOnClickListener {
            val medidaFinal = seekbarMedida.progress + MEDIDA_MIN
            val intent = Intent()
            intent.putExtra("PIERNA", medidaFinal)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun actualizarTextoMedida(medida: Int) {
        textValorMedida.text = "$medida cm"
    }
}