Future<void> main() async {
  // Define three asynchronous tasks that complete after different durations
  Future<String> future1 = Future.delayed(Duration(seconds: 5), () {
    print("Returning result 1");
    return 'Result 1';
  });
  Future<String> future2 = Future.delayed(Duration(seconds: 6), () {
    print("Returning result 2");
    return 'Result 2';
  });
  Future<String> future3 = Future.delayed(Duration(seconds: 3), () {
    print("Returning result 3");
    return 'Result 3';
  });

  print('Starting Future.wait...');
  // Wait for all the tasks to complete
  final results = await Future.wait([future1, future2, future3]);
  print('All tasks have completed');

  // the results are in the same order as the original Futures
  print('Results: $results');
}