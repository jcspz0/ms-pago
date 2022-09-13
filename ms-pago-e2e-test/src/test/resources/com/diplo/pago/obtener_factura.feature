@tag
Feature: obtener una factura de una reserva pagada en su totalidad
  Se requiere obtener la factura de una reserva que esta pagada en su totalidad

  @tag1
  Scenario Outline: como operador del sistema quiero obtener una factura de una reserva
    Given dada la reserva "<reserva_id>" y los datos de facturacion con detalle "<detalle>" y nit <nit>
    When obtener la deuda de la reserva a facturar
    And generar la factura
    Then Se obtiene una respuesta con codigo <codigo_resp> por la obtencion de la factura

    Examples: 
      | reserva_id                           | detalle          | nit     | codigo_resp |
      | f55eb2a1-8b4d-45de-9bc8-c052c44e881a | detalle del pago | 4731768 |         200 |
