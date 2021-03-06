# Operaci贸n Fuego de Quasar


Api Rest creada para realizar el calculo de la ubicaci贸n de la nave portacarga imperial, triangulando su posici贸n respecto a tres sat茅lites fijos 


## Contexto 馃殌


Han Solo ha sido recientemente nombrado General de la Alianza Rebelde y busca dar un gran golpe contra el Imperio Gal谩ctico para reavivar la llama de la resistencia.
El servicio de inteligencia rebelde ha detectado un llamado de auxilio de una nave portacarga imperial a la deriva en un campo de asteroides. El manifiesto de la nave es ultra clasificado, pero se rumorea que transporta raciones y armamento para una legi贸n entera.


### Requerimientos 馃搵


 * Java 1.8 o superior
 * maven


## Desaf铆o 馃摙


Como jefe de comunicaciones rebelde, tu misi贸n es crear un programa en Java que retorne la fuente y contenido del mensaje de auxilio. Para esto, cuentas con tres sat茅lites que te permitir谩n triangular la posici贸n, 隆pero cuidado! el mensaje puede no llegar completo a cada sat茅lite debido al campo de asteroides frente a la nave.


### Instalaci贸n 馃敡


1. Clonar repositorio
```
git clone https://github.com/alejandroc2742/quasar.git
```


2. Crear el jar desde el c贸digo fuente, ejecutando el siguiente comando, ubicado desde la carpeta ra铆z del proyecto.


```
mvn package
```
3. Opcionalmente se podr谩 ver el resultado de la ejecuci贸n de las pruebas unitarias ejecutando el comando.
```
mvn clean install
```
4. Por 煤ltimo al ejecutar
```
java -jar target/quasar-0.0.1-SNAPSHOT.jar
```
Se podr谩 ingresar a la url 
```
http://localhost:8080/actuator/health
```
All铆 se podr谩 revisar el estado de salud del proyecto, la base de datos a la que se encuentra conectado e informaci贸n importante como el espacio en disco.




## Implementaci贸n de Open Api 鈿欙笍


El proyecto cuenta con integraci贸n con Open Api, se ha dejado con sus configuraciones por defecto para poder mostrar la capacidad de documentaci贸n del mismo, es decir que se pueden ver verbos que no necesariamente se encuentran implementados, como DELETE O PUT, se podr谩 acceder a trav茅s de 


```
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```
Una de las ventajas de la librer铆a, genera la documentaci贸n correspondiente a los m茅todos de la Api, de forma autom谩tica permitiendo la entrega de los servicios mucho m谩s r谩pido a los consumidores.




## 驴 C贸mo probar ?  馃敥


Se ha hosteado el servicio en google Cloud run, para ello, se ha creado una imagen dockerizada del proyecto y s茅 ha subido al registry encargado de desplegar los servicios, es de recordar que los servicios hosteados en el GCP cuentan con un tiempo de uso para entrar en estado idle, es decir que la primer vez que se consuman despu茅s de un periodo de inactividad pueden tardar un tiempo en responder.


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
Devuelve la posici贸n de la nave y reconstruye el mensaje recibido en las tres naves en caso de contar con la informaci贸n necesaria.


* POST 
```
https://quasar-t42t4ngvhq-uc.a.run.app/topsecret_split
```
Guarda sat茅lites en base de datos, retorna el correspondiente DTO a la entidad guardada en caso de ser exitoso.


* GET 
```
https://quasar-t42t4ngvhq-uc.a.run.app/topsecret_split
```
Recupera la posici贸n y mensaje del m茅todo anterior, en el caso de contar con la informaci贸n necesaria, realiza la validaci贸n de que los sat茅lites, sato, kenobi y skywalker hayan sido persistidos con anterioridad, para poder usar las coordenadas almacenadas y determinar la ubicaci贸n del emisor del mensaje.





## Autor 鉁掞笍


* **Luis Alejandro C茅spedes** - *L铆der de la Resistencia* - [alejandroc2742](https://github.com/alejandroc2742/)

