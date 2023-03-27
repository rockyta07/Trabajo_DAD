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
* Diseñar un diagrama UML con las clases y funciones a implementar.
* Diseñar un esquema de la base de datos básica.
* Planificación de las plantillas de la web.
* Recolecta de imágenes sin copyright para mostrarlas en la web.

INVESTIGACIÓN:
* Investigar el funcionamiento de MySQL y la implementación con entidades en Java.
* Investigar el funcionamiento de Mustache como creador de plantillas html.
* Estudiar la implementación general de la BBDD + HTML.

IMPLEMENTACIONES:
* Implementación de la BBDD diseñada.
* Implementación de Mustache y las plantillas de la web.
* Pruebas y debugging de la implementación inicial para evaluar los errores y corregirlos en las siguientes versiones.

https://github.com/rockyta07/Trabajo_DAD/commit/fe3044662324d72fdb1b92f5c464ea95bbb6b181

Breve descripción del proyecto

## Tabla de contenidos

- [Login y Logout](#login-y-logout)
- [Pantallas privadas y públicas](#pantallas-privadas-y-públicas)
- [Https](#https)
- [Diagrama de clases y templates](#diagrama-de-clases-y-templates)
- [Implementar un servicio interno funcional](#implementar-un-servicio-interno-funcional)
- [CSRF](#csrf)
- [Elegir e implementar mecanismos de comunicación](#elegir-e-implementar-mecanismos-de-comunicación)

## Login y Logout

Breve descripción del proceso de inicio de sesión y cierre de sesión.

## Pantallas privadas y públicas

Explicación de las pantallas o secciones del proyecto que están disponibles públicamente y las que requieren inicio de sesión.

## Https

Explicación de cómo se implementa HTTPS en el proyecto y por qué es importante.

## Diagrama de clases y templates

Descripción de la arquitectura del proyecto y ejemplos de templates utilizados.

## Implementar un servicio interno funcional

Breve descripción de cómo se implementa un servicio interno y su funcionalidad.

## CSRF

Explicación de cómo se protege el proyecto contra ataques CSRF.

## Elegir e implementar mecanismos de comunicación
Para la implementación de la seguridad nos hemos puesto los siguientes objetivos a cumplir:
