team1 = []
for i in range(3):
    team1.append(list(map(int,input().split())))
team2 = []
for i in range(3):
    team2.append(list(map(int,input().split())))

result1 = [0,0,0,0]
result2 = [0,0,0,0]
for match in team1:
    if match[0] > match[1]:
        result1[0]+=3
    elif match[0] == match[1]:
        result1[0]+=1
    result1[1] += match[0] - match[1] 
    result1[2] += match[0]
    result1[3]+=match[2]
for match in team2:
    if match[0] > match[1]:
        result2[0]+=3
    elif match[0] == match[1]:
        result2[0]+=1
    result2[1] += match[0] - match[1] 
    result2[2] += match[0]
    result2[3] += match[2]


if result1 > result2:
    [print(x, end= " ") for x in result1]
else:
    [print(x, end= " ") for x in result2]
    
    

