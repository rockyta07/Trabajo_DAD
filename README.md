# Fluff&Paws
Marcia García de la Mata, Rocío Arranz Esteban y Daniel Corredor Padrino

![logoWeb](https://user-images.githubusercontent.com/102540777/222416233-702d5929-cbea-4585-b3a5-8ef92fbf3fa7.png)

Para organizarnos usaremos "Trello" al cuál se puede acceder desde el siguiente link:
https://trello.com/invite/b/fjavwWrE/ATTI966c72349a3704d1d675c1228d98b2338C41BE0D/organizacion-fluffpaws

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

--------------------------------------------------------------------------------------------------------
                                                DISEÑO:  
--------------------------------------------------------------------------------------------------------

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

* Recolecta de imágenes sin copyright para mostrarlas en la web:
  Para ello se ha hecho uso de pixabay, cogiendo fotografías de animales sin regalías.

--------------------------------------------------------------------------------------------------------
                                           INVESTIGACIÓN:  
--------------------------------------------------------------------------------------------------------

* Investigar el funcionamiento de MySQL y la implementación con entidades en Java.
* Investigar el funcionamiento de Mustache como creador de plantillas html.
* Estudiar la implementación general de la BBDD + HTML.

--------------------------------------------------------------------------------------------------------
                                          IMPLEMENTACIONES:  
--------------------------------------------------------------------------------------------------------

* Implementación de la BBDD diseñada:
   Para realizar la conexión con la base de datos hemos usado Workbench, a la hora de crear la conexión es importante tener en cuenta que la contraseña debe de ser mayor de 8 dígitos para que la conexión no de ningún problema, el nombre que hemos usado para la conexión es fluffsandpaws y la contraseña password, la conexión se realizará incluyendo en el application.properties, la contraseña y el nombre, asi la conexión se realizará directamente:
spring.datasource.url=jdbc:mysql://localhost:3306/fluffandpaws.
spring.datasource.username=fluffandpaws.
spring.datasource.password=password.
Para la creación de tablas automaticamente, hemos incluido en los servicios de cada entidad un método init() que se encargará de crear las tablas cada vez que se ejecute la aplicación.
* Implementación de Mustache y las plantillas de la web:
  Para Mustache hemos tenido en cuenta una serie de cosas, en primer lugar hay que centrarse en la creación de servicios (los cuales poseen métodos de buscar en las tablas, modificar, añadir y borrar), los servicios tendrán un @Autowire con el repositorio de la entidad correspondiente, a su vez se crean los controllers de cada entidad, en estos controllers se crearán los objetos de los servicios para poder llamar a las funciones(Crear,modificar,etc), aquí ya entra la parte de Mustache, a la hora de realizar un método en controller que se encargue de mostrar por ejemplo, todos los usuarios, se indicará la plantilla a la cual se va a dirigir después de llamar al método que nos interesa. Esa plantilla se realizará con Mustache, que lo que permite es que metiendo el nombre de los atributos asignados con model.addAttribute recoja en este caso todos los usuarios y posteriormente mostrarlos.
* Pruebas y debugging de la implementación inicial para evaluar los errores y corregirlos en las siguientes versiones:
  Una vez que teniamos cosas programadas listas para comprobar se paso a probarlo. En estas comprobaciones nos surgieron unas series de errores que comentaremos a continuación. 



--------------------------------------------------------------------------------------------------------
                          INICIAR LA BASE DE DATOS CON MYSQL 
--------------------------------------------------------------------------------------------------------   

Para que la aplicación sea capaz de crear, modificar y guardar las tablas, debe existir una base de datos llamada "fluffandpaws" donde se conectará a un esquema creado llamado también "fluffandpaws". Los datos de inicio de sesión deberán ser los siguientes:

Usuario: fluffandpaws
Contraseña: password
  
  
--------------------------------------------------------------------------------------------------------
                          CAPTURAS DE NUESTRA PÁGINA WEB:  
--------------------------------------------------------------------------------------------------------      
La pantalla nada más poner localhost:8080 nos muestra la pantalla de protectoras. Cada pantalla está programa con un header y footer para no tener que repetir código constantemente.Tenemos un menú, formado por animales, protectora, mensajes y usuarios en el cual se puede elegir a que sección ir.
![image](https://user-images.githubusercontent.com/102741945/221723996-9d3ad6da-1398-40fa-a964-9db5df92317b.png)
Si entramos por ejemplo en protectora 3 y se ha creado algún animal perteneciente a esa protectora aparece lo sigueinte:

![image](https://user-images.githubusercontent.com/102741945/221727334-a20e01b0-7d76-4765-b970-95f6f1ffbdd2.png)

Si pulsamos en crear protectora nos aparecen los atributos para rellenar la nueva protectora:
![image](https://user-images.githubusercontent.com/102741945/221728006-3beb7ea0-49ec-4654-8720-c02df9ffcd0d.png)

![image](https://user-images.githubusercontent.com/102741945/221728046-f8afe0a9-c8d6-48e6-b2ef-1436e179e5b6.png)

Observamos en el Mysql y se ha creado:

![image](https://user-images.githubusercontent.com/102741945/221728074-922dbb1f-664e-44ca-b817-4b63477fbc2e.png)


Si entramos dentro de una protectora y le damos a editar:

![image](https://user-images.githubusercontent.com/102741945/221728386-385d0a6c-0e3b-4329-8661-b27b4a0bddc3.png)
![image](https://user-images.githubusercontent.com/102741945/221728396-ef3560ba-20ec-494b-bf39-957bffbe830f.png)

Si dentro de la protectora 1 le damos a crear animal, añadimos a un perro:
![image](https://user-images.githubusercontent.com/102741945/221729260-51889493-8f43-4409-bff4-f4c25895923a.png)

Luego al entrar en protectora 1 aparece:

![image](https://user-images.githubusercontent.com/102741945/221729416-940ef1c2-a53e-49f6-9267-e85fbe14dc62.png)

Dentro de protectora podemos borrar el animal asociado:
![image](https://user-images.githubusercontent.com/102741945/221729625-89dfd4d0-dd06-4f80-9261-ca96ef9a6667.png)


Ahora vamos a animales:

![image](https://user-images.githubusercontent.com/102741945/221729851-e1d5b8bf-86db-4f14-8911-42b8939c793d.png)

Tenemos la opción también de crear animales asociados a una determinada protectora:

![image](https://user-images.githubusercontent.com/102741945/221730195-41fdf533-c3d5-4978-a9ae-7d70a75878f8.png)
![image](https://user-images.githubusercontent.com/102741945/221730252-d6fb73d6-e2ff-464d-8f0d-68fd585ba4f5.png)
![image](https://user-images.githubusercontent.com/102741945/221730341-1914151a-dafa-4b44-a8d9-0f753fce2e89.png)
![image](https://user-images.githubusercontent.com/102741945/221730413-1d424dff-1d35-4672-9da4-444ff49a2da6.png)


Entrando en cada animal te da la opción de editar o borrar:


![image](https://user-images.githubusercontent.com/102741945/221730520-4b73d0dc-35e2-4642-aafc-259a4995e0a3.png)
![image](https://user-images.githubusercontent.com/102741945/221730558-749fe977-00bf-4579-973e-d51ad6456dd5.png)
En la página principal de animales ya no aparece:
![image](https://user-images.githubusercontent.com/102741945/221730644-76b741f5-d183-4b31-b294-e7ab8fb051bc.png)


Y en la base de datos tampoco:


![image](https://user-images.githubusercontent.com/102741945/221730701-034e0362-4b63-4678-a9d4-27757b98d60a.png)

Para editar nos metemos en el perro que queramos editar:
![image](https://user-images.githubusercontent.com/102741945/221730852-fc7835fd-2d99-49db-b5e6-fcc42405eea1.png)
![image](https://user-images.githubusercontent.com/102741945/221730900-f74840cf-d37c-46ea-a57a-f0653de66c40.png)
Y ahora ya visualizamos a Josito:
![image](https://user-images.githubusercontent.com/102741945/221730946-161a652f-e8e7-4ba1-b592-734fac0368af.png)

En usuarios el funcionamiento es el mismo, se pueden modificar, registrar y borrar:
![image](https://user-images.githubusercontent.com/102741945/221731069-d8e84b74-6df9-4315-9e42-991376b74be3.png)
Si entramos dentro de Juan por ejemplo y lo editamos:

![image](https://user-images.githubusercontent.com/102741945/221731188-5498ec3a-7fb9-415e-8c96-36ff33508d42.png)
Ahora Juan se llama Pepito:

![image](https://user-images.githubusercontent.com/102741945/221731260-9e64c346-46e8-4f2e-a921-418a555a3755.png)
En la base también viene modificado:

![image](https://user-images.githubusercontent.com/102741945/221731328-765658aa-59cc-47c9-be1c-f7b511eb5766.png)

Ahora borramos a Pepito y ya no aparece:

![image](https://user-images.githubusercontent.com/102741945/221731413-e369294e-b460-425d-9bc6-2d9b78fa1abd.png)

 Y en la base de datos desaparece:
 
 ![image](https://user-images.githubusercontent.com/102741945/221731504-9ef73375-4395-4d6b-b589-ea38687272f4.png)

Mensajes también es igual, apareciendo los correos y el texto del corre:

![image](https://user-images.githubusercontent.com/102741945/221731618-a63505f1-9543-4b47-9196-80e0a69f3fb6.png)

![image](https://user-images.githubusercontent.com/102741945/221731687-ae8d48ec-9b4e-46aa-a5ea-9c0323b478e7.png)

No hemos incluido edit porque intuimos que cuando un mensaje ya se ha enviado no se puede modificar:
Pero si podemos borrarlo, primero observemos la base de datos:


![image](https://user-images.githubusercontent.com/102741945/221731881-58dca05e-39dd-4966-a13f-00c29ee8c46a.png)

Y ahora borramos:
![image](https://user-images.githubusercontent.com/102741945/221731972-651e8ad9-8a58-4c01-a8a4-77bf6ec19baa.png)







--------------------------------------------------------------------------------------------------------
                           PROBLEMAS CON LOS QUE NOS HEMOS ENCONTRADO:  
--------------------------------------------------------------------------------------------------------

Tras presentarse un sin fin de problemas, optamos por cambiar de IDE, usando ahora IntelliJ. Para resolver los problemas básicos hemos desarollado un par de videos a modo de tutorial que ayudan a comprender como trabajar en Github y en el IDE a la vez, así como trabajar con las bases de datos en IntelliJ:

- https://urjc-my.sharepoint.com/:v:/g/personal/r_arranz_2020_alumnos_urjc_es/EdWWhKa94VRPol5vn5-p3VQBsnoUs1gdhqfiRvCenq2hpg?e=cOHJfh
- https://urjc-my.sharepoint.com/:v:/g/personal/r_arranz_2020_alumnos_urjc_es/ESc4gWhVX3VIus1w0HLsv8wBsZRGA36lfbRqUUoZQ3jibQ?e=DEdCfc

Posteriormente nos encontramos con problemas de versiones, siendo el más destacable el uso continuo de RestController en vez de Controller.
Problemas de compresión de funcionamiento de BBDD, al principio creamos en el Workbech manualmente las tablas, lo cual es erroneo, al igual que también nos generó errores que teniamos una contraseña de 4 dígitos. Esto se solucionó creando otro user en el mysql con otra contraseña y conectandola bien en el código para que las tablas se crearan solas sin tener que meterlas manualmente.
No nos ejecutaba el proyecto debido a que las dependencias estaban mal especificadas y no habiamos incluido los mapped by.
Los setId nos generaban problemas con mustache, aparecía como si algún objeto estuviese usando el setId y no era así.
Mustache no coge bien los nombres de los atributos de cada entidad y a la hora de ponerlos en los html mustache de editar nos daba el error de que no encontraba el atributo, hemos conseguido que la mayoria de atributos los encuentre (probando con minusculas, mayúsculas,etc).


# Fase 3: Implementación de Seguridad

Para la implementación de la seguridad nos hemos puesto los siguientes objetivos a cumplir:

- Añadir las funciones de login (con su respectivo html de error) y logout
- Establecer que pantallas son públicas y cuáles privadas
Hemos incluido una carpeta llamada security para incluir todos los archivos de seguridad, uno de ellos es WebSecurityConfig en el cual indicamos que pantallas y que opciones son publicas y privadas con el permitAll() (publicas) y hasAnyRole( indica que son privadas y luego dentro de que sean privadas dependiendo de que rol se tenga permite realizar unas cosas u otras).
- Convertir nuestra aplicación en https
En primer lugar se ha creado un certificado con keytool, para ello en el cmd se ha incluido lo siguiente:
![image](https://user-images.githubusercontent.com/102741945/225415225-0d6a1ab8-8e8f-459b-a05f-4633b5453560.png)
Esto lo que hace es crearte la certificación y un fichero keystore.jks que contiene el certificado autofirmado generado con la herramienta del JDK keytool.
Se incluye en el application.properties la contraseña del certificado, la dirección del keystore y el puerto, que en este caso será el 8443.
Cuando abrimos la página web verificamos que se ha creado correctamente el certificado:

![image](https://user-images.githubusercontent.com/102741945/225416252-457633ef-8860-4c27-8ae3-582f229f430d.png)

- El diagrama de clases y tamplates resultantes es: 

![main](https://user-images.githubusercontent.com/102540777/225600693-e444cebb-ff30-47f6-b6f8-5b8edbfe3d0b.png)

- Implementar el servicio interno funcional en un proceso separado
- Elegir e implementar el mecanismo de comunicación
- Desplegar el proyecto en una máquina virtual mediante el empaquetado jar
Resumen de lo que hay que hacer con openstack
/////////Preparación de claves///////////////////////
1.	Acceder a https://clea.etsii.urjc.es/horizon.
2.	Accedemos con las credenciales que nos ha pasado el profe.
3.	Limite de 8 máquinas virtuales levantadas.
4.	Acceder desde el myapps, no desde casa.
5.	2 ips flotantes como máximo en el proyecto.
6.	Compute toda la configuración de las máquinas y las claves sh para conectarme(no se puede usar la de usuario y contraseña) hay que crear una clave pública y privada(clave asimétrica) la clave privada es para conectarse a la máquina.
7.	Creamos un par de claves en keyPairs, le damos a créate Key Pair, el nombre que sea y clave ssh, le doy a crear.
8.	Me sale un cuadro de dialogo para descargarlo(no perderlo porque sino no podemos acceder). Lo metemos en la carpeta .ssh.
////////// Instancias y grupos de seguridad////////////////

1.	El objetivo es crear una instancia y asignarla a las claves.
2.	Vamos a instances, pide los credenciales, vamos a launch instance,ponemos un instance name le damos a next(no hay que rellenar nada más ahí), elegimos una imagen(elegimos Ubuntu 2204 en la flechita para arriba), cambiamos el tamaño (volumen) ponemos un mínimo de 8gigas, le damos a siguiente, elegimos ahora la c04 la cual es suficiente para ejecutar. La red aparece directamente seleccionada, vamos a siguiente, otra vez a siguiente, hasta security groups, dejamos el default, next, seleccionamos la clave creada (la publica la inyecta dentro de la instancia), ya podemos lanzar la instancia.
3.	Openstack prepara la instancia(con una ip privada), puede fallar si tienes muchos volúmenes(así que cuidado porque si se crean volúmenes de más hay que borrarlos).
4.	Para acceder a la máquina tengo que usar una de las ips flotantes.
5.	En nuestra instancia creada, a la derecha en la flechita pinchamos en ella y después en asociar una ip flotante,, en ipaddress le damos al más, le doy una descripción(el mismo nombre de la máquina), le digo que la genere y aparece directamente la ip generada, indico a que instancia la quiero asociar(port to be associated), elijo la que quiero y le doy a associate.
6.	Abrimos la terminal, nos movemos a la carpeta .ssh donde tenemos nuestra clave privada, escribimos en la terminal una vez estamos en la dirección .ssh, ssh -i y el paquete donde está la clave.pem Ubuntu@(la ip flotante que se acaba de crear), nos dice si nos fiamos, le decimos que si y ya me mete dentro de la máquina, LO PRIMERO QUE HAY QUE HACER ES ACTUALIZARRRR, así que hacemos un sudo apt update.
7.	Sudo apt install nginx, le decimos que si a continuar y me lo instala. Para verificar que esta instalado, hacemos curl localhost. Me devuelve un html y me dice welcome. Vamos al navegador copiamos la ip flotante y se queda pensando(si esto pasa, significa que no tenemos abierto el puerto 80), así que lo paramos, vamos al openstack vamos a la instancia de nuestra máquina, le damos  ala flechita para abajo y vamos a editar security groups, aparece el default, así que vamos ahora a network que está a la izquierda debajo de computer y pinchamos en security groups vamos a default y a la derecha pinchamos en manager rules, vemos que aparece el peurto 22 pero no el 80. Volvemos para atrás donde security group y le damos a créate security group, lo llamamos puerto80 lo creamos,le damos a add rule, en el despleglable en rule seleccionamos (http), le damos al botón azul y ya estaría.
8.	 Vamos a compute a instances, vamos a nuestra instancia le damos  a la flechita para abajo y le damos a edit security groups, donde pone puerto 80 le doy al más y se me emte el puerto 80 como otro security group.
9.	Volvemos a hacer lo del ip flotante en el buscador y ya me manda el welcome.
////////////////////////Acceso desde myapss////////////////////////
1.	Las ips flotantes solo nos funcionan en el myapps
2.	Nos metemos en el escritorio Ubuntu.
3.	Necesito meter mi clave privado .ssh en la unidad R, creamos una carpeta ssh y dentro subimos la clave privada.
4.	En el Ubuntu del myapps abrimos la consola, hacemos ssh -i /media/Unidad_R_Documentos_MyApps/.ssh/(el nombre de la clave privada) ubuntu@(ip flotante todo junto con el ubuntu) le doy enter, le decimos que si y ya estaríamos dentro.

/////////Ejecutar aplicaciones web java////////////////
1.	Generamos el jar correspondiente de la webAdopcion, lo abrimos en una temrinal dándole click derecho, ponemos scp -i ~/.ssh/openstack-etsii/(el nombre de la clave privada.pem) webAdopcion.jar ubuntu@(ip):/home/ubuntu
 
2.	Hacemos  
3.	Si hago ls -la debe de aparecer el jar.
4.	Nos dira algo del java, así que hay que instalarlo, hay que instalar al menos el 17
 
Así se instala.

5.	Hacemos java  
Y ya arrancaría.
6.	Ponemos la Ip flotante y nos pasa lo mismo que antes no se puede conectar, vamos a security groups de nuevo, creamos un security group especifico para el sprint, lo llamamos web sprint le damos a add rule ponemos custum tc rule y en port ponemos 8080 y le damos a add. Vamos a la instancia le damos a la flecha, a esitar security groups, eliminamos el pueto 80 y añado el sprint.

7.	Cargamos de nuevo en el buscador el puerto, y ya nos abre.


- Opcionalmente aplicar seguridad CSRF




