package com.example.proyecto_aplicacion_medidas_244

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PiernaActivity : AppCompatActivity() {

    // Referencias a los elementos de la interfaz (TextView, SeekBar y Button)
    private lateinit var textValorMedida: TextView
    private lateinit var seekbarMedida: SeekBar
    private lateinit var btnGuardar: Button

    // Valores mínimo y máximo permitidos para la medida de la pierna (en centímetros)
    private val MEDIDA_MIN = 30
    private val MEDIDA_MAX = 80

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se asocia esta Activity con su layout correspondiente (activity_pierna.xml)
        setContentView(R.layout.activity_pierna)

        // ============================================================
        // INICIALIZACIÓN DE VISTAS
        // Conectamos las variables con los componentes definidos en el XML
        // ============================================================
        textValorMedida = findViewById(R.id.text_valor_medida)
        seekbarMedida = findViewById(R.id.seekbar_medida)
        btnGuardar = findViewById(R.id.btn_guardar)

        // ============================================================
        // CONFIGURACIÓN DEL SEEK BAR
        // ============================================================
        // El SeekBar va de 0 a (MEDIDA_MAX - MEDIDA_MIN)
        // Se suma MEDIDA_MIN para obtener el valor real
        seekbarMedida.max = MEDIDA_MAX - MEDIDA_MIN
        seekbarMedida.progress = 15 // Valor inicial (45 cm = 30 + 15)

        // Mostrar valor inicial en el TextView
        actualizarTextoMedida(seekbarMedida.progress + MEDIDA_MIN)

        // ============================================================
        // LISTENER DEL SEEK BAR
        // Detecta cuando el usuario cambia el valor del deslizador
        // ============================================================
        seekbarMedida.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            // Cada vez que se cambia la posición del SeekBar
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                actualizarTextoMedida(progress + MEDIDA_MIN) // Actualiza el texto con la medida actual
            }

            // Métodos requeridos por la interfaz, sin implementación necesaria
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // ============================================================
        // BOTÓN GUARDAR
        // Envía el valor seleccionado a la Activity que llamó a esta
        // ============================================================
        btnGuardar.setOnClickListener {
            val medidaFinal = seekbarMedida.progress + MEDIDA_MIN // Valor final de la pierna
            val intent = Intent() // Intent vacío para devolver datos
            intent.putExtra("PIERNA", medidaFinal) // Añade la medida como extra
            setResult(RESULT_OK, intent) // Devolver resultado a la actividad anterior
            finish() // Cierra esta Activity y vuelve atrás
        }
    }

    // ============================================================
    // MÉTODO AUXILIAR
    // Actualiza el texto del TextView con la medida actual en centímetros
    // ============================================================
    private fun actualizarTextoMedida(medida: Int) {
        textValorMedida.text = "$medida cm"
    }
}
