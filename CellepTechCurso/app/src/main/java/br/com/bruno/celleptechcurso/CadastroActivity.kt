package br.com.bruno.celleptechcurso


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Criar uma lista de opções para o Spinner
        val listaGenero = arrayListOf("Selecione o gênero", "Feminino", "Masculino", "Não-binário")

        // Criar uma adaptador para o Spinner: Layout
        //
        val generoAdapter = ArrayAdapter(
            this,                                           // Contexto
            android.R.layout.simple_spinner_dropdown_item,  // Layout
            listaGenero                                     // Dados
        )

        // Plugar o adaptador ao Spinner da tela
        spnCadastroGenero.adapter = generoAdapter

        // Executando o clique do botão cadastrar
        btnCadastroCadastrar.setOnClickListener {
            // Capturando os dados digitados
            val nome = edtCadastroNome.text.toString().trim()
            val sobrenome = edtCadastroSobrenome.text.toString().trim()
            val email = edtCadastroEmail.text.toString().trim().toLowerCase()
            val senha = edtCadastroSenha.text.toString().trim()
            val genero = spnCadastroGenero.selectedItem.toString()

            // Validação dos Campos
            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty()
                || genero == listaGenero[0]){
                // Apresentando mensagem de erro ao usuário
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }else{
                // Todos os campos foram preenchidos

                // Criando ou acessando o arquivo Shared Preferences
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                // Editar o arquivo
                val editPrefs = sharedPrefs.edit()

                // Apresentando uma mensagem de sucesso ao usuário
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show()

                // Preparando os dados a serem salvos
                editPrefs.putString("NOME", nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("GENERO", genero)

                // salvar os dados no arquivo shared preferences
                editPrefs.apply()

                // Abrir a MainActivity
                val mIntent = Intent(this, MainActivity::class.java)
                // Passando informações através da intent
                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)

                // Tirar todas as telas do empilhamento
                finishAffinity()
            }
        }

    }
}