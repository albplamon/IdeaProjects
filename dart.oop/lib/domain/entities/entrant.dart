import 'producte.dart';

class Entrant extends Producte {
  List<String> dietType;

  Entrant({
    required super.id,
    required super.name,
    required super.description,
    required super.tipus,
    required super.allergens,
    required super.price,
    required super.calories,
    required super.additionalInfo,
    required super.img,
    required this.dietType,
  });

  factory Entrant.fromJSON(Map<String, dynamic> json) {
    return Entrant(
      id: json['id'] ?? '',
      name: json['name'] ?? '',
      description: json['description'] ?? '',
      tipus: json['tipus'] ?? '',
      allergens: List<String>.from(json['allergens'] ?? []),
      price: (json['price'] ?? 0).toDouble(),
      calories: json['calories'] ?? 0,
      additionalInfo: json['additionalInfo'] ?? '',
      img: json['img'] ?? '',
      dietType: List<String>.from(json['dietType'] ?? []),
    );
  }

  @override
  String toString() {
    return 'Entrant{id: $id, name: $name, description: $description, '
        'tipus: $tipus, allergens: $allergens, price: $price, '
        'calories: $calories, additionalInfo: $additionalInfo, '
        'img: $img, dietType: $dietType}';
  }

  @override
  String toStringSmart() {
    return '''
Entrant
-------
Nom: $name
Descripció: $description
Tipus: $tipus
Al·lèrgens: ${allergens.join(', ')}
Preu: $price €
Calories: $calories
Informació addicional: $additionalInfo
Imatge: $img
Tipus de dieta: ${dietType.join(', ')}
''';
  }

  @override
  ProducteClass getClass() {
    return ProducteClass.entrant;
  }
}


class MainCourse extends Producte {
  List<String> dietType;

  MainCourse({
    required super.id,
    required super.name,
    required super.description,
    required super.tipus,
    required super.allergens,
    required super.price,
    required super.calories,
    required super.additionalInfo,
    required super.img,
    required this.dietType,
  });

  factory MainCourse.fromJSON(Map<String, dynamic> json) {
    return MainCourse(
      id: json['id'] ?? '',
      name: json['name'] ?? '',
      description: json['description'] ?? '',
      tipus: json['tipus'] ?? '',
      allergens: List<String>.from(json['allergens'] ?? []),
      price: (json['price'] ?? 0).toDouble(),
      calories: json['calories'] ?? 0,
      additionalInfo: json['additionalInfo'] ?? '',
      img: json['img'] ?? '',
      dietType: List<String>.from(json['dietType'] ?? []),
    );
  }

  @override
  String toString() {
    return 'MainCourse{id: $id, name: $name, description: $description, '
        'tipus: $tipus, allergens: $allergens, price: $price, '
        'calories: $calories, additionalInfo: $additionalInfo, '
        'img: $img, dietType: $dietType}';
  }

  @override
  String toStringSmart() {
    return '''
Main Course
-----------
Nom: $name
Descripció: $description
Tipus: $tipus
Al·lèrgens: ${allergens.join(', ')}
Preu: $price €
Calories: $calories
Informació addicional: $additionalInfo
Imatge: $img
Tipus de dieta: ${dietType.join(', ')}
''';
  }

  @override
  ProducteClass getClass() {
    return ProducteClass.main;
  }
}


class Drink extends Producte {
  bool isAlcoholic;

  Drink({
    required super.id,
    required super.name,
    required super.description,
    required super.tipus,
    required super.allergens,
    required super.price,
    required super.calories,
    required super.additionalInfo,
    required super.img,
    required this.isAlcoholic,
  });

  factory Drink.fromJSON(Map<String, dynamic> json) {
    return Drink(
      id: json['id'] ?? '',
      name: json['name'] ?? '',
      description: json['description'] ?? '',
      tipus: json['tipus'] ?? '',
      allergens: List<String>.from(json['allergens'] ?? []),
      price: (json['price'] ?? 0).toDouble(),
      calories: json['calories'] ?? 0,
      additionalInfo: json['additionalInfo'] ?? '',
      img: json['img'] ?? '',
      isAlcoholic: json['isAlcoholic'] ?? false,
    );
  }

  @override
  String toString() {
    return 'Drink{id: $id, name: $name, description: $description, '
        'tipus: $tipus, allergens: $allergens, price: $price, '
        'calories: $calories, additionalInfo: $additionalInfo, '
        'img: $img, isAlcoholic: $isAlcoholic}';
  }

  @override
  String toStringSmart() {
    return '''
Drink
-----
Nom: $name
Descripció: $description
Tipus: $tipus
Al·lèrgens: ${allergens.join(', ')}
Preu: $price €
Calories: $calories
Informació addicional: $additionalInfo
Imatge: $img
Alcohòlica: ${isAlcoholic ? 'Sí' : 'No'}
''';
  }

  @override
  ProducteClass getClass() {
    return ProducteClass.drink;
  }
}