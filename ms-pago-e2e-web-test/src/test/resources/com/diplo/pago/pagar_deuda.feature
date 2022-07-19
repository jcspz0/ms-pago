@tag
Feature: Realizar pago de una reserva
  Se requiere realizar el pago de una reserva

  @tag1
  Scenario Outline: como operador del sistema quiero pagar una reserva por un monto 
    Given dada la reserva "<reserva_id>" con monto de <total> se realiza el pago de <monto> con detalle "<detalle>" y nit <nit>
    When obtener la deuda de la reserva
    And realizar el pago
    Then Se obtiene una respuesta con codigo <codigo_resp> por el pago

    Examples: 
      | reserva_id                           | total | monto | detalle          | nit     | codigo_resp |
      | 04a3f9d7-68ab-404f-b3e3-850e88f5c711 |   1000 | 1 |detalle del pago | 4731768 |         200 |
