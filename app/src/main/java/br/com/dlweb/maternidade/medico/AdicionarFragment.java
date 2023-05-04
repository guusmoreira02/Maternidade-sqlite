package br.com.dlweb.maternidade.medico;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.com.dlweb.maternidade.R;
import br.com.dlweb.maternidade.database.DatabaseHelper;

public class AdicionarFragment extends Fragment {

    private EditText etNome;
    private EditText etEspecialidade;
    private EditText etCelular;

    public AdicionarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.medico_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editTextNome);
        etEspecialidade = v.findViewById(R.id.editTextEspecialidade);
        etCelular = v.findViewById(R.id.editTextCelular);

        Button btnAdicionar = v.findViewById(R.id.buttonAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
        });

        return v;
    }

    private void adicionar () {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (etEspecialidade.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a Especialidade!", Toast.LENGTH_LONG).show();
        } else if (etCelular.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
        }else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Medico m = new Medico();
            m.setNome(etNome.getText().toString());
            m.setEspecialidade(etEspecialidade.getText().toString());
            m.setCelular(etCelular.getText().toString());
            databaseHelper.createMedico(m);
            Toast.makeText(getActivity(), "Medico salvo!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
        }
    }
}