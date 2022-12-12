/*
* Full Name: Nguyen Hoang Tan
* Student ID: 21521413
* Course ID: IT007.N12.KHCL
* University of Information Technology
*/

#include <pthread.h>
#include <stdio.h>

int x = 0;

void* processA(void*){
    while(1){
        x++;
        if(x == 20)
            x = 0;
        printf("Process A: %d\n" , x);
    }
}

void* processB(void*){
    while(1){
        x++;
        if(x==20)
            x = 0;
        printf("Process B: %d\n", x);
    }
}

int main(){
    pthread_t t1, t2;
    pthread_create(&t1, NULL, processA, NULL);
    pthread_create(&t2, NULL, processB, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    return 0;
}
