import 'dart:convert';
import 'dart:io';
import 'plato.dart';
import 'bebida.dart';
import 'package:http/http.dart' as http;

String base = 'https://healthys-express-backend-production.up.railway.app/api';

// Pide una lista a la API (igual que antes)
Future<List<dynamic>> obtenerLista(String url) async {
  var respuesta = await http.get(Uri.parse(url));

  if (respuesta.statusCode == 200) {
    String body = utf8.decode(respuesta.bodyBytes);
    return jsonDecode(body) as List;
  }
  return [];
}

// Lee un número que escribe el usuario
int leerOpcion() {
  String? texto = stdin.readLineSync();
  return int.tryParse(texto ?? '') ?? -1;
}

// Submenú: lista completa o por tipo
Future<void> submenu(String nombre, String recurso) async {
  print('\n--- $nombre ---');
  print('1. Lista completa');
  print('2. Lista por tipo (avanzado)');
  print('0. Volver');

  int opcion = leerOpcion();

  if (opcion == 1) {
    var lista = await obtenerLista('$base/$recurso');
    for (var elemento in lista) {
      if (recurso == 'begudes') {
        print(Bebida.fromJson(elemento));
      } else {
        print(Plato.fromJson(elemento));
      }
    }
  } else if (opcion == 2) {
    print('Esta parte es la avanzada, la hacemos después.');
  }
}

void main() async {
  int opcion = -1;

  while (opcion != 0) {
    print('\n=== MENÚ ===');
    print('1. Entrantes');
    print('2. Bebidas');
    print('3. Platos principales');
    print('0. Salir');

    opcion = leerOpcion();

    if (opcion == 1) {
      await submenu('Entrantes', 'entrants');
    } else if (opcion == 2) {
      await submenu('Bebidas', 'begudes');
    } else if (opcion == 3) {
      await submenu('Platos principales', 'principals');
    } else if (opcion != 0) {
      print('Opción no válida');
    }
  }

  print('¡Hasta luego!');
}