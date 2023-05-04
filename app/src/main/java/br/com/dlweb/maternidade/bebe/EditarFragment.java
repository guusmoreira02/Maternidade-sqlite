package br.com.dlweb.maternidade.bebe;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class EditarFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private Bebe m;
    private EditText etNome;
    private EditText etData_nascimento;
    private EditText etPeso;
    private EditText etAltura;

    public EditarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bebe_fragment_editar, container, false);
        Bundle b = getArguments();
        int id_bebe = b != null ? b.getInt("id") : null;

        etNome = v.findViewById(R.id.editTextNome);
        etData_nascimento = v.findViewById(R.id.editTextDataNascimento);
        etPeso = v.findViewById(R.id.editTextPeso);
        etAltura = v.findViewById(R.id.editTextAltura);


        databaseHelper = new DatabaseHelper(getActivity());
        m = databaseHelper.getByIdBebe(id_bebe);
        etNome.setText(m.getNome());
        etData_nascimento.setText(m.getData_nascimento());
        etPeso.setText(m.getPeso());
        etAltura.setText(m.getAltura());

        Button btnEditar = v.findViewById(R.id.buttonEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_bebe);
            }
        });

        Button btnExcluir = v.findViewById(R.id.buttonExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.dialog_excluir_medico);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_bebe);
                    }
                });
                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Não faz nada
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return v;
    }

    private void editar (int id) {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (etData_nascimento.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a Especialidade!", Toast.LENGTH_LONG).show();
        } else if (etPeso.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
        }  else if (etAltura.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Bebe m = new Bebe();
            m.setId(id);
            m.setNome(etNome.getText().toString());
            m.setData_nascimento(etData_nascimento.getText().toString());
            m.setPeso(etPeso.getText().toString());
            m.setAltura(etAltura.getText().toString());
            databaseHelper.updateBebe(m);
            Toast.makeText(getActivity(), "Medico atualizada!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameBebe, new ListarFragment()).commit();
        }
    }

    private void excluir(int id) {
        m = new Bebe();
        m.setId(id);
        databaseHelper.deleteBebe(m);
        Toast.makeText(getActivity(), "Bebe excluída!", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameBebe, new ListarFragment()).commit();
    }
}