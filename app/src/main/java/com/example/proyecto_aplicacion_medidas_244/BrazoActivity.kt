package com.example.proyecto_aplicacion_medidas_244

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Clase que representa la pantalla donde el usuario ajusta la medida del brazo
class BrazoActivity : AppCompatActivity() {

    // Declaración de los elementos de la interfaz (vistas)
    private lateinit var textValorMedida: TextView   // Muestra el valor actual en cm
    private lateinit var seekbarMedida: SeekBar      // Barra deslizante para seleccionar la medida
    private lateinit var btnGuardar: Button          // Botón para guardar la medida

    // Constantes que definen el rango de la medida (mínimo y máximo)
    private val MEDIDA_MIN = 20
    private val MEDIDA_MAX = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brazo) // Carga el diseño XML de esta pantalla

        // Asociamos los elementos de la interfaz con sus IDs del layout XML
        textValorMedida = findViewById(R.id.text_valor_medida)
        seekbarMedida = findViewById(R.id.seekbar_medida)
        btnGuardar = findViewById(R.id.btn_guardar)

        // Configuramos el rango de la SeekBar:
        // Como SeekBar empieza desde 0, ajustamos su máximo restando el mínimo
        seekbarMedida.max = MEDIDA_MAX - MEDIDA_MIN

        // Valor inicial de la barra (por defecto representa 35 cm)
        seekbarMedida.progress = 15 // porque 15 + 20 (mínimo) = 35

        // Actualizamos el texto para mostrar el valor inicial (35 cm)
        actualizarTextoMedida(seekbarMedida.progress + MEDIDA_MIN)

        // Listener para detectar los cambios en la SeekBar
        seekbarMedida.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            // Se ejecuta cada vez que el usuario mueve la barra
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Se actualiza el texto con el nuevo valor calculado
                actualizarTextoMedida(progress + MEDIDA_MIN)
            }

            // Métodos obligatorios del listener (no se usan en este caso)
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Acción al presionar el botón "Guardar"
        btnGuardar.setOnClickListener {
            // Calculamos la medida final (sumamos el mínimo)
            val medidaFinal = seekbarMedida.progress + MEDIDA_MIN

            // Creamos un intent vacío para devolver el resultado
            val intent = Intent()

            // Añadimos la medida como "extra" al intent con la clave "BRAZO"
            intent.putExtra("BRAZO", medidaFinal)

            // Devolvemos el resultado a la actividad que llamó a esta
            setResult(RESULT_OK, intent)

            // Cerramos la actividad actual y regresamos a la anterior
            finish()
        }
    }

    // Función auxiliar que actualiza el texto del TextView con el valor actual
    private fun actualizarTextoMedida(medida: Int) {
        textValorMedida.text = "$medida cm"
    }
}
