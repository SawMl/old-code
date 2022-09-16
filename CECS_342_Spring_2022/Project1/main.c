#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

struct Block {
    int block_size; // # of bytes in the data section
    struct Block *next_block; // in C, you have to use “struct Block” as the type
};

const int OVERHEAD_SIZE = sizeof(struct Block);
const int POINTER_SIZE = sizeof(void*);

// This pointer always points to the first block in the heap
// which is NOT in use. Any block linked from there must also
// NOT be in use.
struct Block *free_head;

void my_initialize_heap(int size) {
    free_head = (struct Block *)malloc(size);
    
    // The -> operator accesses the field of an object that we have
    // a pointer to.
    free_head->next_block = NULL;
    free_head->block_size = size - OVERHEAD_SIZE;
}

void* my_alloc(int size) {
    while (size % POINTER_SIZE!=0)
        size++;
    
    struct Block *temp, *prev = NULL, *chosen_prev = NULL;
    struct Block *chosen_block = NULL;

    temp = free_head;
    int max = size ;
    
    while (temp != NULL) {
        if (temp->block_size >= max) {
            max=temp->block_size;
            chosen_block = temp;
            chosen_prev = prev;
        }
        prev = temp;
        temp = temp->next_block;
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

void my_free(void* data) {
    struct Block *p = (struct Block*) ((char*)data - OVERHEAD_SIZE);
    p->next_block = free_head;
    free_head = p;
}

int main() {
    my_initialize_heap(1000);
    printf("%d\n",2*sizeof(double));

    printf("1,2,3,4,5,6?\n");
    int choice;
    scanf("%d",&choice);

    
    switch (choice){
      case 1:
        printf("TEST 1\n");
        int *a = my_alloc(sizeof(int));
        printf("a is at address %p\n", a);
        my_free(a);
        printf("a is at address %p\n", a);
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
        printf("%d\n",POINTER_SIZE);
        printf("l is at address %p\n", l);
        break;
      default:
        break;
    }
    return 0;
}
