package uta.edu.ec.prueba1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivityLoginSMVS extends AppCompatActivity {

    private EditText etNombresLoginSMVS, etApellidosLoginSMVS, etPrimerNumeroLoginSMVS, etSegundoNumeroLoginSMVS;
    private Button btnCerrarLoginSMVS;

    @Override
    protected void onCreate(Bundle savedInstanceStateLoginSMVS) {
        super.onCreate(savedInstanceStateLoginSMVS);
        setContentView(R.layout.activity_third);

        etNombresLoginSMVS = findViewById(R.id.etNombres3LoginSMVS);
        etApellidosLoginSMVS = findViewById(R.id.etApellidos3LoginSMVS);
        etPrimerNumeroLoginSMVS = findViewById(R.id.etPrimerNumero3LoginSMVS);
        etSegundoNumeroLoginSMVS = findViewById(R.id.etSegundoNumero3LoginSMVS);
        btnCerrarLoginSMVS = findViewById(R.id.btnCerrar3LoginSMVS);

        Intent intentLoginSMVS = getIntent();
        if (intentLoginSMVS != null) {
            etNombresLoginSMVS.setText(intentLoginSMVS.getStringExtra("nombres"));
            etApellidosLoginSMVS.setText(intentLoginSMVS.getStringExtra("apellidos"));
            etPrimerNumeroLoginSMVS.setText(intentLoginSMVS.getStringExtra("primerNumero"));
        }

        btnCerrarLoginSMVS.setOnClickListener(vLoginSMVS -> {
            if (validarCamposSMVS()) {
                Intent resultIntentLoginSMVS = new Intent();
                resultIntentLoginSMVS.putExtra("primerNumero", etPrimerNumeroLoginSMVS.getText().toString().trim());
                resultIntentLoginSMVS.putExtra("segundoNumero", etSegundoNumeroLoginSMVS.getText().toString().trim());
                setResult(RESULT_OK, resultIntentLoginSMVS);
                finish();
            }
        });
    }

    private boolean validarCamposSMVS() {
        String pStr = etPrimerNumeroLoginSMVS.getText().toString().trim();
        String sStr = etSegundoNumeroLoginSMVS.getText().toString().trim();

        if (pStr.isEmpty()) {
            etPrimerNumeroLoginSMVS.setError("Campo requerido");
            Toast.makeText(this, "El primer número es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sStr.isEmpty()) {
            etSegundoNumeroLoginSMVS.setError("Campo requerido");
            Toast.makeText(this, "El segundo número es obligatorio", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int n1 = Integer.parseInt(pStr);
            int n2 = Integer.parseInt(sStr);

            if (n1 <= 0) {
                etPrimerNumeroLoginSMVS.setError("Debe ser mayor a 0");
                Toast.makeText(this, "Los números deben ser mayores a cero", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (n2 <= 0) {
                etSegundoNumeroLoginSMVS.setError("Debe ser mayor a 0");
                Toast.makeText(this, "Los números deben ser mayores a cero", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese números válidos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (validarCamposSMVS()) {
            Intent resultIntentLoginSMVS = new Intent();
            resultIntentLoginSMVS.putExtra("primerNumero", etPrimerNumeroLoginSMVS.getText().toString().trim());
            resultIntentLoginSMVS.putExtra("segundoNumero", etSegundoNumeroLoginSMVS.getText().toString().trim());
            setResult(RESULT_OK, resultIntentLoginSMVS);
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Debe completar los campos correctamente (>0)", Toast.LENGTH_LONG).show();
        }
    }
}
