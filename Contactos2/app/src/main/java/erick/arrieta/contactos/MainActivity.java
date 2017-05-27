package erick.arrieta.contactos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;
    private Contact dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dataSource = new Contact(this);
        dataSource.open();
        contactos = dataSource.GetContactos();

        /*new ArrayList();
        contactos.add(new Contacto("Juan","5567546302","juanito@gmail.com"));
        contactos.add(new Contacto("Eduardo","5538477723","eduardo@gmail.com"));
        contactos.add(new Contacto("Lucya","5599377465","lucya@gmail.com"));
        contactos.add(new Contacto("Pedro","559599394","pedro@gmail.com"));*/
        ArrayList<String> nombres = new ArrayList<String>();

        for (Contacto contacto:contactos ) {
            nombres.add(contacto.getNombre());

        }

        ListView listViewContactos = (ListView) findViewById(R.id.listViewContactos);
        listViewContactos.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,nombres));

        listViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetallesContacto.class);
                intent.putExtra("nombre",contactos.get(position).getNombre());
                intent.putExtra("telefono",contactos.get(position).getTelefono());
                intent.putExtra("email", contactos.get(position).getEmail());

                startActivity(intent);
            }
        });

    }
}
