package br.com.senac.gildastore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ForgotPWActivity extends AppCompatActivity {

    Button buttonvai;
    EditText telUsuario,cpfUsuario;
    String contatando, cpfUsu;
    String URL = "https://gildastore.com/API/teste/forgotpw.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpw);

        buttonvai = findViewById(R.id.buttonvai);
        telUsuario = findViewById(R.id.edittextTel);
        cpfUsuario = findViewById(R.id.edittextCPF);

        getSupportActionBar().setTitle("Esqueci minha senha");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonvai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
        });



    }

    private void Submit() {

        contatando = telUsuario.getText().toString();
        cpfUsu = cpfUsuario.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("1")){
                    Toast.makeText(getApplicationContext(),"Email enviado com sucesso, por favor cheque sua caixa de email.",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Os dados estão incorretos.",Toast.LENGTH_LONG).show();
                }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ForgotPWActivity.this,"Por favor cheque sua conexão.", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("telUsuario",contatando);
                params.put("cpfUsuario",cpfUsu);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
