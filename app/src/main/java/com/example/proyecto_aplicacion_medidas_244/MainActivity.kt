package com.example.proyecto_aplicacion_medidas_244

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita la compatibilidad con "Edge to Edge" (pantalla completa)
        enableEdgeToEdge()

        // Se asocia esta Activity con su layout correspondiente (activity_main.xml)
        setContentView(R.layout.activity_main)

        // ============================================================
        // Ajuste de padding automático para las barras del sistema
        // Esto evita que el contenido quede tapado por la barra de estado
        // o la barra de navegación.
        // ============================================================
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Obtenemos los insets de las barras del sistema (top, bottom, left, right)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Aplicamos padding al layout principal según los insets
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            // Devolvemos los insets para que puedan seguir siendo aplicados si es necesario
            insets
        }

        // ============================================================
        // BOTÓN "Tomar medida"
        // ============================================================
        val botonTomarMedidas = findViewById<Button>(R.id.boton_tomar_medidas)
        botonTomarMedidas.setOnClickListener {
            // Inicia la Activity para tomar medidas
            val intent = Intent(this, TomarMedidasActivity::class.java)
            startActivity(intent)
        }
    }
}
