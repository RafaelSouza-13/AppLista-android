package devandroid.rafael.applista.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import devandroid.rafael.applista.R;
import devandroid.rafael.applista.model.Tarefa;
import devandroid.rafael.applista.model.Pessoa;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    public static final String NOME_PREFERENCES = "pref_lista_tarefa";
    EditText editTextNome;
    EditText editTextSobrenome;
    EditText editTextCurso;
    EditText editTextContato;

    Button btnSalvar;
    Button btnLimpar;
    Button btnFinalizar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadIds();
    }

    protected void loadIds(){
        editTextNome = findViewById(R.id.editTextNome);
        editTextSobrenome = findViewById(R.id.editTextSobrenome);
        editTextCurso = findViewById(R.id.editTextTarefa);
        editTextContato = findViewById(R.id.editTextContato);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        preferences = getSharedPreferences(NOME_PREFERENCES, 0);
        SharedPreferences.Editor listaTarefa = preferences.edit();

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNome.setText("");
                editTextSobrenome.setText("");
                editTextContato.setText("");
                editTextCurso.setText("");
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Até mais", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarefa tarefa = new Tarefa(editTextCurso.getText().toString());
                Pessoa pessoa = new Pessoa(editTextNome.getText().toString(),
                        editTextSobrenome.getText().toString(),
                        tarefa,
                        editTextContato.getText().toString());
                Toast.makeText(MainActivity.this, "Salvando: "+pessoa.toString(), Toast.LENGTH_LONG).show();
                listaTarefa.putString("nome", pessoa.getNome());
                listaTarefa.putString("sobrenome", pessoa.getSobrenome());
                listaTarefa.putString("telefone", pessoa.getTelefone());
                listaTarefa.putString("tarefa", pessoa.getTarefa().getTarefa());
                listaTarefa.apply();
            }
        });
    }
}