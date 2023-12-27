package com.example.carbrowser

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

@SuppressLint("StaticFieldLeak")
class BackgroundWorkers(var context: Context): AsyncTask<String, String, String>() {

    var result: String = ""

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): String {
        val user: String? = params[0]
        val password: String? = params[1]
        val type: String? = params[2]
        val login_url = "http://192.168.1.100/login.php"

        if(type.equals("login")){

            // Setup connection and type to php web
            val url = URL(login_url)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

            try{
                // Setip connection type and property
                urlConnection.doOutput = true
                urlConnection.doInput = true
                urlConnection.setChunkedStreamingMode(0);

                // 2 way method, require input and output stream
                urlConnection.setRequestMethod("POST")

                // Initialized output stream
                val outBuffered = BufferedWriter(OutputStreamWriter(urlConnection.outputStream, "UTF-8"))

                // Query encoding and writing
                val postData: String = URLEncoder.encode("User","UTF-8") +
                        '=' +
                        URLEncoder.encode(user,"UTF-8") +
                        '&' +
                        URLEncoder.encode("Password","UTF-8") +
                        '=' +
                        URLEncoder.encode(password,"UTF-8")

                outBuffered.write(postData)

                // Load and Close stream
                outBuffered.flush()
                outBuffered.close()

                // Initialized input stream
                val inputStream: InputStream? = urlConnection.inputStream
                val inBuffered = BufferedReader(InputStreamReader(inputStream, "iso-8859-1"))

                // Write result from BufferedReader stream
                var line: String

                while(inBuffered.readLine().also { line = it } != null){
                    result += line
                }

                // Close connection and stream
                inputStream?.close()
                inBuffered.close()
                urlConnection.disconnect()

            }catch (e: MalformedURLException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch(e: Exception) {
                e.printStackTrace()
            }finally {
                urlConnection.disconnect()
                // Issue hosting web root manager to access the MySQL database via php
                // This is a poor coding practice, but an alternative way to trigger login
                if(user?.equals("User")!! && password?.equals("User")!!) {
                    result = "1"
                }
            }
        }
        return result
    }

    @Deprecated("Deprecated in Java")
    protected override fun onPreExecute() {

    }

    @Deprecated("Deprecated in Java")
    protected override fun onPostExecute(result: String?) {
    }

    @Deprecated("Deprecated in Java")
    protected override fun onProgressUpdate(vararg values: String?) {
    }
}