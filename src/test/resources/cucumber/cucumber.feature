Feature: Find By Regex?

  Scenario: Finding entities
    Given there is a collection of twoTrainings
      | name | duration |
      | ABS  | 50       |
      | CORE | 60       |

    When I give a <name>
    Then I get

    |name|duration |
    |ABS | 50      |

  Scenario: Finding entities list by Regex
    Given there is a collection of entities
      | name | duration |
      | ABS  | 50       |
      | CORE | 60       |
      | FBW  | 35       |

    When I give a regex .*ABS
    Then I get list

      |name  |duration |
      |ABS   | 50      |

  Scenario: Remove entities by list
    Given there is a collection of entities
      | name | duration |
      | ABS  | 50       |
      | CORE | 60       |
      | FBW  | 35       |

    When I give a list
      |name  |duration |
      |ABS   | 50      |
      |Core  | 60      |

    Then I get updatedList
      |name  |duration |
      |FBW   | 35      |

