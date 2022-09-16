#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

struct Block {
    int block_size;
    struct Block *next_block;
};

const int OVERHEAD_SIZE = sizeof(struct Block);
const int POINTER_SIZE = sizeof(void*);

struct Block *free_head;

void my_initialize_heap(int size) {
    free_head = (struct Block *)malloc(size);
    free_head->next_block = NULL;
    free_head->block_size = size - OVERHEAD_SIZE;
}

void* my_alloc(int size) {
    while (size % POINTER_SIZE!=0){
        size++;
    }
    
    struct Block *temp, *prev = NULL, *chosen_prev = NULL;
    struct Block *chosen_block = NULL;

    temp = free_head;
    int max = size ;
    bool foundExact=false;;

    //look for exact
    while (temp!=NULL){
      if (temp->block_size == size){
        chosen_block = temp;
        chosen_prev = prev;
        foundExact=true;
        break;
      }
      prev = temp;
      temp = temp->next_block;
    }
    
    if (!foundExact){
      temp = free_head;
      while (temp != NULL) { 
        if (temp->block_size > max) {
            max=temp->block_size;
            chosen_block = temp;
            chosen_prev = prev;
        }
        prev = temp;
        temp = temp->next_block;
      }
    }


    if (chosen_block == NULL) {
        return NULL;
    }

    bool split = false;
    int sizeRemaining = chosen_block->block_size;
    sizeRemaining = sizeRemaining - size - OVERHEAD_SIZE - POINTER_SIZE;
    if (sizeRemaining >= 0){
        split = true;
    }
    
    bool head = chosen_block==free_head;

    if (!split && head){
        free_head = free_head->next_block;
        chosen_block->block_size = size;
        chosen_block->next_block=NULL;
    }
    // Branch 2: we are splitting the head node.
    else if (split && head){
        struct Block *new_block = (struct Block*)((char*)chosen_block + size + OVERHEAD_SIZE);
        new_block->block_size = free_head->block_size - size - OVERHEAD_SIZE;
        new_block->next_block = free_head->next_block;
        free_head->block_size = size;
        free_head->next_block = NULL;
        free_head = new_block;

    }
    // Branch 3: we are not splitting an interior node.
    else if (!split && !head){
        chosen_block->block_size = size;
        chosen_prev->next_block = chosen_block->next_block;
        chosen_block->next_block = NULL;
    }
    // Branch 4: we are splitting an interior node.
    else{
        struct Block *new_block = (struct Block*)((char*)chosen_block + size + OVERHEAD_SIZE);
        new_block->block_size = chosen_block->block_size - size - OVERHEAD_SIZE;
        new_block->next_block = chosen_block->next_block;
        chosen_prev->next_block = new_block;
        chosen_block->block_size=size;
        chosen_block->next_block=NULL;
        
    }
    
    return (char*)chosen_block + OVERHEAD_SIZE;
}

/*Performs coalescing by traversing through the free list and checking if
the end/beginning of an available block is the beginning/end of the block we're trying to free. If it is, they are "merged".
*/
void my_free(void* data) {
    struct Block *p = (struct Block*) ((char*)data - OVERHEAD_SIZE);

    struct Block *temp;
    struct Block *temp2;
    temp = free_head;

    int x,y;
    bool flag = false;
  while (temp!=NULL){
    if (temp<p){
      x = temp->block_size;
      temp2 = (struct Block*) ((char*)temp + OVERHEAD_SIZE + x);
      if (temp2==p){
        y = x + OVERHEAD_SIZE + p->block_size;
        temp->block_size=y;
        p = temp;
      }
    }
    else if (temp>p){
      x = p->block_size;
      temp2 = (struct Block*) ((char*)p + OVERHEAD_SIZE + x);
      if (temp2==temp){
        y = x + OVERHEAD_SIZE + temp->block_size;
        p->block_size=y;
      }
    }
    temp = temp->next_block;
  }
  p->next_block = free_head;
  free_head = p;
}


int main() {
    my_initialize_heap(1000);

    printf("Choose Test 1,2,3,4,5,6?\n");
    int choice;
    scanf("%d",&choice);

    
    switch (choice){
      case 1:
        printf("TEST 1\n");
        int *a = my_alloc(sizeof(int));
        printf("a is at address %p\n", a);
        my_free(a);
        int *z = my_alloc(sizeof(int));
        printf("z is at address %p\n", z);
        break;
      case 2:
        printf("TEST 2\n");
        int *b = my_alloc(sizeof(int));
        printf("b is at address %p\n", b);
        int *c = my_alloc(sizeof(int));
        printf("c is at address %p\n", c);
        break;
      case 3:
        printf("TEST 3\n");
        int *d = my_alloc(sizeof(int));
        int *e = my_alloc(sizeof(int));
        int *f = my_alloc(sizeof(int));
        printf("d is at address %p\n", d);
        printf("e is at address %p\n", e);
        printf("f is at address %p\n", f);
        my_free(e);
        int *g = my_alloc(2 * sizeof(double));
        printf("g is at address %p\n", g);
        my_free(g);
        
        int *h = my_alloc(sizeof(int));
        printf("h is at address %p\n", h);
        
        break;
      case 4:
        printf("TEST 4\n");
        int *i = my_alloc(sizeof(char));
        int *j = my_alloc(sizeof(int));
        printf("i is at address %p\n", i);
        printf("j is at address %p\n", j);
        break;
      case 5:
        printf("TEST 5\n");
        int *k = my_alloc(80 * sizeof(int));
        int *l = my_alloc(sizeof(int));
        printf("k is at address %p\n", k);
        printf("l is at address %p\n", l);
        my_free(k);
        printf("l is at address %p\n", l);

        break;
      
      case 6: //Demonstrates coalescing
        printf("Test 6\n");
        printf("free_head is at address %p\n", free_head);
        int *m = my_alloc(sizeof(int));
        int *n = my_alloc(sizeof(int));
        int *o = my_alloc(sizeof(int));
        printf("m is at address %p\n", m);
        printf("n is at address %p\n", n);
        printf("o is at address %p\n", o);
        my_free(m);
        my_free(o);
        printf("free_head is at address %p\n", free_head);
        printf("n is at address %p\n", n);
        my_free(n);
        printf("free_head is at address %p\n", free_head);


      default:
        break;
    }
    return 0;
}