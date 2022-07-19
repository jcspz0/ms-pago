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
      | 8cfe59d6-7bdc-40fb-ac9a-91ac2b267e5e | PAGADA |         200 |
