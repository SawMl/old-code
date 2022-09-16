//Shawn Anthony
//CECS 342 SEC 03
//Jan 28, 2022
//Homework 2 - Point Me in the Right Direction

#include <iostream>

int main() {
  char* buffer = (char*) malloc(1000);
  int* x = (int*)(buffer + 4);
  double* y = (double*)(buffer + 8);
  *x = 100;
  *y = 3.14159;

  printf("%d",*((int*)(buffer + 4)));
  printf("\n%f",*((double*)(buffer + 8)));
  return 0;

}
