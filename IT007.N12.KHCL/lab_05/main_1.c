/*
 * Full Name: Nguyen Hoang Tan
 * Student ID: 21521413
 * Course ID: IT007.N12.KHCL
 * University of Information Technology
*/

#include <semaphore.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int sells,products;
sem_t sem1, sem2 ;

void *processA(void*) 
{
	while(1)
	{
		sem_wait(&sem1);
		sells++;
		sem_post(&sem2);
		printf("sells = %d, products = %d, diff: %d \n",sells, products, products-sells);
	}
}

void *processB(void*)
{
	while (1)
	{
		sem_wait(&sem2);
		products++;
		sem_post(&sem1);
		printf("sells = %d, products = %d, diff: %d \n",sells, products, products-sells);
	}
}

int main()
{
	sem_init(&sem1,0,0);
	sem_init(&sem2,0,23);

	sells = products =  0;
 
	pthread_t t1, t2;

	pthread_create(&t1, NULL, processA, NULL);
	pthread_create(&t2, NULL, processB, NULL);

	pthread_join(t1, NULL);
	pthread_join(t2, NULL);
	
	return 0;
}

