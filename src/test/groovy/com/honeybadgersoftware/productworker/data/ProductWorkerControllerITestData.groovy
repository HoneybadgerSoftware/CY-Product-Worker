package com.honeybadgersoftware.productworker.data

class ProductWorkerControllerITestData {

    def static CHECK_PRODUCTS_REQUEST = '''
{
  "data": [
    {
      "productName": "Fiji water 1lt",
      "manufacturer": "XYZ Corp"
    },
    {
      "productName": "testProduct 1kg",
      "manufacturer": "ABC Inc"
    }
  ]
}
'''

    def static CHECK_PRODUCTS_RESPONSE = '''
{
  "data": [
    {
      "id": 12345678,
      "existsInDb": true,
      "name": "Fiji water 1lt",
      "manufacturer": "XYZ Corp"
    },
    {
      "id": 87654321,
      "existsInDb": false,
      "name": "testProduct 1kg",
      "manufacturer": "ABC Inc"
    }
  ]
}
'''

    def static NEW_PRODUCTS_REQUEST = '''
{
  "data" : [ 
    {
      "id" : 12345678,
      "averagePrice" : 19.99,
      "description" : "example description",
      "url" : "http://example.com/test.jpg"
    },
    {
      "id" : 87654321,
      "averagePrice" : 99.99,
      "description" : "test description",
      "url" : "http://example.com/test2.jpg"
    }
  ]
}
'''

    def static AVAILABILITY_UPDATE_REQUEST = '''
{
  "shopId": 123,
  "existingProductsData": [
    {
      "id": 12345678,
      "price": 19.99
    }
  ],
  "newProductsData": [
    {
      "id": 12345678,
      "price": 19.99
    },
    {
      "id": 87654321,
      "price": 99.99
    }
  ]
}
'''

}
