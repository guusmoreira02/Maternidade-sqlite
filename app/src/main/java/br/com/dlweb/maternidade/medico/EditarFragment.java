package br.com.dlweb.maternidade.medico;

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
    private Medico m;
    private EditText etNome;
    private EditText etEspecialidade;
    private EditText etCelular;

    public EditarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.medico_fragment_editar, container, false);
        Bundle b = getArguments();
        int id_medico = b != null ? b.getInt("id") : null;

        etNome = v.findViewById(R.id.editTextNome);
        etEspecialidade = v.findViewById(R.id.editTextEspecialidade);
        etCelular = v.findViewById(R.id.editTextCelular);


        databaseHelper = new DatabaseHelper(getActivity());
        m = databaseHelper.getByIdMedico(id_medico);
        etNome.setText(m.getNome());
        etEspecialidade.setText(m.getEspecialidade());
        etCelular.setText(m.getCelular());

        Button btnEditar = v.findViewById(R.id.buttonEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_medico);
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
                        excluir(id_medico);
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
        } else if (etEspecialidade.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a Especialidade!", Toast.LENGTH_LONG).show();
        } else if (etCelular.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Medico m = new Medico();
            m.setId(id);
            m.setNome(etNome.getText().toString());
            m.setEspecialidade(etEspecialidade.getText().toString());
            m.setCelular(etCelular.getText().toString());
            databaseHelper.updateMedico(m);
            Toast.makeText(getActivity(), "Medico atualizada!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
        }
    }

    private void excluir(int id) {
        m = new Medico();
        m.setId(id);
        databaseHelper.deleteMedico(m);
        Toast.makeText(getActivity(), "Medico excluída!", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
    }
}