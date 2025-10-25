package com.example.proyecto_aplicacion_medidas_244

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AntebrazoActivity : AppCompatActivity() {

    // Declaración de variables para los elementos de la interfaz
    private lateinit var textValorMedida: TextView
    private lateinit var seekbarMedida: SeekBar
    private lateinit var btnGuardar: Button

    // Valores mínimos y máximos de la medida (en cm)
    private val MEDIDA_MIN = 15
    private val MEDIDA_MAX = 45

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antebrazo) // Asigna el layout correspondiente a esta actividad

        // Vinculamos las variables con los elementos del layout
        textValorMedida = findViewById(R.id.text_valor_medida)
        seekbarMedida = findViewById(R.id.seekbar_medida)
        btnGuardar = findViewById(R.id.btn_guardar)

        // Configuramos el rango de la barra (SeekBar)
        // La diferencia entre el máximo y el mínimo define la longitud total del rango
        seekbarMedida.max = MEDIDA_MAX - MEDIDA_MIN

        // Valor inicial de la barra (10 unidades sobre el mínimo, o sea, 25 cm)
        seekbarMedida.progress = 10

        // Actualiza el texto con el valor inicial
        actualizarTextoMedida(seekbarMedida.progress + MEDIDA_MIN)

        // Listener que detecta cambios en la posición del SeekBar
        seekbarMedida.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            // Se ejecuta cada vez que el usuario mueve la barra
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Actualiza el texto con el valor actual (sumando el mínimo)
                actualizarTextoMedida(progress + MEDIDA_MIN)
            }

            // No se usa, pero deben estar implementados para cumplir con la interfaz
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Acción del botón "Guardar"
        btnGuardar.setOnClickListener {
            // Calcula el valor final de la medida seleccionada
            val medidaFinal = seekbarMedida.progress + MEDIDA_MIN

            // Crea un intent vacío para devolver el resultado
            val intent = Intent()

            // Agrega el valor medido como dato extra al intent
            intent.putExtra("ANTEBRAZO", medidaFinal)

            // Devuelve el resultado a la actividad anterior
            setResult(RESULT_OK, intent)

            // Cierra esta actividad y vuelve atrás
            finish()
        }
    }

    // Función auxiliar que actualiza el texto del valor de medida mostrado
    private fun actualizarTextoMedida(medida: Int) {
        textValorMedida.text = "$medida cm" // Muestra la medida actual con la unidad "cm"
    }
}
