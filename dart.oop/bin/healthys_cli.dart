import '../lib/domain/entities/entrant.dart';

void main() {
  print('======================================');
  print('       HEALTHYS - PROVA ENTITATS      ');
  print('======================================');

  // --------------------------------------------------
  // ENTRANT
  // --------------------------------------------------

  Entrant entrant = Entrant(
    id: '1',
    name: 'Ensalada Valenciana',
    description: 'Ensalada con productos frescos',
    tipus: 'entrant',
    allergens: ['frutos secos'],
    price: 7.50,
    calories: 250,
    additionalInfo: 'Servida fría',
    img: 'ensalada.jpg',
    dietType: ['vegetariana', 'vegana'],
  );

  print('\n--- ENTRANT ---');
  print(entrant);
  print(entrant.toStringSmart());
  print('Clase: ${entrant.getClass()}');


  // --------------------------------------------------
  // MAIN COURSE
  // --------------------------------------------------

  MainCourse mainCourse = MainCourse(
    id: '2',
    name: 'Paella Valenciana',
    description: 'Paella tradicional valenciana',
    tipus: 'principal',
    allergens: [],
    price: 14.50,
    calories: 650,
    additionalInfo: 'Especialidad de la casa',
    img: 'paella.jpg',
    dietType: [],
  );

  print('\n--- MAIN COURSE ---');
  print(mainCourse);
  print(mainCourse.toStringSmart());
  print('Clase: ${mainCourse.getClass()}');


  // --------------------------------------------------
  // DRINK
  // --------------------------------------------------

  Drink drink = Drink(
    id: '3',
    name: 'Agua',
    description: 'Agua mineral',
    tipus: 'beguda',
    allergens: [],
    price: 2.00,
    calories: 0,
    additionalInfo: '',
    img: 'agua.jpg',
    isAlcoholic: false,
  );

  print('\n--- DRINK ---');
  print(drink);
  print(drink.toStringSmart());
  print('Clase: ${drink.getClass()}');


  // --------------------------------------------------
  // TEST FROM JSON
  // --------------------------------------------------

  Map<String, dynamic> entrantJson = {
    'id': '4',
    'name': 'Patatas Bravas',
    'description': 'Patatas con salsa brava',
    'tipus': 'entrant',
    'allergens': [],
    'price': 6.50,
    'calories': 400,
    'additionalInfo': 'Salsa picante',
    'img': 'bravas.jpg',
    'dietType': ['vegetariana'],
  };

  Entrant entrantFromJson = Entrant.fromJSON(entrantJson);

  print('\n--- ENTRANT DES DE JSON ---');
  print(entrantFromJson.toStringSmart());


  Map<String, dynamic> drinkJson = {
    'id': '5',
    'name': 'Cerveza',
    'description': 'Cerveza de barril',
    'tipus': 'beguda',
    'allergens': ['gluten'],
    'price': 3.50,
    'calories': 150,
    'additionalInfo': '',
    'img': 'cerveza.jpg',
    'isAlcoholic': true,
  };

  Drink drinkFromJson = Drink.fromJSON(drinkJson);

  print('\n--- DRINK DES DE JSON ---');
  print(drinkFromJson.toStringSmart());


  print('\n======================================');
  print('             FI DE LA PROVA           ');
  print('======================================');
}