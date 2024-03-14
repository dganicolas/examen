import java.time.LocalDate

/**
 * 1. Descripción
 * ¡Bienvenidos a esta emocionante actividad individual de programación orientada a objetos (POO)!
 * Vamos a embarcarnos en la creación de un Sistema de Gestión de Biblioteca utilizando Kotlin,
 * un lenguaje moderno y poderoso que nos permitirá aplicar y profundizar en nuestros conocimientos de
 * POO de manera práctica y efectiva.
 *
 * En este sistema, seremos capaces de gestionar y realizar préstamos de libros
 * y otros elementos que exploraremos más adelante. El objetivo es desarrollar un
 * sistema que no solo sea funcional sino también eficiente, escalable y fácil de mantener.
 *
 * Interacción y Usabilidad: Para verificar el correcto funcionamiento del sistema
 * y facilitar la interacción con el mismo, implementaremos un menú de usuario.
 * Este menú incorporará todas las acciones posibles del sistema,
 * permitiendo al usuario interactuar de manera clara y eficaz.
 *
 * 1.1 Elementos Clave del Dominio
 * Para llevar a cabo este proyecto, trabajaremos con varios conceptos clave que formarán la base
 * de nuestro sistema:
 *
 * Libro: Este será uno de los elementos fundamentales de nuestro sistema.
 * Cada libro contendrá información esencial como por ejemplo id (UUID),
 * título, autor, año de publicación, temática, y estado (disponible o prestado).
 *
 * GestorBiblioteca: Esta clase será el corazón de nuestro sistema,
 * encargándose de la lógica para gestionar los libros y otros elementos.
 * Incluirá un catálogo de elementos y un registro de préstamos (RegistroPrestamos),
 * permitiendo agregar o eliminar elementos, registrar préstamos y devoluciones,
 * y consultar la disponibilidad de los elementos.
 *
 * Usuario: Representará a los usuarios de la biblioteca,
 * conteniendo información como ID, nombre, y una lista de libros prestados.
 *
 * Catálogo: Este concepto merece especial atención.
 * Se trata de una colección dinámica que alberga todos los
 * elementos disponibles en la biblioteca, desde libros hasta revistas y DVDs.
 * El catálogo facilita la organización y el acceso rápido a estos elementos,
 * jugando un papel fundamental en la eficiencia y flexibilidad del sistema.
 *
 * RegistroPrestamos: Mantendrá un registro de los préstamos actuales y
 * un historial de todos los préstamos realizados, permitiendo registrar préstamos y devoluciones,
 * así como consultar el historial de préstamos de libros específicos o usuarios.
 *
 * 1.2 Evolución y Optimización del Sistema
 * A lo largo de este proyecto, no solo estableceremos las bases funcionales de nuestro sistema
 * sino que también lo expandiremos y optimizaremos mediante:
 *
 * La integración de métodos estáticos y el aprovechamiento de clases genéricas,
 * lo que nos permitirá manejar diversos tipos de elementos de manera más eficiente.
 *
 * La aplicación de principios de herencia, clases abstractas e interfaces
 * para construir una arquitectura de software robusta y extensible.
 *
 * 1.3 Buenas Prácticas y Organización del Código
 * A medida que desarrollamos nuestro sistema, deberemos seguir varias buenas prácticas
 * para asegurar la calidad y la comprensión del código:
 *
 * Comentarios y Documentación: Es vital comentar adecuadamente aquellos puntos del código
 * que contengan lógica compleja o crítica. Esto no solo ayuda en el proceso de depuración
 * sino que también facilita el mantenimiento y la extensibilidad del sistema.
 *
 * Separación de Responsabilidades: La salida a consola debe estar estrictamente
 * separada de la lógica de gestión de la biblioteca.
 * Evitaremos colocar println directamente en las clases que no tienen esa responsabilidad.
 * En su lugar, utilizaremos mecanismos de log en clases específicamente diseñadas
 * para interactuar con el usuario, manteniendo así nuestro código limpio y bien organizado.
 *     1.4 Trabajo en Ramas
 *     El proyecto esta formado de un conjunto de ejercicios que evolucionará
 *     el sistema en base a las especificaciones. Cada ejercicio se trabajará en ramas separadas,
 *     permitiendo una organización clara y facilitando la revisión del trabajo realizado.
 *     Los nombres de las ramas seguirán el patrón ejercicio-N, donde N es el número del ejercicio,
 *     ayudando a mantener una estructura ordenada y coherente en el repositorio.
 *
 * */

/**
 * Libro: Este será uno de los elementos fundamentales de nuestro sistema.
 * Cada libro contendrá información esencial como por ejemplo id (UUID),
 * título, autor, año de publicación, temática, y estado (disponible o prestado).
 * Utilizas un String para representar el ID (UUID) del libro.
 * Esto está bien, pero ten en cuenta que los UUID se generan generalmente
 * como cadenas alfanuméricas de 32 caracteres (por ejemplo, “6ba7b810-9dad-11d1-80b4-00c04fd430c8”).
 * Si deseas utilizar un UUID real, puedes considerar cambiar el tipo de dato del ID a java.util.UUID.
 * */
interface Element{
    val id:Int
    val título:String
}
interface Book :Element{
    val autor:String
    val anoDePublicación:String
    val temática:String
    var estado:Boolean
}

data class Libro(
    override val id: Int,
    override val título: String,
    override val autor: String,
    override val anoDePublicación: String,
    override val temática: String,
    override var estado: Boolean
) :Book

/**
 * Usuario: Representará a los usuarios de la biblioteca,
 * conteniendo información como ID, nombre, y una lista de libros prestados.
 */
interface InterfazUsuario{
    val id:Int
    var nombre:String
    val listaLibros:MutableList<Libro>
}
class Usuario(override val id: Int,
                   override var nombre: String,
                   override val listaLibros: MutableList<Libro>):InterfazUsuario

/**
* GestorBiblioteca: Esta clase será el corazón de nuestro sistema,
* encargándose de la lógica para gestionar los libros y otros elementos.
* Incluirá un catálogo de elementos y un registro de préstamos (RegistroPrestamos),
* permitiendo agregar o eliminar elementos, registrar préstamos y devoluciones,
* y consultar la disponibilidad de los elementos.
*/

class GestorBibloteca(val usuarios:MutableList<InterfazUsuario>,
                      val catalogo: Catalogue,
                      val registroPrestamos: LoanRegistration){
    fun añadirUsuario(){
        val nombre = GestorConsola.crearUsuario()
    }

}

/**
* Catálogo: Este concepto merece especial atención.
* Se trata de una colección dinámica que alberga todos los
* elementos disponibles en la biblioteca, desde libros hasta revistas y DVDs.
* El catálogo facilita la organización y el acceso rápido a estos elementos,
* jugando un papel fundamental en la eficiencia y flexibilidad del sistema.
*/
interface Catalogue{
    val elementos: MutableMap<Int, Element>
    fun agregarElemento(elemento: Element)
    fun eliminarElemento(id: Int)
    fun buscarElemento(id: Int): Element?

    fun listarElementos(): List<Element>
}
class Catalogo:Catalogue {
    override val elementos: MutableMap<Int, Element> = mutableMapOf()
    override fun agregarElemento(elemento: Element) {
        elementos[elemento.id] = elemento
    }

    override fun eliminarElemento(id: Int) {
        elementos.remove(id)
    }

    override fun buscarElemento(id: Int): Element? {
        return elementos[id]
    }

    override fun listarElementos(): List<Element> {
        return elementos.values.toList()
    }
}




/**
* RegistroPrestamos: Mantendrá un registro de los préstamos actuales y
* un historial de todos los préstamos realizados, permitiendo registrar préstamos y devoluciones,
* así como consultar el historial de préstamos de libros específicos o usuarios.
*/
interface LoanRegistration{
    val prestamosActuales:MutableMap<Int,MutableList<String>>
    val historialPrestamos:MutableMap<Int,MutableList<String>>

    fun registrarPrestamo(libro: Libro, usuario: Int)

    fun registrarDevolucion(libro: Libro,usuario: Int)

    fun consultarHistorial(usuario: Int)

    fun consultarHistorial(libro: Libro)
}
class RegistroPrestamos:LoanRegistration {

    override val prestamosActuales = mutableMapOf<Int,MutableList<String>>()
    override val historialPrestamos = mutableMapOf<Int,MutableList<String>>()

    override fun registrarPrestamo(libro: Libro, usuario: Int) {
        if (prestamosActuales.containsKey(libro.id)) {
            prestamosActuales[libro.id]?.add("el libro ${libro.título} con ID:${libro.id} ha sido prestado")
        } else {
            prestamosActuales[libro.id] = mutableListOf("el libro ${libro.título} con ID:${libro.id} ha sido prestado")
        }
        if (historialPrestamos.containsKey(usuario)) {
            historialPrestamos[usuario]?.addLast("el libro ${libro.título} con ID:${libro.id} ha sido prestado al usuario $usuario, a dia de ${LocalDate.now()}")
        } else {
            historialPrestamos[usuario] = mutableListOf("el libro ${libro.título} con ID:${libro.id} ha sido prestado al usuario $usuario")
        }
        GestorConsola.libroPrestadoODevuelto(historialPrestamos[usuario]?.last() ?: "libro no encontrado")
    }

    override fun registrarDevolucion(libro: Libro, usuario: Int) {
        prestamosActuales[libro.id]?.clear()
        historialPrestamos[usuario]?.addLast("el libro ${libro.título} con ID:${libro.id} ha sido devuelto por el usuario $usuario, a dia de ${LocalDate.now()}")
        GestorConsola.libroPrestadoODevuelto(historialPrestamos[usuario]?.last() ?: "libro no encontrado")
    }

    override fun consultarHistorial(usuario: Int) {
        GestorConsola.consultarHistorial(historialPrestamos[usuario]?.toList())
    }

    override fun consultarHistorial(libro: Libro) {
        GestorConsola.consultarHistorial(prestamosActuales[libro.id])
    }
}


object GestorConsola{
    var idAutomatica = 0
    fun idSecuencial():Int{
        idAutomatica ++
        return idAutomatica
        }

    fun pedirNombre(){
        println("¿dime el nombre del nuevo usuario?")
    }

    fun crearUsuario():String{
        var nombre= ""
        pedirNombre()
        nombre=readln()
        return nombre
    }
    fun libroPrestadoODevuelto(texto:String){
        println(texto)
    }
    fun mostrarInfo(objeto:objetos){
        println("el libro ${objeto.título} lo ${objeto.temática}")
    }
    fun mostrarInfo(objeto:DVD){
        println("el libro ${objeto.título} lo ${objeto.porno}")
    }

    fun consultarHistorial(toList: List<String>?) {
        println(toList)
    }
}











