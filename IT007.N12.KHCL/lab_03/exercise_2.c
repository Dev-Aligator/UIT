/*######################################
# University of Information Technology#
# IT007 Operating System#
# <Your name>, <your Student ID>#
# File: exercise_2.c#
######################################*/
#include<stdio.h>
#include<unistd.h>
int main(){
pid_t pid;
int num_coconuts = 17;
pid = fork();
if(pid == 0) {
num_coconuts = 42;
exit(0);
} else {
wait(NULL); /*wait until the child terminates */
}
printf("I see %d coconuts!\n", num_coconuts);
exit(0);
}
