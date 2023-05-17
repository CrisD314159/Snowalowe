package persistencia;


import exceptions.CuentaException;
import model.Cuenta;
import model.Producto;
import model.Red;
import model.Vendedor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Persistencia {

	public static final String RUTA_ARCHIVO_CUENTAS = "src/resources/cuentas.txt";
	public static final String RUTA_ARCHIVO_VENDEDORES = "src/resources/vendedores.txt";
	public static final String RUTA_ARCHIVO_USUARIOS = "src/resources/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_LOG = "src/resources/Log.txt";
	public static final String RUTA_ARCHIVO_OBJETOS = "src/resources/archivoObjetos.txt";
	public static final String RUTA_ARCHIVO_MODELO_PANADERIA_BINARIO = "src/resources/Red.dat";
	public static final String RUTA_ARCHIVO_MODELO_PANADERIA_XML = "src/resources/Red.xml";


	
	
	public static void cargarDatosArchivos(Red snowalowe) throws FileNotFoundException, IOException {
		
		


		
		//cargar archivos empleados
		ArrayList<Vendedor> vendedoresCargados = cargarVendedores();
		
		if(vendedoresCargados.size() > 0)
			snowalowe.getListaUsuarios().addAll(vendedoresCargados);
		
		//cargar archivo objetos
		
		//cargar archivo empleados
		
		//cargar archivo prestamo
		
	}
	
	
	



	
	
	public static void guardarVendedores(ArrayList<Vendedor> listaVendedores) throws IOException {
		
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Vendedor vendedor: listaVendedores)
		{
			contenido+= vendedor.getNombre()+","+vendedor.getApellido()+","+vendedor.getCedula()+","+vendedor.getCuenta().getUsuario()+","+vendedor.getCuenta().getContrasenia()+","+ vendedor.getProductos()+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENDEDORES, contenido, false);
	}

	public static void guardarRed(Red red) throws IOException {

		// TODO Auto-generated method stub

			ArchivoUtil.guardarRed("src/resources/prueba.txt", red);


	}

	public static Red cargarRed() {
		return ArchivoUtil.cargarRed("src/resources/prueba.txt");
	}
	
	
//	----------------------LOADS------------------------
	
	/**
	 *
	 * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	
	
	
	public static ArrayList<Vendedor> cargarVendedores() throws IOException {
		
		ArrayList<Vendedor> vendedores =new ArrayList<Vendedor>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENDEDORES);
		String linea="";

		for (String s : contenido) {
			linea = s;
			Vendedor vendedor = new Vendedor();
			vendedor.setNombre(linea.split(",")[0]);
			vendedor.setApellido(linea.split(",")[1]);
			vendedor.setCedula(linea.split(",")[2]);
			vendedor.getCuenta().setUsuario(linea.split(",")[3]);
			vendedor.getCuenta().setContrasenia(linea.split(",")[4]);
			//vendedor.setProductos( linea.split(",")[5]);
			vendedores.add(vendedor);
		}
		return vendedores;
	}




	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
	{
		
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}


	public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, CuentaException {
		
		if(validarUsuario(usuario,contrasenia)) {
			return true;
		}else {
			throw new CuentaException("La cuenta no existe");
		}
		
	}
	
	private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException 
	{
		ArrayList<Cuenta> cuentas = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
		
		for (int indiceUsuario = 0; indiceUsuario < cuentas.size(); indiceUsuario++)
		{
			Cuenta usuarioAux = cuentas.get(indiceUsuario);
			if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Cuenta> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
		ArrayList<Cuenta> cuentas =new ArrayList<Cuenta>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			
			Cuenta cuenta = new Cuenta();
			cuenta.setUsuario(linea.split(",")[0]);
			cuenta.setContrasenia(linea.split(",")[1]);
			
			cuentas.add(cuenta);
		}
		return cuentas;
	}
	
	
//	----------------------SAVES------------------------
	
	/**
	 * Guarda en un archivo de texto todos la informaci�n de las personas almacenadas en el ArrayList
	 * @throws IOException
	 */
	
	public static void guardarObjetos(ArrayList<Cuenta> listaCuentas) throws IOException  {
		String contenido = "";
		
		for(Cuenta cuenta : listaCuentas) {
			contenido+= cuenta.getUsuario()+","+ cuenta.getContrasenia()+","+"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CUENTAS, contenido, true);
	}


	
	
	
	//------------------------------------SERIALIZACI�N  y XML
	
	
	public static Red cargarRecursoBancoBinario() {
		
		Red snowalowe = null;
		
		try {
			snowalowe = (Red)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_PANADERIA_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return snowalowe;
	}
	
	public static void guardarRecursoBancoBinario(Red snowalowe) {
		
		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_PANADERIA_BINARIO, snowalowe);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Red cargarRecursoRedXML() {

		Red snowalowe = null;
		
		try {
			snowalowe = (Red)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PANADERIA_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return snowalowe;

	}

	
	
	public static void guardarRecursoRedXML(Red snowalowe) {
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PANADERIA_XML, snowalowe);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
