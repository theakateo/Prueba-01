package uta.edu.ec.prueba1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivitySMVS extends AppCompatActivity {

    private EditText etNombresSMVS, etApellidosSMVS, etPrimerNumeroSMVS, etSegundoNumeroSMVS;
    private Button btnSiguienteSMVS, btnCerrarSMVS;

    private final ActivityResultLauncher<Intent> thirdActivityLauncherSMVS = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            resultSMVS -> {
                if (resultSMVS.getResultCode() == RESULT_OK && resultSMVS.getData() != null) {
                    Intent dataSMVS = resultSMVS.getData();
                    etPrimerNumeroSMVS.setText(dataSMVS.getStringExtra("primerNumero"));
                    etSegundoNumeroSMVS.setText(dataSMVS.getStringExtra("segundoNumero"));
                    
                    // Habilitar campos al volver de la tercera pantalla
                    etNombresSMVS.setEnabled(true);
                    etApellidosSMVS.setEnabled(true);
                    btnCerrarSMVS.setEnabled(true);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceStateSMVS) {
        super.onCreate(savedInstanceStateSMVS);
        setContentView(R.layout.activity_second);

        etNombresSMVS = findViewById(R.id.etNombres2LoginSMVS);
        etApellidosSMVS = findViewById(R.id.etApellidos2LoginSMVS);
        etPrimerNumeroSMVS = findViewById(R.id.etPrimerNumero2LoginSMVS);
        etSegundoNumeroSMVS = findViewById(R.id.etSegundoNumero2LoginSMVS);
        btnSiguienteSMVS = findViewById(R.id.btnSiguiente2LoginSMVS);
        btnCerrarSMVS = findViewById(R.id.btnCerrar2LoginSMVS);

        btnSiguienteSMVS.setOnClickListener(vSMVS -> {
            Intent intentSMVS = new Intent(SecondActivitySMVS.this, ThirdActivitySMVS.class);
            intentSMVS.putExtra("nombres", etNombresSMVS.getText().toString());
            intentSMVS.putExtra("apellidos", etApellidosSMVS.getText().toString());
            thirdActivityLauncherSMVS.launch(intentSMVS);
        });

        btnCerrarSMVS.setOnClickListener(vSMVS -> {
            Intent resultIntentSMVS = new Intent();
            resultIntentSMVS.putExtra("nombres", etNombresSMVS.getText().toString());
            resultIntentSMVS.putExtra("apellidos", etApellidosSMVS.getText().toString());
            resultIntentSMVS.putExtra("primerNumero", etPrimerNumeroSMVS.getText().toString());
            resultIntentSMVS.putExtra("segundoNumero", etSegundoNumeroSMVS.getText().toString());
            setResult(RESULT_OK, resultIntentSMVS);
            finish();
        });
    }
}
