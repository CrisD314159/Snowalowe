package application;

public class Snowalowe {

    private static Snowalowe snowalowe; //creamos una instancia, esta de momento es = null

    private Snowalowe(){  }    //creamos el constructor de la clase privado para que no se puede acceder a el
/*-----------------------------------------------------------------------------------------*/
    // el Synchronized sirve por si estamos usando hilos no creen una instancia diferente sino que los 2 hilos esperen a la finalizacion del metodo
    public synchronized static Snowalowe getSnowalowe() {
        if (snowalowe == null){              //este metodo va a retornar el objeto, si el objeto es null va a generar la primera y unica instancia del objeto
            snowalowe = new Snowalowe();
        }
        return snowalowe;
    }
 /*-----------------------------------------------------------------------------------------*/
}
