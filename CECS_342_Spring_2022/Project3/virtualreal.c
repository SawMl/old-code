#include <stdio.h>

struct Employee{
  void **ptr;
  int age;
};

struct HourlyEmployee{
  void **ptr;
  int age;
  double hourly_rate;
  double hours;
};

struct ComissionEmployee{
  void **ptr;
  int age;
  double sales_amount;
};

struct SeniorSalesman{
  void **ptr;
  int age;
  double sales_amount;
};

void Speak_Hourly(struct Employee *ptr){
  struct HourlyEmployee *Hourly = (struct HourlyEmployee*) ptr;
  float num = (float) Hourly->hourly_rate;
  printf("I work for $%.2f dollars per hour :(",num);
}

void Speak_Comission(struct Employee *ptr){
  struct ComissionEmployee *comission;
  comission = (struct ComissionEmployee*) ptr;
  printf("I make comission on $%.2f dollars in sales!",(float)comission->sales_amount);
}

double GetPay_Hourly(struct Employee *ptr){
  struct HourlyEmployee *Hourly;
  Hourly = (struct HourlyEmployee*) ptr;
  double amount = (Hourly->hourly_rate)*(Hourly->hours);
  return amount;
}

double GetPay_Comission(struct Employee *ptr){
  struct ComissionEmployee *comission;
  comission = (struct ComissionEmployee*) ptr;
  double amount = (comission->sales_amount)*0.1+40000;
  return amount;
}

double GetPay_Senior(struct Employee *ptr){
  double pct = 0.2;
  if (ptr->age>=40){
    pct+=0.05;
  }
  struct SeniorSalesman *senior;
  senior = (struct SeniorSalesman*) ptr;
  double amount = (senior->sales_amount)*pct+50000;
  return amount;
}

void* Vtable_Hourly[2] = {Speak_Hourly, GetPay_Hourly};
void* Vtable_Comission[2] = {Speak_Comission, GetPay_Comission};
void* Vtable_Senior[2] = {Speak_Comission, GetPay_Senior};

void Construct_Hourly(struct HourlyEmployee *ptr){
  ptr->ptr=Vtable_Hourly;
  ptr->age = 0;
  ptr-> hourly_rate = 0;
  ptr->hours = 0;
}

void Construct_Comission(struct ComissionEmployee *ptr){
  ptr->ptr=Vtable_Comission;
  ptr->age = 0;
  ptr->sales_amount=0;
}

void Construct_Senior(struct SeniorSalesman *ptr){
  ptr->ptr=Vtable_Senior;
  ptr->age = 0;
  ptr->sales_amount=0;
}

int main(void) {

  struct Employee *eptr;

  printf("Choose Employee Type:\n(1)Hourly\n(2)Comission\n(3)Senior\n");
  int choice;
  scanf("%d",&choice);
  int age;
  printf("\nInput the employee's age:\n");
  scanf("%d",&age);
  struct HourlyEmployee *h = (struct HourlyEmployee*)malloc(sizeof(struct HourlyEmployee));
  struct ComissionEmployee *c = (struct ComissionEmployee*)malloc(sizeof(struct ComissionEmployee));
  struct SeniorSalesman *s = (struct SeniorSalesman*)malloc(sizeof(struct SeniorSalesman));
  float sales;
  float payRate;
  int hours;

  switch (choice){
    case 1:
      printf("\nInput the employee's hourly pay rate:\n");
      scanf("%f",&payRate);
      printf("\nInput the employee's hours:\n");
      scanf("%d",&hours);
      Construct_Hourly(h);
      h->age=age;
      h->hours=hours;
      h->hourly_rate=((double)payRate);
      eptr=h;
      break;
    case 2: 
      printf("\nInput the employee's amount of sales:\n");
      scanf("%f",&sales);
      Construct_Comission(c);
      c->age=age;
      c->sales_amount=(double)sales;
      eptr=c;
      break;
    case 3:
      printf("\nInput the employee's amount of sales:\n");
      scanf("%f",&sales);
      Construct_Senior(s);
      s->age=age;
      s->sales_amount=(double)sales;
      eptr=s;
      break;
    default: break;
  }

  ((void (*) (struct Employee*))eptr->ptr[0])((struct Employee *)eptr);
  double earnings = ((double (*) (struct Employee*))eptr->ptr[1])((struct Employee *)eptr);
  printf("\nThey make $%.2f",(float)earnings);
  
  return 0;
}