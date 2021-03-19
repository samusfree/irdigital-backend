# IRDigitalBackend
Intercorp Retail Digital Backend Challenge

### Microservice developed in Java with Spring boot
####Stack
Java 11

Spring Boot 2.43

Lombok

Gradle

Spring Data JPA

Hibernate 5.0

PostgressQL on Heroku

Heroku PAAS

### API Rest documentada en Swagger
* [Link Swagger-ui](http://samus-irdigital.us-west-2.elasticbeanstalk.com/swagger-ui.html)
* [Link Api-docs](http://samus-irdigital.us-west-2.elasticbeanstalk.com/v2/api-docs)

### Postman de prueba 
* [Colección a importar](https://www.getpostman.com/collections/5f0ad835369b9e37633a)
	
### Script Base de datos desplegado en RDS Mysql
```
CREATE TABLE intercorpreto.cliente (
	id INTEGER NOT NULL AUTO_INCREMENT,
	nombre varchar(300) NOT NULL,
	apellido varchar(300) NOT NULL,
	edad INTEGER NOT NULL,
	fecha_nacimiento DATE NOT NULL,
	CONSTRAINT cliente_PK PRIMARY KEY (id)
);
```

### Repositorio de Config Server
* [Repositorio Github config server](https://github.com/samusfree/samusirdigitalconfig)

## Servicios Rest

### Endpoint de creación Cliente POST 

#### URL
POST http://samus-irdigital.us-west-2.elasticbeanstalk.com/cliente/

#### json de entrada de ejemplo
```
{
  "apellido": "Gonzales",
  "edad": 30,
  "fechaNacimiento": "07/10/1989",
  "nombre": "Samuel"
}
```


#### json de salida de ejemplo
```
{
  "apellido": "Gonzales",
  "edad": 30,
  "fechaNacimiento": "07/10/1989",
  "fechaPosibleDeceso": "07/10/2073",
  "id": 1,
  "nombre": "Samuel"
}
```

### Endpoint de Listado de clientes 

#### URL
GET http://samus-irdigital.us-west-2.elasticbeanstalk.com/cliente/

#### json de entrada de ejemplo
No aplica

#### json de salida de ejemplo
```
[
  {
    "id": 4,
    "nombre": "Samuel",
    "apellido": "Gonzales",
    "edad": 30,
    "fechaNacimiento": "07/10/1989",
    "fechaPosibleDeceso": "07/10/2063"
  },
  {
    "id": 5,
    "nombre": "Cliente",
    "apellido": "Prueba",
    "edad": 27,
    "fechaNacimiento": "05/10/1992",
    "fechaPosibleDeceso": "05/10/2066"
  }
]
```

### Endpoint de KPI de clientes 

#### URL
GET http://samus-irdigital.us-west-2.elasticbeanstalk.com/cliente/kpi

#### json de entrada de ejemplo
No aplica

#### json de salida de ejemplo
```
{
  "promedioEdad": 28.5,
  "desviacionEstandar": 1.5
}
```
