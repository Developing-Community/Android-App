package ir.dev_community.devcommunity

import android.app.AlertDialog
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var pDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    override fun onClick(v: View?) {
        pDialog= ProgressDialog(this)
        pDialog.setMessage(getString(R.string.please_wait))
        pDialog.show()

        val jSonSend = JSONObject()
        val url = "https://dev-community.ir/api/user/register/"
        val queue = Volley.newRequestQueue(this)

        val responseListener = Response.Listener<JSONObject> { response ->
            //TODO: For test:
            showAlert(response.toString())
            pDialog.dismiss()
        }

        val errorListener = Response.ErrorListener { error ->
            //TODO: For test
            showAlert(error.toString())
            pDialog.dismiss()

        }

        if(password_editText.text.toString().equals(password2_editText.text.toString())
                && !username_editText.text.toString().equals(null)
        && !lastName_editText.text.toString().equals(null)
        && !email_editText.text.toString().equals(null)) {

            jSonSend.put("username",username_editText.text.toString())
            jSonSend.put("email",email_editText.text.toString())
            jSonSend.put("password",password_editText.text.toString())
            jSonSend.put("first_name",firstName_editText.text.toString())
            jSonSend.put("last_name",lastName_editText.text.toString())

            val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jSonSend, responseListener, errorListener)
            queue.add(jsonRequest)
        } else{
            pDialog.dismiss()
            Toast.makeText(this , getString(R.string.input_error) , Toast.LENGTH_LONG).show()
        }
    }

    //todo: for test
    private fun showAlert(message:String){
        val alert = AlertDialog.Builder(this)
        alert.setMessage(message)
        alert.show()
    }
}
