package com.example.proyecto_aplicacion_medidas_244

// Imports necesarios para la actividad y sus componentes de UI
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Clase de la actividad para medir el tronco
class TroncoActivity : AppCompatActivity() {

    // Variables para los elementos de la UI
    private lateinit var textValorMedida: TextView
    private lateinit var seekbarMedida: SeekBar
    private lateinit var btnGuardar: Button

    // Constantes para definir el rango de la medida
    private val MEDIDA_MIN = 60
    private val MEDIDA_MAX = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tronco)

        // Inicializar los elementos de la UI
        textValorMedida = findViewById(R.id.text_valor_medida)
        seekbarMedida = findViewById(R.id.seekbar_medida)
        btnGuardar = findViewById(R.id.btn_guardar)

        // Configurar rango y valor inicial del SeekBar
        seekbarMedida.max = MEDIDA_MAX - MEDIDA_MIN
        seekbarMedida.progress = 10 // 70 cm por defecto
        actualizarTextoMedida(seekbarMedida.progress + MEDIDA_MIN)

        // Listener para actualizar el TextView al mover la barra
        seekbarMedida.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                actualizarTextoMedida(progress + MEDIDA_MIN)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Listener para el botón guardar: devuelve la medida a la actividad anterior
        btnGuardar.setOnClickListener {
            val medidaFinal = seekbarMedida.progress + MEDIDA_MIN
            val intent = Intent()
            intent.putExtra("TRONCO", medidaFinal)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    // Función auxiliar para mostrar la medida en el TextView
    private fun actualizarTextoMedida(medida: Int) {
        textValorMedida.text = "$medida cm"
    }
}
