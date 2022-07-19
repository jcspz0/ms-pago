@tag
Feature: vencer una deuda que este asociado a una reserva
  Se requiere vencer una deuda a partir de su id de reserva

  @tag1
  Scenario Outline: Se vence la deuda que tenga asociado el id de reserva proporcionado
    Given Dado el id de reserva "<reserva_id>"
    When Vencer la deuda asociada a la reserva
    Then El estado de la reserva es "<estado>"
    And el codigo de respuesta obtenido al vencer la reserva es <codigo_resp>

    Examples: 
      | reserva_id                           | estado  | codigo_resp |
      | 7b777a32-4d9e-4ecb-bd31-faa7902b0994 | VENCIDA |         200 |
