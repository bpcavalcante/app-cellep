package br.com.bruno.celleptechcurso


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog

import br.com.bruno.celleptechcurso.R
import br.com.bruno.celleptechcurso.WebActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recuperar o email passado pela intent
        val email = intent.getStringExtra("INTENT_EMAIL")

        // Acessar a referência do arquivo de Shared Preferences
        val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

        // Recuperar os dados gravados no SharedPrefence
        val nome = sharedPrefs.getString("NOME", "chave não encontrada")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "chave não encontrada")
        val genero = sharedPrefs.getString("GENERO", "chave não ecnontrada")

        // Exibindo os dados recuperado nas TextViews
        txtMainNome.text = "$nome $sobrenome"
        txtMainEmail.text = email
        txtMainGenero.text = genero

        // Listener (ouvinte) do botão Sair
        btnMainSair.setOnClickListener {
            // Criando um Alert Dialog
            AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja mesmo sair?")
                .setPositiveButton("Sair") { _, _ ->
                    val mIntent = Intent(this, LoginActivity::class.java)
                    startActivity(mIntent)
                    finishAffinity()
                }
                .setNeutralButton("Não") { _,_ -> }
                .setCancelable(true)
                .show()
        }

        // Listener (ouvinte) do botão Site Cellep
        btnMainSite.setOnClickListener {
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
        }
    }
}