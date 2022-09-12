@tag
Feature: Realizar pago
  Se requiere realizar un pago a una deuda

  @tag1
  Scenario Outline: realizar pago
    Given dada la deuda "<deuda_id>" se requiere realizar el pago de <monto> por concepto de "<detalle>"
    When realizar el pago con los datos proporcionados
    Then Se obtiene una respuesta con codigo <codigo_resp> por el pago realizado

    Examples: 
      | deuda_id                             | monto | detalle        | codigo_resp |
      | 00546c9d-1ae7-45c0-9498-65b5c6a3d269 |     1 | pago de prueba |         200 |
