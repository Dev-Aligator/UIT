/*######################################
# University of Information Technology #
# IT007 Operating System #
# <Nguyen Hoang Tan>, <21521413> #
# File: fcfs.c #
######################################*/
#include<stdio.h>
    int main(){
    int pn[10];
    int arr[10], bur[10], star[10], finish[10], tat[10], wt[10], i, n;
    int totwt=0, tottat=0;
    printf("Enter the number of processes:");
    scanf("%d",&n);
    for(i=0;i<n;i++) {
        printf("Enter the Process Name, Arrival Time & Burst Time:");
        scanf("%d%d%d",&pn[i],&arr[i],&bur[i]);
    }
    for(i=0;i<n;i++) {
        if(i==0) {
            star[i]=arr[i];
            wt[i]=star[i]-arr[i];
    finish[i]=star[i]+bur[i];
    tat[i]=finish[i]-arr[i];
        } else{
            star[i]=finish[i-1];
            wt[i]=star[i]-arr[i];
            finish[i]=star[i]+bur[i];
            tat[i]=finish[i]-arr[i];
        }
    }
    printf("\nPName Arrtime Burtime Start TAT Finish");
    for(i=0;i<n;i++) {
        printf("\n%d\t%6d\t\t%6d\t%6d\t%6d\t%6d",pn[i],arr[i],bur[i],star[i],tat[i],finish[i]);
        totwt+=wt[i];
        tottat+=tat[i];
    }
    float awt = 0;
    float atat = 0;
    for(int i = 0; i < n ; ++i){
        awt += wt[i];
        atat += tat[i];
    }
    awt/=n;
    atat/=n;
    printf("\nAverage waiting time: %f",awt);
    printf("\nAverage turnaround: %f\n",atat);

    return 0;
}
