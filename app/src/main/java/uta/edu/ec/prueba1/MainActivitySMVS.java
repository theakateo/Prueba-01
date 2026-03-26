package uta.edu.ec.prueba1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivitySMVS extends AppCompatActivity {

    private EditText etNombresSMVS, etApellidosSMVS, etPrimerNumeroSMVS, etSegundoNumeroSMVS, etMultiplicadorSMVS, etPotenciaSMVS, etFactorialSMVS;
    private Button btnSiguienteSMVS, btnMostrarSMVS;

    private String nombresStrSMVS, apellidosStrSMVS, primerNumeroStrSMVS, segundoNumeroStrSMVS;

    private final ActivityResultLauncher<Intent> secondActivityLauncherSMVS = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            resultSMVS -> {
                if (resultSMVS.getResultCode() == RESULT_OK && resultSMVS.getData() != null) {
                    Intent dataSMVS = resultSMVS.getData();
                    nombresStrSMVS = dataSMVS.getStringExtra("nombres");
                    apellidosStrSMVS = dataSMVS.getStringExtra("apellidos");
                    primerNumeroStrSMVS = dataSMVS.getStringExtra("primerNumero");
                    segundoNumeroStrSMVS = dataSMVS.getStringExtra("segundoNumero");

                    etNombresSMVS.setText(nombresStrSMVS);
                    etApellidosSMVS.setText(apellidosStrSMVS);
                    etPrimerNumeroSMVS.setText(primerNumeroStrSMVS);
                    etSegundoNumeroSMVS.setText(segundoNumeroStrSMVS);

                    btnMostrarSMVS.setEnabled(true);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceStateSMVS) {
        super.onCreate(savedInstanceStateSMVS);
        setContentView(R.layout.activity_main);

        etNombresSMVS = findViewById(R.id.etNombres1LoginSMVS);
        etApellidosSMVS = findViewById(R.id.etApellidos1LoginSMVS);
        etPrimerNumeroSMVS = findViewById(R.id.etPrimerNumero1LoginSMVS);
        etSegundoNumeroSMVS = findViewById(R.id.etSegundoNumero1LoginSMVS);
        etMultiplicadorSMVS = findViewById(R.id.etMultiplicador1LoginSMVS);
        etPotenciaSMVS = findViewById(R.id.etPotencia1LoginSMVS);
        etFactorialSMVS = findViewById(R.id.etFactorial1LoginSMVS);
        btnSiguienteSMVS = findViewById(R.id.btnSiguiente1LoginSMVS);
        btnMostrarSMVS = findViewById(R.id.btnMostrar1LoginSMVS);

        btnSiguienteSMVS.setOnClickListener(vSMVS -> {
            Intent intentSMVS = new Intent(MainActivitySMVS.this, SecondActivitySMVS.class);
            secondActivityLauncherSMVS.launch(intentSMVS);
        });

        btnMostrarSMVS.setOnClickListener(vSMVS -> {
            realizarCalculosSMVS();
        });
    }

    private void realizarCalculosSMVS() {
        if (primerNumeroStrSMVS != null && segundoNumeroStrSMVS != null) {
            try {
                int num1SMVS = Integer.parseInt(primerNumeroStrSMVS);
                int num2SMVS = Integer.parseInt(segundoNumeroStrSMVS);

                // Multiplicación mediante sumas sucesivas
                int multiSMVS = 0;
                for (int i = 0; i < num2SMVS; i++) {
                    multiSMVS += num1SMVS;
                }
                etMultiplicadorSMVS.setText(String.valueOf(multiSMVS));

                // Potencia mediante multiplicaciones sucesivas (num1 ^ num2)
                // Se asume num2 >= 1 por validación previa
                long potSMVS = 1;
                for (int i = 0; i < num2SMVS; i++) {
                    long tempPotSMVS = 0;
                    for (int j = 0; j < num1SMVS; j++) {
                        tempPotSMVS += potSMVS;
                    }
                    potSMVS = tempPotSMVS;
                }
                etPotenciaSMVS.setText(String.valueOf(potSMVS));

                // Factorial del primer número
                long factSMVS = 1;
                for (int i = 1; i <= num1SMVS; i++) {
                    long tempFactSMVS = 0;
                    for (int j = 0; j < i; j++) {
                        tempFactSMVS += factSMVS;
                    }
                    factSMVS = tempFactSMVS;
                }
                etFactorialSMVS.setText(String.valueOf(factSMVS));

            } catch (Exception eSMVS) {
                // Error de parseo
            }
        }
    }
}
