package com.example.proyecto_aplicacion_medidas_244

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.roundToInt

class ResultadosActivity : AppCompatActivity() {

    // Mapa de datos de entrepierna
    private val entrepierna: Map<Int, Medidas> = mapOf(
        75 to Medidas(R = 5, Sm = 49, St = 5, Tj = 10),
        76 to Medidas(R = 5, Sm = 49, St = 5, Tj = 11),
        77 to Medidas(R = 6, Sm = 50, St = 6, Tj = 11),
        78 to Medidas(R = 6, Sm = 51, St = 6, Tj = 12),
        79 to Medidas(R = 6, Sm = 51, St = 6, Tj = 12),
        80 to Medidas(R = 7, Sm = 52, St = 7, Tj = 12),
        81 to Medidas(R = 7, Sm = 53, St = 7, Tj = 13),
        82 to Medidas(R = 7, Sm = 54, St = 7, Tj = 13),
        83 to Medidas(R = 7, Sm = 54, St = 7, Tj = 14),
        84 to Medidas(R = 8, Sm = 55, St = 8, Tj = 14),
        85 to Medidas(R = 8, Sm = 56, St = 8, Tj = 14),
        86 to Medidas(R = 8, Sm = 57, St = 8, Tj = 14),
        87 to Medidas(R = 9, Sm = 58, St = 9, Tj = 15),
        88 to Medidas(R = 9, Sm = 59, St = 9, Tj = 15),
        89 to Medidas(R = 9, Sm = 60, St = 9, Tj = 15),
        90 to Medidas(R = 9, Sm = 60, St = 9, Tj = 16)
    )

    private lateinit var tvAlturaSillin: TextView
    private lateinit var tvRetrocesoSillin: TextView
    private lateinit var tvAlturaSillinTijaManillar: TextView
    private lateinit var tvLargoCuadro: TextView
    private lateinit var tvTijaManillar: TextView
    private lateinit var tvSillinManillar: TextView
    private lateinit var btnComenzar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        // Inicializar vistas
        tvAlturaSillin = findViewById(R.id.tv_alturaSillin)
        tvRetrocesoSillin = findViewById(R.id.tv_retrocesoSillin)
        tvAlturaSillinTijaManillar = findViewById(R.id.tv_alturaSillinTijaManillar)
        tvLargoCuadro = findViewById(R.id.tv_largoCuadro)
        tvTijaManillar = findViewById(R.id.tv_tijaManillar)
        tvSillinManillar = findViewById(R.id.tv_sillinManillar)
        btnComenzar = findViewById(R.id.btn_comenzar)

        // Recibir datos del Intent
        val medidaEntrepierna = intent.getIntExtra("ENTREPIERNA", 0)
        val medidaTronco = intent.getIntExtra("TRONCO", 0)
        val medidaMuslo = intent.getIntExtra("MUSLO", 0)
        val medidaPierna = intent.getIntExtra("PIERNA", 0)
        val medidaBrazo = intent.getIntExtra("BRAZO", 0)
        val medidaAntebrazo = intent.getIntExtra("ANTEBRAZO", 0)

        // Calcular y mostrar resultados
        calcularResultados(medidaEntrepierna)

        // Botón Comenzar (vuelve a la pantalla principal)
        btnComenzar.setOnClickListener {
            finish() // Cierra esta activity y vuelve a la anterior
        }
    }

    private fun calcularResultados(entrepiernaInt: Int) {
        // Buscar las medidas correspondientes en el mapa
        val medidas = entrepierna[entrepiernaInt]

        if (medidas != null && entrepiernaInt > 0) {
            // Calcular altura del sillín: entrepierna * 0.885
            val alturaSillin = (entrepiernaInt * 0.885).redondear(1)

            // Calcular largo del cuadro: (entrepierna * 0.65) + 2
            val largoCuadro = ((entrepiernaInt * 0.65) + 2).redondear(0)

            // Obtener valores del mapa
            val retrocesoSillin = medidas.R
            val sillinManillar = medidas.Sm
            val alturaSillinTijaManillar = medidas.St
            val tijaManillar = medidas.Tj

            // Mostrar resultados
            tvAlturaSillin.text = "$alturaSillin cm"
            tvRetrocesoSillin.text = "$retrocesoSillin cm"
            tvAlturaSillinTijaManillar.text = "$alturaSillinTijaManillar cm"
            tvLargoCuadro.text = "$largoCuadro cm"
            tvTijaManillar.text = "$tijaManillar cm"
            tvSillinManillar.text = "$sillinManillar cm"
        } else {
            // Si no hay datos válidos, mostrar mensaje
            tvAlturaSillin.text = "N/A"
            tvRetrocesoSillin.text = "N/A"
            tvAlturaSillinTijaManillar.text = "N/A"
            tvLargoCuadro.text = "N/A"
            tvTijaManillar.text = "N/A"
            tvSillinManillar.text = "N/A"
        }
    }

    // Función de extensión para redondear (ya está definida en TomarMedidasActivity,
    // pero la necesitamos aquí también)
    private fun Double.redondear(decimals: Int): Double {
        val factor = 10.0.pow(decimals.toDouble())
        return (this * factor).roundToInt() / factor
    }
}