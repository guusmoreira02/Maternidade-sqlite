package br.com.dlweb.maternidade.bebe;

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
    private EditText etData_nascimento;
    private EditText etPeso;
    private EditText etAltura;

    public AdicionarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bebe_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editTextNome);
        etData_nascimento = v.findViewById(R.id.editTextDataNascimento);
        etPeso = v.findViewById(R.id.editTextPeso);
        etAltura = v.findViewById(R.id.editTextAltura);

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
        } else if (etData_nascimento.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a Especialidade!", Toast.LENGTH_LONG).show();
        } else if (etPeso.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
        }else if (etAltura.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
        }else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Bebe m = new Bebe();
            m.setNome(etNome.getText().toString());
            m.setData_nascimento(etData_nascimento.getText().toString());
            m.setPeso(etPeso.getText().toString());
            m.setAltura(etAltura.getText().toString());
            databaseHelper.createBebe(m);
            Toast.makeText(getActivity(), "Medico salvo!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameBebe, new ListarFragment()).commit();
        }
    }
}