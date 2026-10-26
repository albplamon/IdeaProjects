import 'dart:io';
import 'package:mi_app/mi_app.dart';



void main() {
  print('=== Comprobador de años bisiestos ===');
  print('Escribe un año o EXIT para salir.\n');

  while (true) {
    stdout.write('Año: ');
    String? entrada = stdin.readLineSync();

    if (entrada == null) {
      print('\nNo se ha recibido nada. Saliendo...');
      break;
    }

    if (entrada.trim().toUpperCase() == 'EXIT') {
      print('Adiós.');
      break;
    }

    int? anio = leerAnio(entrada);

    if (anio == null) {
      print('Entrada no válida. Escribe un número entero (por ejemplo 2024) o EXIT.\n');
      continue;
    }

    if (anio <= 0) {
      print('Escribe un año positivo (mayor que 0).\n');
      continue;
    }

    if (esBisiesto(anio)) {
      print('$anio es bisiesto.\n');
    } else {
      print('$anio no es bisiesto. El siguiente bisiesto es ${siguienteBisiesto(anio)}.\n');
    }
  }
}