@tag
Feature: Buscar deuda por id
  Se requiere buscar la deuda por el id

  @tag1
  Scenario Outline: obtener deuda del id
    Given El id de la deuda a encontrar es "<deuda_id>"
    When Busco la deuda por el id
    Then El estado de la deuda encontrada es "<estado>"
    And El codigo de respuesta al buscar la deuda es <codigo_resp>

    Examples: 
      | deuda_id                             | estado | codigo_resp |
      | 1e3cbe2a-7eb1-47f2-b57a-0f6df689c741 | PAGADA |         200 |
