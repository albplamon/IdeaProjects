class Plato {
  String id;
  String name;
  String description;
  String tipus;
  List<String> allergens;
  double price;
  int calories;
  List<String> dietType;

  Plato(this.id, this.name, this.description, this.tipus, this.allergens,
      this.price, this.calories, this.dietType);

  // Crea un Plato a partir de un elemento del JSON
  factory Plato.fromJson(Map<String, dynamic> json) {
    return Plato(
      json['id'],
      json['name'],
      json['description'],
      json['tipus'],
      List<String>.from(json['allergens']),
      (json['price'] as num).toDouble(),
      json['calories'],
      List<String>.from(json['dietType']),
    );
  }

  // Cómo se ve el plato al imprimirlo
  @override
  String toString() {
    return '$name ($tipus)\n'
        '  $description\n'
        '  Precio: $price € | Calorías: $calories\n'
        '  Alérgenos: ${allergens.isEmpty ? "ninguno" : allergens.join(", ")}\n'
        '  Dieta: ${dietType.isEmpty ? "-" : dietType.join(", ")}\n';
  }
}