
import 'dart:io';
String menu(){
  return "1-Añadir clase\n"
      "2-Añadir estudiante\n"
      "3-Ver todas las clases\n"
      "4-Eliminar estudiante\n"
      "5-Eliminar clase\n"
      "6-Ver todos los estudiantes\n"
      "0-Salir";
}
void claseNueva(Map<String, String> school){
  stdout.write("Que clase quiere introducir?");
  stdout.write("Codigo: ");
  String? codigo = stdin.readLineSync();
  stdout.write("Clase: ");
  String? clase = stdin.readLineSync();
  if (codigo == null || codigo.isEmpty || clase == null || clase.isEmpty) {
    print("Datos no válidos");
    return;
  }

  if (school.containsKey(codigo)) {
    print("Ya existe una clase con el código $codigo");
    return;
  }
  school[codigo] = clase;
  print("clse añadida con exito!");
}

void anyadirEstudiante(Map<String, String> school, Map<String, List<String>> estudiantes) {
  if (school.isEmpty) {
    print("No hay clases. Añade una clase primero.");
    return;
  }

  stdout.write("Código de la clase: ");
  String? codigo = stdin.readLineSync();

  if (codigo == null || !school.containsKey(codigo)) {
    print("La clase no existe");
    return;
  }

  stdout.write("Nombre del estudiante: ");
  String? nombre = stdin.readLineSync();

  if (nombre == null || nombre.trim().isEmpty) {
    print("Nombre no válido");
    return;
  }

  estudiantes.putIfAbsent(codigo, () => []);

  if (estudiantes[codigo]!.contains(nombre)) {
    print("$nombre ya está en la clase $codigo");
    return;
  }

  estudiantes[codigo]!.add(nombre);
  print("$nombre añadido a ${school[codigo]}");
}
void verClases(Map<String, String> school){
  school.forEach((codigo, clase) => print("Código:  $codigo, Clase: $clase"));
}
