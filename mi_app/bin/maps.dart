import 'dart:io';
import 'package:mi_app/maps.dart';

void main() {
  Map<String, String> school = {};
  Map<String, List<String>> estudiantes = {};

  String? opcion;

  do {
    print(menu());
    stdout.write("Opción: ");
    opcion = stdin.readLineSync();

    switch (opcion) {
      case "1":
        claseNueva(school);
        break;
      case "2":
        anyadirEstudiante(school, estudiantes);
        break;
      case "3":
        verClases(school);
        break;
      case "0":
        print("Adiós");
        break;
      default:
        print("Opción no válida");
    }
  } while (opcion != "0");
}