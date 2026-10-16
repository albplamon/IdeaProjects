class Bebida {
  String id;
  String name;
  String description;
  String tipus;
  List<String> allergens;
  double price;
  int calories;
  bool isAlcoholic;

  Bebida(this.id, this.name, this.description, this.tipus, this.allergens,
      this.price, this.calories, this.isAlcoholic);

  factory Bebida.fromJson(Map<String, dynamic> json) {
    return Bebida(
      json['id'],
      json['name'],
      json['description'],
      json['tipus'],
      List<String>.from(json['allergens']),
      (json['price'] as num).toDouble(),
      json['calories'],
      json['isAlcoholic'],
    );
  }

  @override
  String toString() {
    return '$name ($tipus)\n'
        '  $description\n'
        '  Precio: $price € | Calorías: $calories\n'
        '  Alérgenos: ${allergens.isEmpty ? "ninguno" : allergens.join(", ")}\n'
        '  Alcohol: ${isAlcoholic ? "sí" : "no"}\n';
  }
}