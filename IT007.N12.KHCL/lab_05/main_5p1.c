
/*
* Full Name: Nguyen Hoang Tan
* Student ID: 21521413
* Course ID: IT007.N12.KHCL
* University of Information Technology
*/

#include <pthread.h>
#include <stdio.h>


pthread_mutex_t mutex[4];
pthread_t a, b, c, d, e, f, g;
int x1, x2, x3, x4, x5, x6;
int w, v, y, z, ans;

void* processA(void*){
    w = x1 * x2;
    pthread_mutex_unlock(&mutex[0]);
    printf("Process A\n");
    return NULL;    
}

void* processB(void*){
    v = x3 * x4;
    pthread_mutex_unlock(&mutex[1]);
    printf("Process B\n");
    return NULL;
}

void* processC(void*){
    pthread_mutex_lock(&mutex[1]);
    pthread_mutex_unlock(&mutex[1]);
    y = v * x5;
    pthread_mutex_unlock(&mutex[2]);
    printf("Process C\n");
    return NULL;
}

void* processD(void*){
    pthread_mutex_lock(&mutex[1]);
    pthread_mutex_unlock(&mutex[1]);

    z = v * x6;

    pthread_mutex_unlock(&mutex[3]);
    printf("Process D\n");
    return NULL;
}

void* processE(void*){
    pthread_mutex_lock(&mutex[0]);
    pthread_mutex_lock(&mutex[2]);
    y = w * y;

    pthread_mutex_unlock(&mutex[0]);
    pthread_mutex_unlock(&mutex[2]);

    printf("Process E\n");
    return NULL;
}

void* processF(void*){
    pthread_mutex_lock(&mutex[0]);
    pthread_mutex_lock(&mutex[3]);
    z = w * z;

    printf("Process F\n");

    pthread_mutex_unlock(&mutex[0]);
    pthread_mutex_unlock(&mutex[3]);
    return NULL;
}

void* processG(void*){
    pthread_mutex_lock(&mutex[2]);
    pthread_mutex_lock(&mutex[3]);
    ans = y + z;
    printf("Ans = y + z = %d \n", ans);
    return NULL;
}

int main(){
    for(int i = 0 ; i <= 3 ; ++i){
        pthread_mutex_init(&mutex[i], NULL);
        pthread_mutex_lock(&mutex[i]);
    }

    printf("Enter x1, x2, x3, x4, x5, x6: ");
    scanf("%d %d %d %d %d %d", &x1, &x2, &x3, &x4, &x5, &x6);
    pthread_create(&a, NULL, processA, NULL);
    pthread_create(&b, NULL, processB, NULL);
    pthread_create(&c, NULL, processC, NULL);
    pthread_create(&d, NULL, processD, NULL);
    pthread_create(&e, NULL, processE, NULL);
    pthread_create(&f, NULL, processF, NULL);
    pthread_create(&g, NULL, processG, NULL);

    pthread_join(a, NULL);
    pthread_join(b, NULL);
    pthread_join(c, NULL);
    pthread_join(d, NULL);
    pthread_join(e, NULL);
    pthread_join(f, NULL);
    pthread_join(g, NULL);
    
    return 0;

}
