# Fluff&Paws
Marcia García de la Mata, Rocío Arranz Esteban y Daniel Corredor Padrino

![logoWeb](https://user-images.githubusercontent.com/102540777/222416233-702d5929-cbea-4585-b3a5-8ef92fbf3fa7.png)

Para organizarnos usaremos "Trello" al cuál se puede acceder desde el siguiente link:
https://trello.com/invite/b/fjavwWrE/ATTI966c72349a3704d1d675c1228d98b2338C41BE0D/organizacion-fluffpaws

## FASES DE LA PRÁCTICA

- [Fase 1: Declaración de Objetivos](#fase-1-declaracion-de-objetivos)
- [Fase 2: Implementación Funcional](#fase-2-implementacion-funcional)
- [Fase 3: Implementación de Seguridad](#fase-3-implementacion-de-seguridad)


## Fase 1: Declaración de Objetivos

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

## Fase 2: Implementación Funcional

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


## Fase 3: Implementación de Seguridad

## Tabla de contenidos

- [Login y Logout](#login-y-logout)
- [Pantallas privadas y públicas](#pantallas-privadas-y-públicas)
- [Https](#https)
- [Diagrama de clases y templates](#diagrama-de-clases-y-templates)
- [CSRF](#csrf)
- [Elegir e implementar mecanismos de comunicación](#elegir-e-implementar-mecanismos-de-comunicación)
- [Desplegar el proyecto en una máquina virtual](#desplegar-el-proyecto-en-una-máquina-virtual)
- [Commits de interés de cada uno](#commits-de-interés-de-cada-uno)
- [Navegación](#navegación)

## Login y Logout

Para la implementación de la seguridad nos hemos puesto los siguientes objetivos a cumplir:

- Añadir las funciones de login (con su respectivo html de error) y logout para usuarios ya existentes y para los que aún no existen, registrarse.

![readme](https://user-images.githubusercontent.com/102540777/227144118-ebfc3553-4148-469b-8b7e-9a899e08afee.png)


## Pantallas privadas y públicas

Para comenzar la separación de las vistas en nuestra página web diseñamos sobre el papel un sketch 
de cuales queríamos que fuesen accesibles para los usuarios no registrados y cuales no, separando a su vez las privadas entre las pantallas que serán 
exclusivamente para los usuarios "USER" y las que serán para los usuarios de tipo "ADMIN" (incluyendo también funcionalidades carácteristicas)

![Captura de pantalla 2023-03-23 013647](https://user-images.githubusercontent.com/102540777/227144443-72856284-ba4f-4988-b3e0-632c9f362c53.png)

Hemos incluido una carpeta llamada security para incluir todos los archivos de seguridad, uno de ellos es WebSecurityConfig en el cual indicamos que pantallas y que opciones son publicas y privadas con el permitAll() (publicas) y hasAnyRole( indica que son privadas y luego dentro de que sean privadas dependiendo de que rol se tenga permite realizar unas cosas u otras).

## Https

En primer lugar se ha creado un certificado con keytool, para ello en el cmd se ha incluido lo siguiente:
![image](https://user-images.githubusercontent.com/102741945/225415225-0d6a1ab8-8e8f-459b-a05f-4633b5453560.png)
Esto lo que hace es crearte la certificación y un fichero keystore.jks que contiene el certificado autofirmado generado con la herramienta del JDK keytool.
Se incluye en el application.properties la contraseña del certificado, la dirección del keystore y el puerto, que en este caso será el 8443.
Cuando abrimos la página web verificamos que se ha creado correctamente el certificado:

![image](https://user-images.githubusercontent.com/102741945/225416252-457633ef-8860-4c27-8ae3-582f229f430d.png)

## Diagrama de clases y templates

![main](https://user-images.githubusercontent.com/102540777/228085692-49cfce4f-b902-4f0a-b74e-a5ba2aa05c52.png)


## CSRF

En nuestra aplicación hacemos uso de tokens generados por un interceptor para protegernos minimamente de los ataques CSRF (Cross Site Request Forgery)
los cuales implementamos de la siguiente manera:

![readme2](https://user-images.githubusercontent.com/102540777/227144230-2ec8ef7d-6067-47c6-b44d-bb45a4c0c8b5.png)


La clase CSRFHandlerInterceptor es la implementación del interceptor que se ejecuta antes o después de una solicitud. 
El método postHandle en esta clase verifica si la solicitud incluye un objeto CsrfToken. 
Si es así, el método agrega el token al objeto ModelAndView que se utiliza para renderizar la vista, la cual puede usar el token para protegerse contra ataques CSRF.

## Elegir e implementar mecanismos de comunicación

Para implementar el servicio interno de nuestra página, inicialmente pensamos en usar webSocket pero enseguida nos vimos limitadas por el tiempo y la escasez de ejemplos para nuestra idea.
Nuestro servicio interno requeriría de una comunicación cliente-cliente si implementasemos la funcionalidad de chat en vivo (el cúal tampoco es en sí un sistema interno del tipo que se requería),
el cuál a su vez, nos presenta la limitación de que los mensajes no tienen permanencia. 
Todos los ejemplos que encontramos de websocket hacían referencia a comunicaciones del tipo servidor-servidor por lo que rápidamente desechamos la idea para posteriormente visualizar otra, 
un sistema interno que tras la adopción envíe al usuario un correo electrónico con el certificado de adopción, de esta manera conseguiríamos la comunicación servidor-servidor interna que buscábamos.

Para esta implementación usaremos API REST que, mientras no se habla de ella en el apartado de temario correspondiente a la comunicación de aplicaciones distribuidas, es muy útil para nuestra finalidad.
La implementación del sistema interno la diviremos en dos conexiones, la interacción de parte del usuario con nuestra aplicación principal y la interacción entre las máquinas.
El cliente interactuará mediante un botón con el cual se tomará su correo electrónico, posteriormente la página web procesará la petición de adopción creando un json que contenga los datos del correo
electrónico (remitente, asunto y cuerpo) así como los datos del animal a adoptar, el cuál será enviado al servidor API REST quien procesará esa petición y enviará el correo generando el certificado.
*Importante, cuando el servicio interno accede al gmail, para que pueda acceder sin dar ningún problema se debe de crear una contraseña de aplicación, para ello es necesario hacer la verificación de dos pasos.

Esquema de la comunicación

![image](https://user-images.githubusercontent.com/102741945/227959559-a73ee24d-1141-4c65-9e30-2e291a4b6215.png)

## Desplegar el proyecto en una máquina virtual

Resumen de lo que hay que hacer con openstack:

--------------------------------------------------------------------------------------------------------
                                            Preparación de claves
--------------------------------------------------------------------------------------------------------
Hay que crear una clave pública y privada (clave asimétrica) -> la clave privada es para conectarse a la máquina.

1.	Acceder a https://clea.etsii.urjc.es/horizon.
2.	Accedemos con las credenciales que nos ha pasado el profe.
3.  Creamos un par de claves en "keyPairs", le damos a "create Key Pair", un nombre cualquiera y clave ssh, le doy a crear.
4.	Me sale un cuadro de dialogo para descargarlo (no perderlo porque sino no podemos acceder) el cual guardaremos en una carpeta .ssh.

--------------------------------------------------------------------------------------------------------
                                         Instancias y grupos de seguridad
--------------------------------------------------------------------------------------------------------
El objetivo es crear una instancia y asignarla a las claves para esto seguiremos los siguientes pasos en OpenStack (habiendo ya iniciado sesión con las credenciales dadas):

1.	Creamos una nueva instancia en el apartado "Instances" mediante el botón "launch instance", establecemos un instance name le damos a next(no hay que rellenar nada más ahí), elegimos una imagen(elegimos Ubuntu 2204 en la flechita para arriba), cambiamos el tamaño (volumen) ponemos un mínimo de 8GB, le damos a siguiente. Posteriormente elegimos ahora la opción de configuración "c04", la cual es suficiente para ejecutar nuestra web. La red aparece directamente seleccionada, le damos a siguiente tantas veces como sea necesario hasta security groups, en donde dejaremos el default, next, seleccionamos la clave creada (la publica la inyecta dentro de la instancia), ya podemos lanzar la instancia.

Openstack prepara la instancia (con una ip privada), puede fallar si tienes muchos volúmenes(así que cuidado porque si se crean volúmenes de más hay que borrarlos). Para acceder a la máquina tengo que usar una de las IPs flotantes, para asignar una IP flotante:

2.	En nuestra instancia creada, a la derecha en la flechita pinchamos en ella y después a "asociar una ip flotante", en "IP address" le damos al más, le doy una descripción(el mismo nombre de la máquina), le digo que la genere y aparece directamente la IP generada, indico a que instancia la quiero asociar(port to be associated), elijo la que quiero y le doy a "associate".

3. Para ahora acceder al servidor haciendo uso de nuestras, abrimos la terminal, nos movemos a la carpeta .ssh donde tenemos nuestra clave privada y escribiremos en la terminal:

```
ssh -i ~pathToClaves/Clave.pem ubuntu@ipFlotante
```

Nos dice si nos fiamos, le decimos que si y ya me mete dentro de la máquina. Una vez estamos dentro, lo primero que se debe de hacer es actualizar los paquetes del servidor mediante:

```
sudo apt update
```

4.	Podemos probar que el servidor funciona y que se puede acceder a el haciendo lo siguiente:

```
sudo apt install nginx //Le decimos que si
curl localhost //Para verificar que esta instalado, hacemos curl localhost -> Me devuelve un html y me dice welcome. 
```

Vamos al navegador copiamos la IP flotante y se queda pensando (si esto pasa, significa que no tenemos abierto el puerto 80 que es el cual recibe las peticiones http), así que volvemos a openstack y vamos a "Network". Allí pinchamos en security groups y le damos a create security group, lo llamamos puerto80, lo creamos, le damos a "add rule", en el despleglable en rule seleccionamos (http), le damos al botón azul.

8.	 Vamos a compute a instances, vamos a nuestra instancia le damos a la flechita para abajo y le damos a edit security groups, donde pone puerto 80 le doy al más y se me emte el puerto 80 como otro security group, añadido esto el servidor debería ser capaz de recibir peticiones http.

9.	Volvemos a buscar la IP flotante en el buscador y ya me devuelve el welcome de test.

--------------------------------------------------------------------------------------------------------
                                              Acceso desde myapps
--------------------------------------------------------------------------------------------------------
Para acceder con el navegador al servidor haciendo uso de las ips flotantes debemos tener en cuenta que por una restricción del servidor, solo podremos acceder a él mediante el myapps. Los pasos a seguir en este caso son muy parecidos a los de arriba, con la pequeña puntualización de que la ruta de la clave pem tendrá más o menos este aspecto (dependiendo de nuevo de donde hayamos guardado inicialmente la carpeta .ssh):

```
ssh -i /media/Unidad_R_Documentos_MyApps/.ssh/(el nombre de la clave privada) ubuntu@(ip flotante todo junto con el ubuntu) 
```

Como recomendación, para evitar errores copiando el path a la clave, se podría abrir un terminal desde la misma carpeta en la que se encuentran las claves pem de manera que se pudiese hacer directamente: 

```
ssh -i Clave.pem ubuntu@(ip flotante todo junto con el ubuntu)
```

--------------------------------------------------------------------------------------------------------
                                             Despliegue de nuestra web
--------------------------------------------------------------------------------------------------------

REQUISITOS INICIALES:
* Java (JRE o JDK)  

    ![image](https://user-images.githubusercontent.com/102741945/226338290-c64cac20-496a-483c-a7af-22cb0534a7d4.png)
    
* Añadir puertos 8080 y 8443 a los grupos de seguridad 

    (vamos a security groups de nuevo, creamos un security group especifico para la Fase3, le damos a add rule ponemos "custom tc rule" y en port ponemos 8080 y le damos a add. Añadimos otra "custom tc rule" para el puerto 8443. Vamos a la instancia le damos a la flecha, a esitar security groups, eliminamos el pueto 80 y añado el que acabamos de crear).
    
* MySQL


Con los requisitos previos ya resueltos podemos empezar a subir el jar:

1.	Generamos el jar correspondiente de la webAdopcion, abrimos una terminal la carpeta en la que se encuentre dicho jar para mayor simplicidad dándole click derecho, ponemos 

```
scp -i ~/.ssh/pathToClavePem/(el nombre de la clave privada.pem) webAdopcion.jar ubuntu@(ipFlotante):/home/ubuntu
```

2.	Hacemos  
![image](https://user-images.githubusercontent.com/102741945/226338250-68c0d1e5-ed50-43e9-bae6-d96e241917c9.png)

4.	Si hago ls -la debe de aparecer el jar.
5.	Nos dira algo del java, así que hay que instalarlo, hay que instalar al menos el 17


Así se instala.

5.	Hacemos  

![image](https://user-images.githubusercontent.com/102741945/226338321-3a2fb857-e0c5-402d-9924-5a8b44200bdf.png)

Y ya arrancaría.
6.	Ponemos la Ip flotante y nos pasa lo mismo que antes no se puede conectar, vamos a security groups de nuevo, creamos un security group especifico para el sprint, lo llamamos web sprint le damos a add rule ponemos custum tc rule y en port ponemos 8080 y le damos a add. Vamos a la instancia le damos a la flecha, a esitar security groups, eliminamos el pueto 80 y añado el sprint.

7.	Cargamos de nuevo en el buscador el puerto, y ya nos abre.
Una vez realizados estos pasos ya podriamos ver nuestra página web a través de la máquina virtual.

--------------------------------------------------------------------------------------------------------
                              Pasos para iniciar la página web una vez creada la máquina virtual
--------------------------------------------------------------------------------------------------------
1.Si estas en casa abrir myApps, si estás en la red de la univesidad no es necesario.

2.Ve a la carpeta ssh y abre una terminal desde esa carpeta(Asi nos ahorramos trabajo).

3.Escribe en la terminal: ssh -i Claves.pem ubuntu@10.100.139.69 y pulsa enter (Esto te llevará a la máquina virtual creada en openStack).

4.Ahora directamente ejecutamos ambos jar(el de la web y el del servidor interno) importante hacerlo desde dos terminales distintas porque sino dará error de acceso, para ejecutarlo ponemos en la terminal: java -jar webAdopcion (el nombre se puede autocompletar con la tabulación) y pulsamos enter.

5.Ahora ya podemos ir a google y poner 10.100.139.69:8443 y se abriría nuesta página web.

## Commits de interés de cada uno


Marcia García De La Mata Pinto

-Login controller+ security : Aqui se muestra el loginwebController->

https://github.com/rockyta07/Trabajo_DAD/commit/30447fc84d8e2afe91702f96f86db8e8d0b9814b

-addsecurity files : usuario con su contraseña cifrada, CSRFHandlerConfiguration, RepositoryUserDetailsService, webSecurityConfig. ->

https://github.com/rockyta07/Trabajo_DAD/commit/f0829d246711dabceedbc80b94bf206100f6239e

-addPasswordencoded: en datainit ->

https://github.com/rockyta07/Trabajo_DAD/commit/1c6fd491cde91ee64e00290e3ba142d86b992161

-creacion de certificado con keytool: Creación del certificado(keystore.jks), añadidos aplication properties. ->

https://github.com/rockyta07/Trabajo_DAD/commit/d4ab55daad76c314d45e4a401d60e40fc19582ed

-Añadida privacidad + modificacionesparalogin: completar la privacidad de las paginas en websecurityconfig. ->

https://github.com/rockyta07/Trabajo_DAD/commit/087d70d094c6f5ae332a17275981fcb470254f18

-login funcionando: modificaciones que hicieron que el login funcionase. ->

https://github.com/rockyta07/Trabajo_DAD/commit/44a153fd5d889803f33e1b798f1cf40a36139366

-Solucionado registro(encriptado) + add correoService(para el servicio interno). ->

https://github.com/rockyta07/Trabajo_DAD/commit/1f8567558a38f544bd7c881f67dcc1f495eda81a

-API Rest funcionando: Modificaciones para que funcione bien API REST ->

https://github.com/rockyta07/Trabajo_DAD/commit/3e50293453a3992191a5adb054605306666a926e

-Servicio interno funcionando: modificaciones para que funcione bien toda la comunicación con servicio interno. ->

https://github.com/rockyta07/Trabajo_DAD/commit/fe3044662324d72fdb1b92f5c464ea95bbb6b181

-boton de borrado solo admin(solucinado): lo relevante de este commit es añadir las variables logged, name y admin en cada controllador para poder usarlo en los html y así poder privatizar las páginas. ->

https://github.com/rockyta07/Trabajo_DAD/commit/7123212cb36d9c071dcc28dea1b1722c0bd19efc

-Modificacion mensajes + se muestra certificado entero: modificacion para que solo pueda crear mensaje el user y que al comunicarse con el servicio interno este le muestre el certificado entero de adopcion. ->

https://github.com/rockyta07/Trabajo_DAD/commit/efb483f9a8d909ab81d76280025e719ad84e34be

*Más commits de solución de cosas pequeñas, como privacidad de algún botón,funcionamiento, añadidos,etc.


## Navegación

En la página inicial nos encontramos las protectoras con su información principal.

![image](https://user-images.githubusercontent.com/102741945/228020594-6c2a325b-97f5-495a-ae3c-4b8dd8e34aa7.png)

Como no ha iniciado sesión nadie lo unico visible van a ser las protectoras, los animales de cada protectora, el inicio de sesión y el registro.

![image](https://user-images.githubusercontent.com/102741945/228020660-0b602ce3-5bec-4c93-84b5-c1cf2580f406.png)

![image](https://user-images.githubusercontent.com/102741945/228020736-2b1bab91-c6d8-4803-b4eb-cddc1e3d3084.png)

![image](https://user-images.githubusercontent.com/102741945/228020768-fc10e158-e212-4838-a822-6e4b7ee30bbd.png)

Nos vamos a la pantalla de inicio de sesión, tenemos guardados en la base de datos el user(Juan con pass "juan123") ,el user (User con pass "pass") y el admin (admin con pass "adminpass") primeramente ingresaremos con un user.

Al iniciar sesión podemos observar el nombre del usuario en el menú, cerrar sesión y podemos ver botones que sin iniciar sesión no veíamos, por ejemplo cuando pinchamos en un animal nos da la opción de adoptarlo:

![image](https://user-images.githubusercontent.com/102741945/228021560-f5d7b427-9f49-4693-911a-9e1a6b1db865.png)

Ahora nos aparece el apartado de mensajes mandados que si pinchamos en el vemos los mansajes que ha mandado el usuario y tiene la opción de crear nuevos mensajes:

![image](https://user-images.githubusercontent.com/102741945/228022091-326f499d-d148-495d-a048-d7df1e115289.png)

Si escribimos un mensaje nuevo:

![image](https://user-images.githubusercontent.com/102741945/228022486-c0f490b0-e769-4094-aa0f-d61558369f9f.png)

Aparecerá en la lista de mensajes mandados:

![image](https://user-images.githubusercontent.com/102741945/228022618-7a263a64-5994-4cc3-bfc2-d7db17bd36bd.png)

Cuando un usuario pincha en el botón de adoptar, nos redirige a una pantalla con los datos generales de la protectora a la cual pertenece ese animal, tiene un recuadro de mensaje:

![image](https://user-images.githubusercontent.com/102741945/228023049-cc5e5469-3f73-4686-833e-7ada58f1ad79.png)

Cuando pinchamos en enviar, esta solicitud la recibe el servicio interno el cual procesa la solicitud y manda un mensaje( desde el correo fluffandpaws) al correo indicado, con el certificado del animal que queremos adoptar:

![image](https://user-images.githubusercontent.com/102741945/228023921-0a4fadd2-b395-4bc7-a8a8-ff36fa3505a2.png)

Ahora entramos con un administrador:

![image](https://user-images.githubusercontent.com/102741945/228024085-230fe841-18db-4336-b4f6-a983e920e2b0.png)

Como podemos observar ya hay más botones disponibles, los cuales el usuario no podía ver, por ejemplo el de crear protectora.
Dentro de la protectora también tenemos más opciones como admin:

![image](https://user-images.githubusercontent.com/102741945/228024298-172a75a1-588b-4d3c-9bae-6585af3ff635.png)

En el apartado de animales pasa lo mismo:

![image](https://user-images.githubusercontent.com/102741945/228024400-e1780c62-4cb9-4df1-b061-d1a327e65c69.png)

![image](https://user-images.githubusercontent.com/102741945/228024438-d83be82c-efd0-43e5-8d94-cb1e39d09bcd.png)

El admin es el único que puede ver el apartado de usuarios con todos los usuarios que están registrados:

![image](https://user-images.githubusercontent.com/102741945/228024567-32d7d0f6-4cc1-4859-a00f-d79cc268c771.png)

Si pincha en un usuario puede visualizar toda la información

![image](https://user-images.githubusercontent.com/102741945/228024727-b964ba37-4b67-4549-bb80-ab752de178cc.png)

Por último vamos a la sección de registro, registrando por ejemplo a un usuario llamado Manolo:

![image](https://user-images.githubusercontent.com/102741945/228025292-a889c046-dcb9-4888-a08a-c6bba0c989d2.png)

Una vez registrado:

![image](https://user-images.githubusercontent.com/102741945/228025376-40f04965-4f1b-4381-afb3-109ca9a3f001.png)

Ahora en usuario visualizamos a Manolo:

![image](https://user-images.githubusercontent.com/102741945/228025488-1336cf99-fad4-4286-b589-ad720d435e56.png)
















