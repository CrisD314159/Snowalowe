package controllers;



import exceptions.ProductoException;
import exceptions.VendedorException;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import model.*;
import persistencia.Persistencia;


import java.io.IOException;
import java.util.ArrayList;

public class ModelFactoryController implements Runnable{

	Red red;

	Thread guardarXml;


	Thread cargarXml;


	Thread guardarBinario;
	Thread cargarBinario;


	//------------------------------  Singleton ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {


		if(red == null){
			//iniciarSalvarDatosPrueba();

			cargarResourceXML();
			guardarResourceXML();


		}

		//Registrar la accion de incio de sesi�n
		Persistencia.guardaRegistroLog("Bienvenidos a Snowalowe", 1, "InicioApp");


	}




	private void iniciarSalvarDatosPrueba() {
		inicializarDatos();
		try {

			Persistencia.cargarDatosArchivos(getRed());
			Persistencia.guardarVendedores(getRed().getListaUsuarios());


		} catch (IOException e) {
			throw new RuntimeException(e);
		}



	}

	@Override
	public void run() {
		Thread hiloActual = Thread.currentThread();
		if(hiloActual == guardarXml){
			//guardarResourceXml();
		}
		if (hiloActual ==  cargarXml){
			//cargarResourceXml();
		}



	}

	private void cargarDatosDesdeArchivos() {

		red = new Red();

		try {
			Persistencia.cargarDatosArchivos(getRed());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	public void cargarResourceXML() {
		/**
		 * cargarXml = new Thread(this);
		 * 		cargarXml.start();
		 */
		red = Persistencia.cargarRecursoRedXML();

	}





	public void guardarResourceXML() {
		/**
		 * guardarXml = new Thread(this);
		 * 		guardarXml.start();
		 */
		Persistencia.guardarRecursoRedXML(red);

	}



	private void inicializarDatos() {

		red = new Red();


		Cuenta cuenta = new Cuenta("user1", "1234");
		Vendedor cliente = new Vendedor();
		cliente.setNombre("juan");
		cliente.setApellido("arias");
		cliente.setCedula("125454");
		cliente.setDireccion("Armenia");
		cliente.setCuenta(cuenta);

		red.getListaUsuarios().add(cliente);



		System.out.println("Banco inicializado "+ red);

	}

	public Red getRed() {
		return red;
	}

	public void setRed(Red red) {
		this.red = red;
	}



	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion, String user, String password) {


		Vendedor vendedor = null;

		try {
			vendedor = getRed().nuevoVendedor(nombre, apellido, cedula, direccion, user, password);
			guardarResourceXML();
		} catch (VendedorException e) {
			throw new RuntimeException(e);
		}

		return vendedor;

	}

	public boolean crearPublicacion(String nombre, String codigo, double precio, String categoria, Image image, Vendedor vendedorLogeado, String date) {

		boolean producto = false;
		try {
			producto = getRed().crearProducto(nombre, codigo, categoria, precio, image, vendedorLogeado, date);

			guardarResourceXML();
		} catch (ProductoException e) {
			throw new RuntimeException(e);
		}
		return producto;
	}


	public boolean actualizarVendedor(String nombre, String apellido, String cedula, String direccion) {

		return getRed().actualizarVendedor( nombre, apellido, cedula, direccion);

	}


	public Boolean eliminarVendedor(String cedula) {

		boolean flagEmpleadoExiste = false;

		flagEmpleadoExiste = getRed().eliminarVendedor(cedula);
		return flagEmpleadoExiste;
	}


	public Vendedor obtenerEmpleado(String cedula) {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<Vendedor> obtenerEmpleados() {

		return red.getListaUsuarios();
	}

	public boolean verificarCuenta(String user, String password) {
		boolean cuenta = false;
		try {
			cuenta =  red.verificarUsuario(user, password);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cuenta;
	}

	public Vendedor buscarVendedor(String usuario, String contrasenia) {
		Vendedor vendedor = null;
		try{
			vendedor = red.obtenerVendedor(usuario, contrasenia);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return  vendedor;
	}

	public boolean respoderMensaje(String mensaje, Vendedor vendedorLogeado, Vendedor autor) {

		boolean responder = false;
		try {
			responder = red.responderMensaje(mensaje, autor, vendedorLogeado);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return responder;
	}

	public boolean eliminarProducto(String codigo, Vendedor vendedor) {
		boolean eliminado = false;
		try {
			eliminado = red.eliminarProducto(codigo, vendedor);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return eliminado;
	}

	public boolean actualizarProducto(String nombre, String codigo, EstadoProducto estado, String categoria, double precio, Image image, Vendedor vendedor) {
		boolean actualizado = false;
		try {
			actualizado = red.actualizarProducto(nombre, codigo, categoria, precio, estado, vendedor, image);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return actualizado;
	}

	public boolean enviarMensaje(String mensaje, Vendedor vendedorLogeado, Vendedor vendedorAliado) {
		boolean enviado = false;
		try {
			enviado = red.enviarMensaje(vendedorLogeado, vendedorAliado, mensaje);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return enviado;
	}

	public ArrayList<Vendedor> obtenerListaVendedoresRecomendados(Vendedor vendedorLogeado) {
		return red.obtenerListaRecomendados(vendedorLogeado);
	}

	public void actualizarTablaRecomendados(Vendedor vendedorLogeado) {
		red.actualizarTablaRecomendaciones(vendedorLogeado);
		guardarResourceXML();
	}

	public boolean enviarSolicitud(Vendedor vendedorLogeado, Vendedor vendedorSeleccionado) {
		boolean enviado = false;
		try {
			enviado =  red.enviarSolucitudAmistad(vendedorLogeado, vendedorSeleccionado);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return enviado;
	}

	public void aceptarSolicitud(Vendedor vendedorLogeado, Vendedor vendedorSeleccionado) {
		red.aceptarSolicitud(vendedorLogeado, vendedorSeleccionado);
		guardarResourceXML();
	}

	public void rechazarSolicitud(Vendedor vendedorLogeado, Vendedor vendedorSeleccionado) {
		red.rechazarSolicitud(vendedorLogeado,vendedorSeleccionado);
		guardarResourceXML();
	}

	public ArrayList<Vendedor> obtenerListaSolicitudes(Vendedor vendedorLogeado) {

		return red.obtenerListaSolicitudes(vendedorLogeado);
	}

	public int contarMeGustas(Producto productoSeleccionado) {
		return red.contarMegustas(productoSeleccionado);
	}

	public boolean verificarExisteMeGusta(Vendedor vendedorAliado, Vendedor vendedorLogeado, Producto productoSeleccionado) {
		return red.verificarMeGusta(vendedorAliado, vendedorLogeado, productoSeleccionado);
	}

	public boolean agregarMeGusta(Vendedor vendedorAliado,Vendedor vendedorLogueado, Producto productoSeleccionado) {
		boolean agregado = false;
		try {
			agregado = red.anadirMeGusta(vendedorAliado, vendedorLogueado, productoSeleccionado);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return agregado;
	}

	public void quitarMeGusta(Vendedor vendedorAliado, Vendedor vendedorLogeado, Producto productoSeleccionado) {
		red.quitarMeGusta(vendedorAliado, vendedorLogeado, productoSeleccionado);
	}

	public ArrayList<Mensaje> obtenerListaMensajes(Vendedor vendedorLogeado) {
		return red.obtenerListaMensajes(vendedorLogeado);
	}

	public Comentario agregarComenterio(Vendedor vendedorLogeado, Vendedor vendedorAliado, String mensaje, Producto productoSeleccionado) {
		Comentario comentario = null;
		try {
			comentario = red.agregarComentario(vendedorLogeado, vendedorAliado, mensaje, productoSeleccionado);
			guardarResourceXML();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return comentario;
	}






}
