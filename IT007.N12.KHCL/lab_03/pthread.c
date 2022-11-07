#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/wait.h>


int loop = 1;

void on_sigint(){
    system("killall -9 vim");
    printf("\nYou have pressed CTR+C! Goodbye!\n");
    loop = 0;
}
int main(){
    printf("Welcome to IT007, I am 21521413\n");
    sleep(1);
    system("vim abcd.txt &");

    loop = 1;

    signal(SIGINT, on_sigint);
    while(loop){

    }
    return 0;

}
