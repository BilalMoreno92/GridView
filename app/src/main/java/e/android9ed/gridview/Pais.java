package e.android9ed.gridview;

import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;

public class Pais {
    String nombre;
    int bandera;

    @Override
    public String toString() {
        return nombre;
    }

    public Pais(String nombre, int bandera) {
        this.nombre = nombre;
        this.bandera = bandera;
    }

    public int getBandera() {
        return bandera;
    }

    public String getNombre() {
        return nombre;
    }
}
