package ir.dev_community.devcommunity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val j = JSONObject()
        val url = "http://dev-community.ir/api/user/register/"
        val queue = Volley.newRequestQueue(this)

        val responseListener = Response.Listener<JSONObject> { response ->

        }

        val errorListener = Response.ErrorListener { error ->

        }

        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, j, responseListener, errorListener)
        queue.add(jsonRequest)
    }

}
