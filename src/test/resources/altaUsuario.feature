Feature: Dar de alta un usuario

Scenario: Registrar un usuario
Given Un usuario se registra en el sistema
When Cumple los requisitos
Then Usuario registrado

Scenario: El correo ya existe
Given Un usuario ya esta registrado en el sistema
When No Cumple los Requisitos
Then Muestra Mensaje de Error

Scenario: Requisitos de Clave
Given Un usuario se va a registrar en el sistema
When No Cumple los Requisitos de la clave
Then Muestra Mensaje de Error de la clave

Scenario: Asignar rol
Given Un usuario se va a registrar en el sistema
When No se ha asignado ningun rol
Then Muestra Mensaje de asignar un rol al usuario
