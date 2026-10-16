enum ProducteClass {
  entrant,
  main,
  drink,
}

abstract class Producte {
  String id;
  String name;
  String description;
  String tipus;
  List<String> allergens;
  double price;
  int calories;
  String additionalInfo;
  String img;

  Producte({
    required this.id,
    required this.name,
    required this.description,
    required this.tipus,
    required this.allergens,
    required this.price,
    required this.calories,
    required this.additionalInfo,
    required this.img,
  });

  @override
  String toString();

  String toStringSmart();

  ProducteClass getClass();
}