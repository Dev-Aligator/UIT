
pc_list = []
queue = []
complete = []

    

n = int(input("Enter number of process: "))
for i in range(n):
    name, arr, burst = input("Enter the Process Name, Arrival Time, Burst Time: ").split()
    pc = [int(name), int(arr), int(burst), 0]
    pc_list.append(pc)

pc_list.sort(key=lambda x:x[1])

t = pc_list[0][1]

queue.append(pc_list[0])
pc_list.pop(0)
while pc_list or queue:
    current = queue[0]
    queue.pop(0)
    current[3] = t - current[1]

    t += current[2]
    count = 0
    for x in pc_list:
        if x[1]<= t:
            queue.append(x)
            count+=1
    for i in range(count):
        pc_list.pop(0)
    queue.sort(key=lambda x:x[2])
    complete.append(current)

avg_wait = 0
avg_ta = 0
complete.sort(key=lambda x:x[0])
for x in complete:
    avg_wait += x[3]
    avg_ta += x[3] + x[2]
    print("Process Name: ", x[0],"| Response time: ", x[3],"| Waiting time: ", x[3],"| Turnaround time: ", x[3] + x[2])

print("Average Waiting Time: ", float(avg_wait)/n)
print("Average Turnaround Time: ", float(avg_ta)/n )




