package uta.edu.ec.prueba1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityLoginSMVS extends AppCompatActivity {

    private EditText etNombresLoginSMVS, etApellidosLoginSMVS, etPrimerNumeroLoginSMVS, etSegundoNumeroLoginSMVS, etMultiplicadorLoginSMVS, etPotenciaLoginSMVS, etFactorialLoginSMVS;
    private Button btnSiguienteLoginSMVS, btnMostrarLoginSMVS;

    private String nombresStrLoginSMVS, apellidosStrLoginSMVS, primerNumeroStrLoginSMVS, segundoNumeroStrLoginSMVS;

    private final ActivityResultLauncher<Intent> secondActivityLauncherLoginSMVS = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            resultLoginSMVS -> {
                if (resultLoginSMVS.getResultCode() == RESULT_OK && resultLoginSMVS.getData() != null) {
                    Intent dataLoginSMVS = resultLoginSMVS.getData();
                    nombresStrLoginSMVS = dataLoginSMVS.getStringExtra("nombres");
                    apellidosStrLoginSMVS = dataLoginSMVS.getStringExtra("apellidos");
                    primerNumeroStrLoginSMVS = dataLoginSMVS.getStringExtra("primerNumero");
                    segundoNumeroStrLoginSMVS = dataLoginSMVS.getStringExtra("segundoNumero");
                    
                    etNombresLoginSMVS.setText(nombresStrLoginSMVS);
                    etApellidosLoginSMVS.setText(apellidosStrLoginSMVS);
                    etPrimerNumeroLoginSMVS.setText(primerNumeroStrLoginSMVS);
                    etSegundoNumeroLoginSMVS.setText(segundoNumeroStrLoginSMVS);
                    
                    btnMostrarLoginSMVS.setEnabled(true);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceStateLoginSMVS) {
        super.onCreate(savedInstanceStateLoginSMVS);
        setContentView(R.layout.activity_main);

        etNombresLoginSMVS = findViewById(R.id.etNombres1LoginSMVS);
        etApellidosLoginSMVS = findViewById(R.id.etApellidos1LoginSMVS);
        etPrimerNumeroLoginSMVS = findViewById(R.id.etPrimerNumero1LoginSMVS);
        etSegundoNumeroLoginSMVS = findViewById(R.id.etSegundoNumero1LoginSMVS);
        etMultiplicadorLoginSMVS = findViewById(R.id.etMultiplicador1LoginSMVS);
        etPotenciaLoginSMVS = findViewById(R.id.etPotencia1LoginSMVS);
        etFactorialLoginSMVS = findViewById(R.id.etFactorial1LoginSMVS);
        btnSiguienteLoginSMVS = findViewById(R.id.btnSiguiente1LoginSMVS);
        btnMostrarLoginSMVS = findViewById(R.id.btnMostrar1LoginSMVS);

        btnSiguienteLoginSMVS.setOnClickListener(vLoginSMVS -> {
            Intent intentLoginSMVS = new Intent(MainActivityLoginSMVS.this, SecondActivityLoginSMVS.class);
            secondActivityLauncherLoginSMVS.launch(intentLoginSMVS);
        });

        btnMostrarLoginSMVS.setOnClickListener(vLoginSMVS -> {
            realizarCalculosLoginSMVS();
        });
    }

    private void realizarCalculosLoginSMVS() {
        if (primerNumeroStrLoginSMVS != null && segundoNumeroStrLoginSMVS != null) {
            try {
                int n1 = Integer.parseInt(primerNumeroStrLoginSMVS);
                int n2 = Integer.parseInt(segundoNumeroStrLoginSMVS);

                // Multiplicación mediante sumas sucesivas
                int multi = 0;
                for (int i = 0; i < n2; i++) {
                    multi += n1;
                }
                etMultiplicadorLoginSMVS.setText(String.valueOf(multi));

                // Potencia (n1 ^ n2) usando solo sumas sucesivas
                long pot = 1;
                for (int i = 0; i < n2; i++) {
                    long tempPot = 0;
                    for (int j = 0; j < n1; j++) {
                        tempPot += pot;
                    }
                    pot = tempPot;
                }
                etPotenciaLoginSMVS.setText(String.valueOf(pot));

                // Factorial (n1!) usando solo sumas sucesivas
                long fact = 1;
                for (int i = 1; i <= n1; i++) {
                    long tempFact = 0;
                    for (int j = 0; j < i; j++) {
                        tempFact += fact;
                    }
                    fact = tempFact;
                }
                etFactorialLoginSMVS.setText(String.valueOf(fact));

            } catch (Exception e) {
                // Error de parseo
            }
        }
    }
}
