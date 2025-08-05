package Zonafit;

import Zonafit.datos.ClienteDAO;
import Zonafit.datos.IClienteDAO;
import Zonafit.presentacion.ZonaFitApp;

public class Main {
    public static void main(String[] args) {
        IClienteDAO clienteDao = new ClienteDAO();
        ZonaFitApp app = new ZonaFitApp(clienteDao);
        app.iniciar();
    }
}