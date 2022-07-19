@tag
Feature: Generar factura de una deuda PAGADA
  Se requiere generar la factura de una deuda PAGADA

  @tag1
  Scenario Outline: generar factura a partir del id de la deuda
    Given El id de la deuda a facturar es "<deuda_id>", el detalle es "<detalle>" y el nit es <nit>
    When generar factura
    Then Se genero codigo de una factura
    And El codigo de respuesta al generar factura es <codigo_resp>

    Examples: 
      | deuda_id                             | detalle            | nit   | codigo_resp |
      | d629e6f4-4bb6-47e9-9f0e-3341b92f6cf5 | detalle de factura | 48451 |         200 |
