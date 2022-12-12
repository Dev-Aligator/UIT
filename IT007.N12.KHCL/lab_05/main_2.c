/*
* Full Name: Nguyen Hoang Tan
* Srudent ID: 21521413
* Course ID: IT007.N12.KHCL
* University of Information Technology
*/

#include <semaphore.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int n, *arr;
int id = 0;

sem_t sem1, sem2;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void* generate(void*){
    while(1){
        sem_wait(&sem2);
        pthread_mutex_lock(&mutex);
        int val = rand()%10000;
        arr[id] = val;
        printf("Push: %d \t Size of the array is %d \n", arr[id++], id+1);
        sem_post(&sem1);
        pthread_mutex_unlock(&mutex);
    }
}

void* eliminate(void*){
    while(1){
        sem_wait(&sem1);
        pthread_mutex_lock(&mutex);

        if(id == 0)
            printf("Array is empty\n");

        else 
            printf("Pop: %d \t Size of the array is %d\n", arr[--id] , id-1);
        sem_post(&sem2);
        pthread_mutex_unlock(&mutex);
    }
}

int main(){
    pthread_t t1, t2;
    pthread_mutex_init(&mutex, NULL);
    printf("Enter the size of array: ");
    scanf("%d", &n);
    arr = (int*)malloc(n*sizeof(int));
    sem_init(&sem1, 0 ,0 );
    sem_init(&sem2, 0, n);

    pthread_create(&t1, NULL, generate, NULL);
    pthread_create(&t2, NULL, eliminate, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    free(arr);
    return 0;
}
