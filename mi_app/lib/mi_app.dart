bool esBisiesto(int anio) {
  return (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0;
}

int siguienteBisiesto(int anio) {
  int n = anio + 1;
  while (!esBisiesto(n)) {
    n++;
  }
  return n;
}

int? leerAnio(String? texto) {
  if (texto == null) return null;
  return int.tryParse(texto.trim());
}