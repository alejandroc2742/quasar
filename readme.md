# Operación Fuego de Quasar


Api Rest creada para realizar el calculo de la ubicación de la nave portacarga imperial, triangulando su posición respecto a tres satélites fijos 


## Contexto 🚀


Han Solo ha sido recientemente nombrado General de la Alianza Rebelde y busca dar un gran golpe contra el Imperio Galáctico para reavivar la llama de la resistencia.
El servicio de inteligencia rebelde ha detectado un llamado de auxilio de una nave portacarga imperial a la deriva en un campo de asteroides. El manifiesto de la nave es ultra clasificado, pero se rumorea que transporta raciones y armamento para una legión entera.


### Requerimientos 📋


 * Java 1.8 o superior
 * maven


## Desafío 📢


Como jefe de comunicaciones rebelde, tu misión es crear un programa en Java que retorne la fuente y contenido del mensaje de auxilio. Para esto, cuentas con tres satélites que te permitirán triangular la posición, ¡pero cuidado! el mensaje puede no llegar completo a cada satélite debido al campo de asteroides frente a la nave.


### Instalación 🔧


1. Clonar repositorio
```
git clone https://github.com/alejandroc2742/quasar.git
```


2. Crear el jar desde el código fuente, ejecutando el siguiente comando, ubicado desde la carpeta raíz del proyecto.


```
mvn package
```
3. Opcionalmente se podrá ver el resultado de la ejecución de las pruebas unitarias ejecutando el comando.
```
mvn clean install
```
4. Por último al ejecutar
```
java -jar target/quasar-0.0.1-SNAPSHOT.jar
```
Se podrá ingresar a la url 
```
http://localhost:8080/actuator/health
```
Allí se podrá revisar el estado de salud del proyecto, la base de datos a la que se encuentra conectado e información importante como el espacio en disco.




## Implementación de Open Api ⚙️


El proyecto cuenta con integración con Open Api, se ha dejado con sus configuraciones por defecto para poder mostrar la capacidad de documentación del mismo, es decir que se pueden ver verbos que no necesariamente se encuentran implementados, como DELETE O PUT, se podrá acceder a través de 


```
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```
Una de las ventajas de la librería, genera la documentación correspondiente a los métodos de la Api, de forma automática permitiendo la entrega de los servicios mucho más rápido a los consumidores.




## ¿ Cómo probar ?  🔩


Se ha hosteado el servicio en google Cloud run, para ello, se ha creado una imagen dockerizada del proyecto y sé ha subido al registry encargado de desplegar los servicios, es de recordar que los servicios hosteados en el GCP cuentan con un tiempo de uso para entrar en estado idle, es decir que la primer vez que se consuman después de un periodo de inactividad pueden tardar un tiempo en responder.


```
https://quasar-t42t4ngvhq-uc.a.run.app/
```


## Uri's


Bien pueden ser consultadas en la url indicada 


```
https://quasar-t42t4ngvhq-uc.a.run.app/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```


Se adjuntan al documento para su facilidad de uso


* POST 
```
https://quasar-t42t4ngvhq-uc.a.run.app/topsecret 
```
Devuelve la posición de la nave y reconstruye el mensaje recibido en las tres naves en caso de contar con la información necesaria.


* POST 
```
https://quasar-t42t4ngvhq-uc.a.run.app/topsecret_split
```
Guarda satélites en base de datos, retorna el correspondiente DTO a la entidad guardada en caso de ser exitoso.


* GET 
```
https://quasar-t42t4n/topsecret_split
```
Recupera la posición y mensaje del método anterior, en el caso de contar con la información necesaria, realiza la validación de que los satélites, sato, kenobi y skywalker hayan sido persistidos con anterioridad, para poder usar las coordenadas almacenadas y determinar la ubicación del emisor del mensaje.





## Autor ✒️


* **Luis Alejandro Céspedes** - *Líder de la Resistencia* - [alejandroc2742](https://github.com/alejandroc2742/)

