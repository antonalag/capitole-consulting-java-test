# Capitole consulting - Proceso de selección - Test java 2020

Para la realización del endpoint que se demanda, se ha decidido utilizar la petición HTTP GET. Los parámetros que se necesitan para obtener los datos de la base de datos se envían como parte de la URL de la petición.

Ejemplo de petición al endpoint:

**http://localhost:8080/product-price?date=2020-06-30T14:00:00&productId=35455&brandId=1**

Ejemplo resultado de la petición al endpoint:

**{
    "productId": 35455,
    "brandId": 1,
    "pricelist": 4,
    "startDate": "2020-06-15T16:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 38.95
}**
