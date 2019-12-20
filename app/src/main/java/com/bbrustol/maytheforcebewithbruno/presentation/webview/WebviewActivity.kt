package com.bbrustol.maytheforcebewithbruno.presentation.webview

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bbrustol.maytheforcebewithbruno.R
import com.bbrustol.maytheforcebewithbruno.utils.Constants
import kotlinx.android.synthetic.main.activity_webview.*

fun webviewActivity() = WebviewActivity()

class WebviewActivity : AppCompatActivity() {

    fun getLaunchingIntent(context: Context?, url: String): Intent {
        val extras = Bundle()
        extras.putString(Constants.ARGUMENT_WEBVIEW_URL, url)

        val intent = Intent(context, WebviewActivity::class.java)
        intent.putExtras(extras)

        return intent
    }

    //region private methods
    private fun getArguments(argument: String): String {
        return intent.getStringExtra(argument)
    }

    private fun configWebView() {

        webView.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY

        webView.settings.builtInZoomControls = true
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true

        webView.webViewClient = object : WebViewClient() {

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Toast.makeText(this@WebviewActivity, "Error: ${error.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        webView.loadUrl(getArguments(Constants.ARGUMENT_WEBVIEW_URL))
    }

    //endregion

    //region override methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        configWebView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        webView.webViewClient = null
    }
    //endregion
}
