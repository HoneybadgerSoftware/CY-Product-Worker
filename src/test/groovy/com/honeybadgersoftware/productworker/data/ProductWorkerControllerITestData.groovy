package com.honeybadgersoftware.productworker.data

class ProductWorkerControllerITestData {

    def static CHECK_PRODUCTS_REQUEST = '''
{
  "products": [
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

    def static CHECK_PRODUCTS_RESPONSE =
            '''
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

}
