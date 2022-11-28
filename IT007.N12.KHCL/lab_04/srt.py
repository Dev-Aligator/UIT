
pc_list = []
queue = []
complete = []

n = int(input("Enter the number of process: "))
for i in range(n):
    name, arr, burst = input("Enter Process Name, Arrival Time, Burst Time: ").split()
    pc = [int(name), int(arr), int(burst), 0, 0, int(burst)]
    pc_list.append(pc)

pc_list.sort(key=lambda tup:tup[1])

t = pc_list[0][1]
queue.append(pc_list[0])
pc_list.pop(0)

while pc_list or queue:
    current = queue[0]
    queue.pop(0)

    if not current[4] == 0 and current[1]:
        current[4] = t
    
    if pc_list:
        pc_time = pc_list[0][1] - t
        current[2] -= pc_time
        t = pc_list[0][1]
        queue.append(pc_list[0])
        pc_list.pop(0)
        queue.sort(key=lambda tup:tup[2])
        if current[2] <= 0:
            current[3] = t
            complete.append(current)
        else:
            queue.append(current)
            queue.sort(key=lambda tup:tup[2])

    else:
        t += current[2]
        current[3] = t
        complete.append(current)



            
complete.sort(key=lambda tup:tup[0])

total_w = 0
total_ta = 0
for x in complete:
    print("Process Name: " , x[0],"| Response time: ", x[4] - x[1], "| Waiting Time: ", x[3] - x[1] - x[5], "| Turnaround Time: ", x[3] - x[1])  
    total_w += x[3] -x[1] - x[5]
    total_ta += x[3] -x[1]
print("Average Waiting Time: ", float(total_w)/n)
print("Average Turnaround Time: ", float(total_ta)/n)

