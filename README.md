# pruevaAvla
api prueba avla
esta es una api de prueba de usuarios, la cual tiene una base de datos posgresql, para levantar el proyecto hay que crear una base de datos sin tablas con el scipt src\main\resources\sql ubicado en llamado crear base de datos.sql despues de crear esta base de datos en cualquier gestor de base de datos que soporte postgesql, una vez que se haga el refresco de las dependencias de gradle(en caso de ser nescesario), iniciar la aplicacion como spring boot app desde eclipse(por comandos gradlew bootRun -Dserver.port=808 o desde eclipse utizando run as - spring boot app) en la primera ejecucion del proyecto generara automaticamente las tablas deacuerdo a las entidades JPA configuradas en el proyecto.
hay 2 maneras de ver las solicitudes:
a travez de sawgger entrando desde la raiz de la api(redirecciona automaticamente al swagger):
http://localhost:8080/api-usuarios-avla/
a tavez de postman(para poder ver de manera mas facil los registros se hicieron 2 apis que listan los usuarios y otra que los ve por id):
POST http://localhost:8080/api-usuarios-avla/usuario/agregar

PUT http://localhost:8080/api-usuarios-avla/usuario/actualizar

GET http://localhost:8080/api-usuarios-avla/usuario/listar-usuarios

GET http://localhost:8080/api-usuarios-avla/usuario/encontrar-usuario/{id}

ejemplos de request con parametros(el que lista los usuarios no nescesita parametros)

POST http://localhost:8080/api-usuarios-avla/usuario/agregar
(no nescesita id's usuarios ni de los telefonos para crearlos ya que lo autogenera):
{ 
"name": "Rodrigo Paredes", 
"email": "rodrigo.paredes123@email.com", 
"password": "Password12", 
"phones": [ 
{ 
"number": "987654321"
}, 
{ 
"number": "987654323"
} 
] 
} 

PUT http://localhost:8080/api-usuarios-avla/usuario/actualizar (nescesita los id's de los usuarios y de los telefonos asociados para saber cual actualizar):

{ 
"idUsuario":2,
"name": "Rodrigo Paredes", 
"email": "rodrigo.paredes123@email.com", 
"password": "PasswordUser12", 
"phones": [ 
{ 
"idTelefono":2,
"number": "987654321"
}, 
{ 
"idTelefono":3,
"number": "987654323"
} 
] 
} 


GET http://localhost:8080/api-usuarios-avla/usuario/encontrar-usuario/{id}:

GET http://localhost:8080/api-usuarios-avla/usuario/encontrar-usuario/1
