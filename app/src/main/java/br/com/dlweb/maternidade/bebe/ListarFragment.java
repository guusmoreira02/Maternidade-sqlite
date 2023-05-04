package br.com.dlweb.maternidade.bebe;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.dlweb.maternidade.R;
import br.com.dlweb.maternidade.database.DatabaseHelper;

public class ListarFragment extends Fragment {

    public ListarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bebe_fragment_listar, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

        RecyclerView recyclerViewBebes = v.findViewById(R.id.recyclerViewBebes);

        LinearLayoutManager manager = new LinearLayoutManager(v.getContext());
        recyclerViewBebes.setLayoutManager(manager);
        recyclerViewBebes.addItemDecoration(new DividerItemDecoration(v.getContext(), LinearLayoutManager.VERTICAL));
        recyclerViewBebes.setHasFixedSize(true);

        Cursor dataBebe = databaseHelper.getAllBebe();
        List<Bebe> bebes = new ArrayList<Bebe>();
        while (dataBebe.moveToNext()) {
            Bebe m = new Bebe();
            int idColumnIndex = dataBebe.getColumnIndex("_id");
            m.setId(Integer.parseInt(dataBebe.getString(idColumnIndex)));
            int nameColumnIndex = dataBebe.getColumnIndex("nome");
            m.setNome(dataBebe.getString(nameColumnIndex));
            int data_nascimentoColumnIndex = dataBebe.getColumnIndex("data_nascimento");
            m.setData_nascimento(dataBebe.getString(data_nascimentoColumnIndex));
            bebes.add(m);
        }
        dataBebe.close();
        //No método getAllBebe() apenas abre a conexão
        databaseHelper.closeDBConnection();

        BebeAdapter adapterBebes = new BebeAdapter(bebes, getActivity());
        recyclerViewBebes.setAdapter(adapterBebes);
        return v;
    }
}