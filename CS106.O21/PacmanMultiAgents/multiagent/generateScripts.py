import os



layout = os.listdir('layouts')
algorithms = ['MinimaxAgent', 'AlphaBetaAgent', 'ExpectimaxAgent']
functions = ['betterEvaluationFunction','scoreEvaluationFunction']
for func in functions:
    for level in layout:
        for al in algorithms:
            command = f'python pacman.py -n 5 -l {level[:-4]} -p {al} -a depth=2,evalFn={func} -f --frameTime 0 -q'
            #command = f'python3 pacman.py --numGames 5 --layout {level[:-4]} --pacman {al} --agentArgs depth=1,evalFn={func} --fixRandomSeed'
            f = open("script.sh", "a")
            f.write(command)
            f.write('\n')
            f.close()

for func in functions:
    for level in layout:
        for al in algorithms:
            command = f'python pacman.py -n 5 -l {level[:-4]} -p {al} -a depth=2,evalFn={func} -f --frameTime 0 -q -g DirectionalGhost'
            #command = f'python3 pacman.py --numGames 5 --layout {level[:-4]} --pacman {al} --agentArgs depth=1,evalFn={func} --fixRandomSeed'
            f = open("script.sh", "a")
            f.write(command)
            f.write('\n')
            f.close()