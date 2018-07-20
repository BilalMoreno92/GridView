package e.android9ed.gridview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paises = findViewById(R.id.gvDatos);
        String[] nombres = {"brazil", "canada", "china", "france", "germany", "india", "italy", "japan", "korea", "mexico", "netherlands", "portugal", "spain", "turkey", "united_kingdom", "united_states"};
        List<Pais> listaPaises = new ArrayList<>();
        for (int i = 0; i < nombres.length; i++){
            listaPaises.add(new Pais(nombres[i].toUpperCase(),getApplicationContext().getResources().getIdentifier(nombres[i], "drawable", getApplicationContext().getPackageName())));
        }
        for (int i = 0; i < listaPaises.size(); i++){
            Log.d("Pais", listaPaises.get(i).getNombre() + " :: " + listaPaises.get(i).getBandera());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPaises);
        ImageAdapter adaptadorConImagen = new ImageAdapter(this, R.layout.gridview_item, (ArrayList) listaPaises);
        paises.setAdapter(adaptadorConImagen);
    }
    
    public void onClick(View v){
        int numeroColumnas = 0;
        switch (v.getId()){
            case R.id.btUno:
                numeroColumnas = 1;
                break;
            case R.id.btDos:
                numeroColumnas = 2;
                break;
            case R.id.btTres:
                numeroColumnas = 3;
                break;
            case R.id.btCuatro:
                numeroColumnas = 4;
                break;
        }
        paises.setNumColumns(numeroColumnas);
    }

    private class ImageAdapter extends BaseAdapter{

        Context context;
        int recursoId;
        ArrayList datos;

        public ImageAdapter(Context context, int recursoId, ArrayList datos) {
            this.context = context;
            this.recursoId = recursoId;
            this.datos = datos;
        }

        @Override
        public int getCount() {
            return datos.size();
        }

        @Override
        public Object getItem(int position) {
            return datos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView imageView;
            View view;

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) { //Nuevo es necesario crear
                view = inflater.inflate(R.layout.gridview_item, null);

            } else { //Reciclado, ya creado
                view = (View) convertView;
            }
            TextView nombre = view.findViewById(R.id.txtPais);
            ImageView bandera = view.findViewById(R.id.imBandera);
            Pais pais = (Pais)datos.get(position);
            nombre.setText(pais.getNombre());
            bandera.setImageResource(pais.getBandera());
            return view;
        }
    }
}
