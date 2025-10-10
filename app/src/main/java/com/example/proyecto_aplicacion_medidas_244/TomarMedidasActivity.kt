package com.example.proyecto_aplicacion_medidas_244

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow
import kotlin.math.roundToInt

// Clase para almacenar las medidas (CORREGIDA)
data class Medidas(val R: Int, val Sm: Int, val St: Int, val Tj: Int)

// Extension para redondear a un numero especifico de decimales
fun Float.redondear(decimals: Int): Float {
    val factor = 10.0.pow(decimals.toDouble()).toFloat()
    return (this * factor).roundToInt() / factor
}

class TomarMedidasActivity : AppCompatActivity() {

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

    // Declaración de variables para los elementos de la UI
    private lateinit var btnEntrepierna: ImageButton
    private lateinit var btnTronco: ImageButton
    private lateinit var btnMuslo: ImageButton
    private lateinit var btnPierna: ImageButton
    private lateinit var btnBrazo: ImageButton
    private lateinit var btnAntebrazo: ImageButton
    private lateinit var textEntrepierna: TextView
    private lateinit var textTronco: TextView
    private lateinit var textMuslo: TextView
    private lateinit var textPierna: TextView
    private lateinit var textBrazo: TextView
    private lateinit var textAntebrazo: TextView
    private lateinit var btnVolver: Button
    private lateinit var btnBorrar: Button
    private lateinit var btnVerResultados: Button

    // Variables para almacenar las medidas ingresadas
    private var medidaEntrepierna: Int = 0
    private var medidaTronco: Int = 0
    private var medidaMuslo: Int = 0
    private var medidaPierna: Int = 0
    private var medidaBrazo: Int = 0
    private var medidaAntebrazo: Int = 0

    // Launchers para recibir resultados de las actividades de medición
    private val entrepiernaResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            medidaEntrepierna = result.data?.getIntExtra("ENTREPIERNA", 0) ?: 0
            textEntrepierna.text = "$medidaEntrepierna cm"
        }
    }

    private val troncoResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            medidaTronco = result.data?.getIntExtra("TRONCO", 0) ?: 0
            textTronco.text = "$medidaTronco cm"
        }
    }

    private val musloResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            medidaMuslo = result.data?.getIntExtra("MUSLO", 0) ?: 0
            textMuslo.text = "$medidaMuslo cm"
        }
    }

    private val piernaResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            medidaPierna = result.data?.getIntExtra("PIERNA", 0) ?: 0
            textPierna.text = "$medidaPierna cm"
        }
    }

    private val brazoResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            medidaBrazo = result.data?.getIntExtra("BRAZO", 0) ?: 0
            textBrazo.text = "$medidaBrazo cm"
        }
    }

    private val antebrazoResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            medidaAntebrazo = result.data?.getIntExtra("ANTEBRAZO", 0) ?: 0
            textAntebrazo.text = "$medidaAntebrazo cm"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomar_medidas)

        // Inicializar los elementos de la UI
        btnEntrepierna = findViewById(R.id.btn_entrepierna)
        btnTronco = findViewById(R.id.btn_tronco)
        btnMuslo = findViewById(R.id.btn_muslo)
        btnPierna = findViewById(R.id.btn_pierna)
        btnBrazo = findViewById(R.id.btn_brazo)
        btnAntebrazo = findViewById(R.id.btn_antebrazo)
        textEntrepierna = findViewById(R.id.text_entrepierna)
        textTronco = findViewById(R.id.text_tronco)
        textMuslo = findViewById(R.id.text_muslo)
        textPierna = findViewById(R.id.text_pierna)
        textBrazo = findViewById(R.id.text_brazo)
        textAntebrazo = findViewById(R.id.text_antebrazo)
        btnVolver = findViewById(R.id.btn_volver)
        btnBorrar = findViewById(R.id.btn_Borrar)
        btnVerResultados = findViewById(R.id.btn_ver_resultados)

        // Restaurar medidas guardadas si existen
        if (savedInstanceState != null) {
            medidaEntrepierna = savedInstanceState.getInt("MEDIDA_ENTREPIERNA", 0)
            medidaTronco = savedInstanceState.getInt("MEDIDA_TRONCO", 0)
            medidaMuslo = savedInstanceState.getInt("MEDIDA_MUSLO", 0)
            medidaPierna = savedInstanceState.getInt("MEDIDA_PIERNA", 0)
            medidaBrazo = savedInstanceState.getInt("MEDIDA_BRAZO", 0)
            medidaAntebrazo = savedInstanceState.getInt("MEDIDA_ANTEBRAZO", 0)

            // Mostrar las medidas restauradas
            if (medidaEntrepierna > 0) textEntrepierna.text = "$medidaEntrepierna cm"
            if (medidaTronco > 0) textTronco.text = "$medidaTronco cm"
            if (medidaMuslo > 0) textMuslo.text = "$medidaMuslo cm"
            if (medidaPierna > 0) textPierna.text = "$medidaPierna cm"
            if (medidaBrazo > 0) textBrazo.text = "$medidaBrazo cm"
            if (medidaAntebrazo > 0) textAntebrazo.text = "$medidaAntebrazo cm"
        }

        // Configurar los listeners para los ImageButton
        btnEntrepierna.setOnClickListener {
            val intent = Intent(this, EntrepiernaActivity::class.java)
            entrepiernaResultLauncher.launch(intent)
        }

        btnTronco.setOnClickListener {
            val intent = Intent(this, TroncoActivity::class.java)
            troncoResultLauncher.launch(intent)
        }

        btnMuslo.setOnClickListener {
            val intent = Intent(this, MusloActivity::class.java)
            musloResultLauncher.launch(intent)
        }

        btnPierna.setOnClickListener {
            val intent = Intent(this, PiernaActivity::class.java)
            piernaResultLauncher.launch(intent)
        }

        btnBrazo.setOnClickListener {
            val intent = Intent(this, BrazoActivity::class.java)
            brazoResultLauncher.launch(intent)
        }

        btnAntebrazo.setOnClickListener {
            val intent = Intent(this, AntebrazoActivity::class.java)
            antebrazoResultLauncher.launch(intent)
        }

        // Configurar el listener para el botón Volver
        btnVolver.setOnClickListener {
            finish() // Regresa a la actividad anterior
        }

        // Configurar el listener para el botón Borrar
        btnBorrar.setOnClickListener {
            medidaEntrepierna = 0
            medidaTronco = 0
            medidaMuslo = 0
            medidaPierna = 0
            medidaBrazo = 0
            medidaAntebrazo = 0

            textEntrepierna.text = ""
            textTronco.text = ""
            textMuslo.text = ""
            textPierna.text = ""
            textBrazo.text = ""
            textAntebrazo.text = ""
        }

        // Configurar el listener para el botón Ver Resultados
        btnVerResultados.setOnClickListener {
            val intent = Intent(this, ResultadosActivity::class.java)
            intent.putExtra("ENTREPIERNA", medidaEntrepierna)
            intent.putExtra("TRONCO", medidaTronco)
            intent.putExtra("MUSLO", medidaMuslo)
            intent.putExtra("PIERNA", medidaPierna)
            intent.putExtra("BRAZO", medidaBrazo)
            intent.putExtra("ANTEBRAZO", medidaAntebrazo)
            startActivity(intent)
        }

        // Ajustar los insets para que la UI no se solape con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Guardar las medidas cuando la Activity se destruye
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("MEDIDA_ENTREPIERNA", medidaEntrepierna)
        outState.putInt("MEDIDA_TRONCO", medidaTronco)
        outState.putInt("MEDIDA_MUSLO", medidaMuslo)
        outState.putInt("MEDIDA_PIERNA", medidaPierna)
        outState.putInt("MEDIDA_BRAZO", medidaBrazo)
        outState.putInt("MEDIDA_ANTEBRAZO", medidaAntebrazo)
    }
}