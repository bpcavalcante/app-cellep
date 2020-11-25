package br.com.bruno.celleptechcurso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        // Habilitando a execução do código JavaScript no WebView
        wbvWeb.settings.javaScriptEnabled = true

        // Carregar uma página Web (URL) no WebView
        wbvWeb.loadUrl("http://br.cellep.com/estacaohack")

        // Definindo o WebView como cliente Web padrão
        wbvWeb.webViewClient = WebViewClient()
    }
}