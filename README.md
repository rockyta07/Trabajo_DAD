# Fluff&Paws
Marcia García de la Mata, Rocío Arranz Esteban y Daniel Corredor Padrino

# Fase 1: Declaración de Objetivos

Página Web para la adopción de animales las funcionalidades de esta estarán separadas según si son publicas o privadas:
- Privadas:
  - Perfil del usuario adoptante (visitantes que se han registrado)
  - Foro / chat de contacto para el proceso de adopción con la protectora
- Públicas:
  - Los animales no adoptados en cada protectora separados por categoría
  - Un apartado informativo de los cuidados básicos de cada tipo de animal
  - Perfil público de cada protectora

Las entidades principales serán:
- Animales (divididos por tipo)
- Mensajes
- Adoptantes
- Protectoras

Descripción de las funcionalidades del servicio interno:

La web pretende ofrecer toda la información necesaria para antes de adoptar, ayudar y simplificar el proceso de adopción y ofrecer servicios posteriores a la adopción (pueden ser utilizados por todo quien tenga un animal) como foros o información de veterinarios, adiestradores u otras consideraciones importantes en los cuidados del animal.

Todos los usuarios que deseen adoptar necesariamente deberán crearse un perfil con un nombre de usuario (inicialmente exclusivamente privado) puede ser tan básico como el usuario desee, sin embargo, hay información que por motivos de registros deben facilitarse (documento de identificación, edad), este se puede editar y borrar en cualquier momento. 

Los visitantes no necesitan perfiles para hacer uso de la web ni nombres de usuario, sin embargo, estos no serán capaces de adoptar animales pero si pedir información a las protectoras (por motivos de identificación en se le asociará un nombre de usuario aleatorio).

Las protectoras son una entidad diferente a las otras dos mencionadas anteriormente. Deberán tener un perfil público completo, este se dara de alta mediante un proceso que asegure que la protectora es efectivamente una registrada y no es un grupo de crianza ilegal, por ejemplo. Tendrá un usuario elegido por la propia protectora y será capaz de crear entradas con tarjetas de información de cada uno de los animales en adopción teniendo que rellenar obligatoriamente los campos básicos.

# Fase 2: Implementación Funcional

Durante el desarrollo de la base de datos nos hemos puesto los siguientes objetivos a cumplir:

DISEÑO:

* Diseñar un diagrama UML con las clases y funciones a implementar (nunca se llego a diseñar como tal, lo cuál nos generó muchos problemas a la hora de entender el código de otra persona o con la cantidad de versiones diferentes que han surgido durante la fase 2).

* Diseñar un esquema de la base de datos básica: 
    Diseñamos un primer concepto de lo que queríamos en nuestra base de datos ya que nos simplificaba la     creación constante de bases de datos hasta que empezamos a entender mejor el funcionamiento de Spring     initializer (incluso entonces nos ayudó para la creación de las dependencias OneToMany, ManyToOne...)

    ![UML_webAdopcion](https://user-images.githubusercontent.com/102540777/221366771-64a7c459-0cde-4a95-8acd-1ff0345d9281.jpg)

    Como diseño general nos ayudó mucho, posteriormente según añadimos funcionalidades nos topamos con       que necesitabamos tomar una decisión que afectaba al filtrado de los animales a mostrar ya que           nuestra entidad Animal inicialmente es creada por una Protectora y luego un Usuario lo puede adoptar. 

    En la creación del animal se debe especificar a que protectora pertenece, pero ¿Cuando es adoptado       que ocurre?

    Opción a) Mantenemos que el canpo de la protectora sin tocar (no se cambiaría a null) aunque un                     adoptante adopte al animal -> Implica que al mostrar los animales en la pantalla inicial                 debemos filtrar por los que tengan su campo usuario a null ya que eso significa que no han               sido adoptados aún.
    Opción b) Cambiamos el campo de la protectora a null en cuanto es adoptado un animal y así solo                     filtramos por loa animales que tiene una protectora.

    Realmente la complejidad de cada una es similar pero optamos por la opción realista (Opción a)) y es     que en la vida real se dan casos de "devolución" de animales.

* Planificación de las plantillas de la web (debido a que nos encontramos justos de tiempo, decidimos optar por plantillas que adaptamos a nuestras necesidades, teniendo en mente una posible mejora visual y funcional de cara a las siguientes fases usando otras herranientas como Axure)

* Recolecta de imágenes sin copyright para mostrarlas en la web.

INVESTIGACIÓN:
* Investigar el funcionamiento de MySQL y la implementación con entidades en Java.
* Investigar el funcionamiento de Mustache como creador de plantillas html.
* Estudiar la implementación general de la BBDD + HTML.

IMPLEMENTACIONES:
* Implementación de la BBDD diseñada.
* Implementación de Mustache y las plantillas de la web.
* Pruebas y debugging de la implementación inicial para evaluar los errores y corregirlos en las siguientes versiones.


--------------------------------------------------------------------------------------------------------
                           PROBLEMAS CON LOS QUE NOS HEMOS ENCONTRADO:  
--------------------------------------------------------------------------------------------------------

Tras presentarse un sin fin de problemas, optamos por cambiar de IDE, usando ahora IntelliJ. Para resolver los problemas básicos hemos desarollado un par de videos a modo de tutorial que ayudan a comprender como trabajar en Github y en el IDE a la vez, así como trabajar con las bases de datos en IntelliJ:

- https://urjc-my.sharepoint.com/:v:/g/personal/r_arranz_2020_alumnos_urjc_es/EdWWhKa94VRPol5vn5-p3VQBsnoUs1gdhqfiRvCenq2hpg?e=cOHJfh
- https://urjc-my.sharepoint.com/:v:/g/personal/r_arranz_2020_alumnos_urjc_es/ESc4gWhVX3VIus1w0HLsv8wBsZRGA36lfbRqUUoZQ3jibQ?e=DEdCfc

Posteriormente nos encontramos con problemas de versiones, siendo el más destacable el uso continuo de RestController en vez de Controller.
