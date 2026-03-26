package uta.edu.ec.prueba1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivityLoginSMVS extends AppCompatActivity {

    private EditText etNombresLoginSMVS, etApellidosLoginSMVS, etPrimerNumeroLoginSMVS, etSegundoNumeroLoginSMVS;
    private Button btnSiguienteLoginSMVS, btnCerrarLoginSMVS;

    private final ActivityResultLauncher<Intent> thirdActivityLauncherLoginSMVS = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            resultLoginSMVS -> {
                if (resultLoginSMVS.getResultCode() == RESULT_OK && resultLoginSMVS.getData() != null) {
                    Intent dataLoginSMVS = resultLoginSMVS.getData();
                    etPrimerNumeroLoginSMVS.setText(dataLoginSMVS.getStringExtra("primerNumero"));
                    etSegundoNumeroLoginSMVS.setText(dataLoginSMVS.getStringExtra("segundoNumero"));
                    
                    // Habilitar campos al volver de la tercera pantalla
                    etNombresLoginSMVS.setEnabled(true);
                    etApellidosLoginSMVS.setEnabled(true);
                    btnCerrarLoginSMVS.setEnabled(true);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceStateLoginSMVS) {
        super.onCreate(savedInstanceStateLoginSMVS);
        setContentView(R.layout.activity_second);

        etNombresLoginSMVS = findViewById(R.id.etNombres2LoginSMVS);
        etApellidosLoginSMVS = findViewById(R.id.etApellidos2LoginSMVS);
        etPrimerNumeroLoginSMVS = findViewById(R.id.etPrimerNumero2LoginSMVS);
        etSegundoNumeroLoginSMVS = findViewById(R.id.etSegundoNumero2LoginSMVS);
        btnSiguienteLoginSMVS = findViewById(R.id.btnSiguiente2LoginSMVS);
        btnCerrarLoginSMVS = findViewById(R.id.btnCerrar2LoginSMVS);

        btnSiguienteLoginSMVS.setOnClickListener(vLoginSMVS -> {
            Intent intentLoginSMVS = new Intent(SecondActivityLoginSMVS.this, ThirdActivityLoginSMVS.class);
            intentLoginSMVS.putExtra("nombres", etNombresLoginSMVS.getText().toString());
            intentLoginSMVS.putExtra("apellidos", etApellidosLoginSMVS.getText().toString());
            thirdActivityLauncherLoginSMVS.launch(intentLoginSMVS);
        });

        btnCerrarLoginSMVS.setOnClickListener(vLoginSMVS -> {
            if (validarCamposSMVS()) {
                Intent resultIntentLoginSMVS = new Intent();
                resultIntentLoginSMVS.putExtra("nombres", etNombresLoginSMVS.getText().toString().trim());
                resultIntentLoginSMVS.putExtra("apellidos", etApellidosLoginSMVS.getText().toString().trim());
                resultIntentLoginSMVS.putExtra("primerNumero", etPrimerNumeroLoginSMVS.getText().toString().trim());
                resultIntentLoginSMVS.putExtra("segundoNumero", etSegundoNumeroLoginSMVS.getText().toString().trim());
                setResult(RESULT_OK, resultIntentLoginSMVS);
                finish();
            }
        });
    }

    private boolean validarCamposSMVS() {
        String nom = etNombresLoginSMVS.getText().toString().trim();
        String ape = etApellidosLoginSMVS.getText().toString().trim();

        if (nom.isEmpty()) {
            etNombresLoginSMVS.setError("Campo requerido");
            Toast.makeText(this, "Debe ingresar sus nombres", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ape.isEmpty()) {
            etApellidosLoginSMVS.setError("Campo requerido");
            Toast.makeText(this, "Debe ingresar sus apellidos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (btnCerrarLoginSMVS.isEnabled()) {
            if (validarCamposSMVS()) {
                Intent resultIntentLoginSMVS = new Intent();
                resultIntentLoginSMVS.putExtra("nombres", etNombresLoginSMVS.getText().toString().trim());
                resultIntentLoginSMVS.putExtra("apellidos", etApellidosLoginSMVS.getText().toString().trim());
                resultIntentLoginSMVS.putExtra("primerNumero", etPrimerNumeroLoginSMVS.getText().toString().trim());
                resultIntentLoginSMVS.putExtra("segundoNumero", etSegundoNumeroLoginSMVS.getText().toString().trim());
                setResult(RESULT_OK, resultIntentLoginSMVS);
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}
