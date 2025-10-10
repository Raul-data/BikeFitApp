package com.example.proyecto_aplicacion_medidas_244

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EntrepiernaActivity : AppCompatActivity() {

    private lateinit var textValorMedida: TextView
    private lateinit var seekbarMedida: SeekBar
    private lateinit var btnGuardar: Button

    // Rango de medidas: 60 a 100 cm
    private val MEDIDA_MIN = 60
    private val MEDIDA_MAX = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrepierna)

        // Inicializar vistas
        textValorMedida = findViewById(R.id.text_valor_medida)
        seekbarMedida = findViewById(R.id.seekbar_medida)
        btnGuardar = findViewById(R.id.btn_guardar)

        // Configurar el SeekBar
        // El SeekBar va de 0 a (MEDIDA_MAX - MEDIDA_MIN)
        seekbarMedida.max = MEDIDA_MAX - MEDIDA_MIN
        seekbarMedida.progress = 18 // Valor inicial: 78 cm (60 + 18 = 78)

        // Mostrar valor inicial
        actualizarTextoMedida(seekbarMedida.progress + MEDIDA_MIN)

        // Listener para el SeekBar
        seekbarMedida.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val medidaActual = progress + MEDIDA_MIN
                actualizarTextoMedida(medidaActual)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // No necesitamos hacer nada aquí
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // No necesitamos hacer nada aquí
            }
        })

        // Listener para el botón Guardar
        btnGuardar.setOnClickListener {
            val medidaFinal = seekbarMedida.progress + MEDIDA_MIN
            val intent = Intent()
            intent.putExtra("ENTREPIERNA", medidaFinal)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun actualizarTextoMedida(medida: Int) {
        textValorMedida.text = "$medida cm"
    }
}