import numpy as np


# SOKOBAN HEURISTICS

def heuristic_displaced(posBox, posGoals):
    countTargetFilled = 0
    for box in posBox:
        if box not in posGoals:
            countTargetFilled += 1
    return countTargetFilled

def heuristic_manhattan_distance(posBox, posGoals):
    '''admissible sokoban heuristic: manhattan distance'''
    '''INPUT: Boxes and Goals positions'''
    '''OUTPUT: a numeric value that serves as an estimate of the distance of the state to the goal.'''   

    distanceSum = 0
    for box in posBox:
        min_distance = 2**31
        for goal in posGoals:
            new_dist = (abs(box[0] - goal[0]) + abs(box[1] - goal[1]))
            if new_dist < min_distance:
                min_distance = new_dist
        distanceSum += min_distance
    
    return distanceSum
