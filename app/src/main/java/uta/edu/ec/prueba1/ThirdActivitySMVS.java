package uta.edu.ec.prueba1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivitySMVS extends AppCompatActivity {

    private EditText etNombresSMVS, etApellidosSMVS, etPrimerNumeroSMVS, etSegundoNumeroSMVS;
    private Button btnCerrarSMVS;

    @Override
    protected void onCreate(Bundle savedInstanceStateSMVS) {
        super.onCreate(savedInstanceStateSMVS);
        setContentView(R.layout.activity_third);

        etNombresSMVS = findViewById(R.id.etNombres3LoginSMVS);
        etApellidosSMVS = findViewById(R.id.etApellidos3LoginSMVS);
        etPrimerNumeroSMVS = findViewById(R.id.etPrimerNumero3LoginSMVS);
        etSegundoNumeroSMVS = findViewById(R.id.etSegundoNumero3LoginSMVS);
        btnCerrarSMVS = findViewById(R.id.btnCerrar3LoginSMVS);

        Intent intentSMVS = getIntent();
        if (intentSMVS != null) {
            etNombresSMVS.setText(intentSMVS.getStringExtra("nombres"));
            etApellidosSMVS.setText(intentSMVS.getStringExtra("apellidos"));
        }

        btnCerrarSMVS.setOnClickListener(vSMVS -> {
            if (validarCamposEstrictamenteSMVS()) {
                Intent resultIntentSMVS = new Intent();
                resultIntentSMVS.putExtra("primerNumero", etPrimerNumeroSMVS.getText().toString().trim());
                resultIntentSMVS.putExtra("segundoNumero", etSegundoNumeroSMVS.getText().toString().trim());
                setResult(RESULT_OK, resultIntentSMVS);
                finish();
            }
        });
    }

    private boolean validarCamposEstrictamenteSMVS() {
        String pStr = etPrimerNumeroSMVS.getText().toString().trim();
        String sStr = etSegundoNumeroSMVS.getText().toString().trim();

        // Validar vacíos (específico para cada campo para mayor claridad)
        if (pStr.isEmpty()) {
            etPrimerNumeroSMVS.setError("Campo requerido");
            Toast.makeText(this, "ERROR: El primer número no puede estar vacío", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sStr.isEmpty()) {
            etSegundoNumeroSMVS.setError("Campo requerido");
            Toast.makeText(this, "ERROR: El segundo número no puede estar vacío", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int n1 = Integer.parseInt(pStr);
            int n2 = Integer.parseInt(sStr);

            // Validar ceros o negativos
            if (n1 <= 0) {
                etPrimerNumeroSMVS.setError("Debe ser mayor a 0");
                Toast.makeText(this, "ERROR: El primer número debe ser mayor a cero", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (n2 <= 0) {
                etSegundoNumeroSMVS.setError("Debe ser mayor a 0");
                Toast.makeText(this, "ERROR: El segundo número debe ser mayor a cero", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "ERROR: Ingrese números enteros válidos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (validarCamposEstrictamenteSMVS()) {
            Intent resultIntentSMVS = new Intent();
            resultIntentSMVS.putExtra("primerNumero", etPrimerNumeroSMVS.getText().toString().trim());
            resultIntentSMVS.putExtra("segundoNumero", etSegundoNumeroSMVS.getText().toString().trim());
            setResult(RESULT_OK, resultIntentSMVS);
            super.onBackPressed();
        } else {
            // No llamar a super.onBackPressed() para evitar que la actividad se cierre
            Toast.makeText(this, "ERROR: Complete los datos correctamente antes de salir (Números > 0)", Toast.LENGTH_LONG).show();
        }
    }
}
