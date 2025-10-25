package com.example.proyecto_aplicacion_medidas_244

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EntrepiernaActivity : AppCompatActivity() {

    // Referencias a los elementos de la interfaz (TextView, SeekBar y Button)
    private lateinit var textValorMedida: TextView
    private lateinit var seekbarMedida: SeekBar
    private lateinit var btnGuardar: Button

    // Valores mínimo y máximo permitidos para la medida (en centímetros)
    private val MEDIDA_MIN = 60
    private val MEDIDA_MAX = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se asocia esta Activity con su layout correspondiente (activity_entrepierna.xml)
        setContentView(R.layout.activity_entrepierna)

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
        // El SeekBar permite seleccionar valores desde 0 hasta (MEDIDA_MAX - MEDIDA_MIN)
        // Luego, a ese valor se le suma MEDIDA_MIN para obtener el valor real.
        seekbarMedida.max = MEDIDA_MAX - MEDIDA_MIN
        seekbarMedida.progress = 18 // Posición inicial (equivale a 78 cm = 60 + 18)

        // Mostrar el valor inicial en pantalla
        actualizarTextoMedida(seekbarMedida.progress + MEDIDA_MIN)

        // ============================================================
        // LISTENER DEL SEEK BAR
        // Detecta cuando el usuario cambia el valor del deslizador
        // ============================================================
        seekbarMedida.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            // Método que se ejecuta cada vez que cambia el valor del SeekBar
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val medidaActual = progress + MEDIDA_MIN
                actualizarTextoMedida(medidaActual) // Actualiza el texto con la nueva medida
            }

            // No se usan estos dos métodos, pero deben declararse por la interfaz
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Llamado al iniciar el toque sobre el deslizador
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Llamado al soltar el deslizador
            }
        })

        // ============================================================
        // BOTÓN GUARDAR
        // Envía el valor seleccionado a la Activity que llamó a esta
        // ============================================================
        btnGuardar.setOnClickListener {
            val medidaFinal = seekbarMedida.progress + MEDIDA_MIN // Valor final elegido
            val intent = Intent() // Se crea un intent vacío para devolver datos
            intent.putExtra("ENTREPIERNA", medidaFinal) // Se añade el valor como extra
            setResult(RESULT_OK, intent) // Se devuelve el resultado a la actividad anterior
            finish() // Cierra la actividad actual y vuelve atrás
        }
    }

    // ============================================================
    // MÉTODO AUXILIAR
    // Actualiza el texto del TextView para mostrar la medida actual
    // ============================================================
    private fun actualizarTextoMedida(medida: Int) {
        textValorMedida.text = "$medida cm"
    }
}
