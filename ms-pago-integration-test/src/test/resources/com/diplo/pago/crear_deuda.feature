@tag
Feature: Crear deuda
  Se requiere crear una deuda a partir de una reserva para que se pueda realizar los pagos

  @tag1
  Scenario Outline: crear deuda
    Given dada la reserva "<reserva_id>" con un monto total de <total>
    When Crear la deuda con los datos proporcionados
    Then Se obtiene una respuesta con codigo <codigo_resp> por la creacion

    Examples: 
      | reserva_id                           | total | codigo_resp |
      | 7b777a32-4d9e-4ecb-bd31-faa7902b0994 |   100 |         200 |
