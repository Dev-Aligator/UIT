/*
* Full Name: Nguyen Hoang Tan
* Student ID: 21521413
* Course ID: IT007.N12.KHCL
* University of Information Technology
*/

#include <pthread.h>
#include <stdio.h>

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
int x = 0;

void* processA(void*){
    while(1){
        pthread_mutex_lock(&mutex);
        x++;
        if(x == 20)
            x = 0;
        printf("Process A: %d\n" , x);
        pthread_mutex_unlock(&mutex);
        
    }
}

void* processB(void*){
    while(1){
        pthread_mutex_lock(&mutex);
        x++;
        if(x==20)
            x = 0;
        printf("Process B: %d\n", x);
        pthread_mutex_unlock(&mutex);
    }
}

int main(){
    pthread_mutex_init(&mutex, NULL);
    pthread_t t1, t2;
    pthread_create(&t1, NULL, processA, NULL);
    pthread_create(&t2, NULL, processB, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    return 0;
}
