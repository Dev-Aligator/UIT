
queue = []

n = int(input("Enter the number of process: "))
quantum = int(input("Quantum Time: "))
for i in range(n):
    name, burst = input("Enter Process Name, Burst Time: ").split()
    pc = [int(name), int(burst), 0, int(burst)]
    queue.append(pc)

def gantt(name, begin, end):
    print("Process Name: ", name, "| Start processor time: ", begin, "| Stop processor time: ", end)

t = 0
total_ta = 0
total_w = 0
while queue:
    current = queue[0]
    queue.pop(0)

    if current[1] > quantum:
        current[1] -= quantum
        t += quantum
        queue.append(current)
        gantt(current[0], t-quantum, t)

    else:
        t += current[1]
        current[2] = t
        gantt(current[0], t-current[1], current[2])

        total_ta += t
        total_w += t-current[3]

print("Average waiting time: ", float(total_w)/n)
print("Average turnaround time: ", float(total_ta)/n)
