
import 'dart:math';

List<Map<String, dynamic>> productos = [
  {"nombre": "Teclado", "precio": 45.5, "categoria": "Informatica", "stock": 10},
  {"nombre": "Monitor", "precio": 189.99, "categoria": "Informatica", "stock": 0},
  {"nombre": "Raton", "precio": 15.0, "categoria": "Informatica", "stock": 25},
  {"nombre": "Silla", "precio": 120.0, "categoria": "Muebles", "stock": 4},
  {"nombre": "Lapiz", "precio": 1.5, "categoria": "Papeleria", "stock": 0},
  {"nombre": "Portatil", "precio": 899.0, "categoria": "Informatica", "stock": 3},
];

String paresEj1(){
  List<int> numeros =[1,2,3,4,5,6,7,8,9,1];

  List<int> pares = numeros.where((n) => n.isEven).toList();
  return "Los pares son: $pares";
}

String masqueZeroEj2(){
  List<int> numeros =[1,-4,3,4,0,6,7,8,19,-6];

  List<int> resultado = numeros.where((num) => num >= 0).toList();
  return "Estos son los numeros mayores que zero: $resultado";
}

String dobleEj3(){
  List<int> numeros = [1,2,3,4,5,67,324,2,-2];

  Iterable<int> doblados = numeros.map((n) => n *2);
  return "Esta es la lista * 2: $doblados";
}

String alcuadradoEj4(){
  List<int> numeros = [1,2,3,4,5,67,324,2,-2];

  List<double> raices = numeros.where((n) => n >= 0).map(sqrt).toList();
  return "Aqui lo tienes: $raices";
}

String largoEj5(){
  List<String> palabras = ["hola", "perro", "pedo", "cola", "chencho"];

  List<int> largura = palabras.map((p) => p.length).toList();
  return "Aqui esta: $largura";
}
String masde5Ej6(){
  List<String> palabras = ["hola", "perrote", "pedote", "colazo", "chencho"];

  List<String> maslargura = palabras.where((n) => n.length > 5).toList();
  return "Aqui esta: $maslargura";
}

String aMayusEj7() {
  List<String> palabras = ["hola", "perrote", "pedote", "colazo", "chencho"];

  List<String> mayus = palabras.map((p) => p.toUpperCase()).toList();
  return "Aqui esta: $mayus";
}
String cuadradoParesEj8() {
  List<int> numeros = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  List<int> resultado = numeros.where((n) => n.isEven).map((n) => n * n).toList();
  return "Los pares al cuadrado son: $resultado";
}

String mayusMasde4Ej9() {
  List<String> palabras = ["hola", "perrote", "casa", "colazo", "sol", "chencho"];

  List<String> resultado = palabras.where((p) => p.length > 4).map((p) => p.toUpperCase()).toList();
  return "Aqui esta: $resultado";
}

String positivosPor10Ej10() {
  List<int> numeros = [1, -4, 3, 0, 6, -7, 8, 19, -6];

  List<int> resultado = numeros.where((n) => n > 0).map((n) => n * 10).toList();
  return "Los positivos por 10 son: $resultado";
}

String nombresConAEj11() {
  List<String> nombres = ["Ana", "Pedro", "Alberto", "Luis", "Andrea", "Maria"];

  List<String> resultado = nombres.where((n) => n.startsWith("A")).map((n) => n.toUpperCase()).toList();
  return "Los nombres con A son: $resultado";
}

String descuentoEj12() {
  List<double> precios = [50.0, 120.0, 99.99, 300.0, 100.0, 250.5];

  List<double> resultado = precios.map((p) => p > 100 ? p * 0.8 : p).toList();
  return "Precios con descuento: $resultado";
}

String sumaEj13() {
  List<int> numeros = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  int suma = numeros.fold(0, (a, b) => a + b);
  return "La suma es: $suma";
}

String productoEj14() {
  List<int> numeros = [1, 2, 3, 4, 5];

  int producto = numeros.fold(1, (a, b) => a * b);
  return "El producto es: $producto";
}

String mayorEj15() {
  List<int> numeros = [1, 45, 3, 99, 5, -7, 23];

  int mayor = numeros.reduce((a, b) => a > b ? a : b);
  return "El mayor es: $mayor";
}

String menorEj16() {
  List<int> numeros = [1, 45, 3, 99, 5, -7, 23];

  int menor = numeros.reduce((a, b) => a < b ? a : b);
  return "El menor es: $menor";
}

String sumaParesEj17() {
  List<int> numeros = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  int suma = numeros.where((n) => n.isEven).fold(0, (a, b) => a + b);
  return "La suma de los pares es: $suma";
}

String mediaEj18() {
  List<int> numeros = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  double media = numeros.fold(0, (a, b) => a + b) / numeros.length;
  return "La media es: $media";
}

String sumaCuadradosEj19() {
  List<int> numeros = [1, 2, 3, 4, 5];

  int suma = numeros.map((n) => n * n).fold(0, (a, b) => a + b);
  return "La suma de los cuadrados es: $suma";
}

String sumaCuadradosParesEj20() {
  List<int> numeros = [1, 2, 3, 4, 5, 6, 7, 8];

  int suma = numeros.where((n) => n.isEven).map((n) => n * n).fold(0, (a, b) => a + b);
  return "La suma de los pares al cuadrado es: $suma";
}

String hayNegativoEj21() {
  List<int> numeros = [1, 2, -3, 4, 5];

  bool resultado = numeros.any((n) => n < 0);
  return "Hay algun negativo: $resultado";
}

String todosMayoresZeroEj22() {
  List<int> numeros = [1, 2, 3, 4, 5];

  bool resultado = numeros.every((n) => n > 0);
  return "Todos son mayores que zero: $resultado";
}

String ningunoZeroEj23() {
  List<int> numeros = [1, 2, 0, 4, 5];

  bool resultado = numeros.every((n) => n != 0);
  return "Ninguno es zero: $resultado";
}

String primerParEj24() {
  List<int> numeros = [1, 3, 5, 6, 7, 8];

  int? primero = numeros.where((n) => n.isEven).firstOrNull;
  return "El primer par es: $primero";
}

String palabraPresenteEj25() {
  List<String> palabras = ["hola", "perro", "pedo", "cola", "chencho"];
  String buscada = "cola";

  bool resultado = palabras.any((p) => p == buscada);
  return "La palabra $buscada esta presente: $resultado";
}

String primerNombreLargoEj26() {
  List<String> nombres = ["Ana", "Pedro", "Alberto", "Maximiliano", "Andrea", "Maria"];

  String? resultado = nombres.where((n) => n.length > 7).firstOrNull;
  return "El primer nombre largo es: $resultado";
}

String precioMasde100Ej27() {
  List<Map<String, dynamic>> resultado = productos.where((p) => p["precio"] > 100).toList();
  return "Productos de mas de 100: $resultado";
}

String nombresProductosEj28() {
  List<String> nombres = productos.map((p) => p["nombre"] as String).toList();
  return "Nombres: $nombres";
}

String sinStockEj29() {
  List<Map<String, dynamic>> resultado = productos.where((p) => p["stock"] == 0).toList();
  return "Productos sin stock: $resultado";
}

String precioConIvaEj30() {
  List<double> precios = productos.map((p) => (p["precio"] as double) * 1.21).toList();
  return "Precios con 21%: $precios";
}

String nombresBaratosEj31() {
  List<String> nombres = productos.where((p) => p["precio"] < 20).map((p) => p["nombre"] as String).toList();
  return "Productos baratos: $nombres";
}

String valorInventarioEj32() {
  double total = productos.map((p) => (p["precio"] as double) * (p["stock"] as int)).fold(0.0, (a, b) => a + b);
  return "Valor total del inventario: $total";
}

String hayProductoSinStockEj33() {
  bool resultado = productos.any((p) => p["stock"] == 0);
  return "Hay algun producto sin stock: $resultado";
}

String todosConStockEj34() {
  bool resultado = productos.every((p) => p["stock"] >= 1);
  return "Todos tienen al menos una unidad: $resultado";
}

String productoMasCaroEj35() {
  Map<String, dynamic> mayor = productos.reduce((a, b) => a["precio"] > b["precio"] ? a : b);
  return "El producto mas caro es: $mayor";
}






