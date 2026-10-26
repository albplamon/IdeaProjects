import 'dart:async';

void main() {
  print("Start");
  Future.delayed(
      Duration(milliseconds: 0),
          () => print("Hello from the event queue")
  );

  scheduleMicrotask(
          () => print("Hello from the microtask queue")
  );

  print("End");

}