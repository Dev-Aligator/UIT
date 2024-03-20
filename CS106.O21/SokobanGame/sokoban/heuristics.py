import numpy as np


# SOKOBAN HEURISTICS

def heuristic_displaced(posBox, posGoals):
    countTargetNotFilled = 0
    for box in posBox:
        if box not in posGoals:
            countTargetNotFilled += 1
    return countTargetNotFilled

def heuristic_distance(posBox, posGoals):
    # print(posPlayer, posBox)
    """A heuristic function to calculate the overall distance between the else boxes and the else goals"""
    distance = 0
    completes = set(posGoals) & set(posBox)
    sortposBox = list(set(posBox).difference(completes))
    sortposGoals = list(set(posGoals).difference(completes))
    for i in range(len(sortposBox)):
        distance += (abs(sortposBox[i][0] - sortposGoals[i][0])) + (abs(sortposBox[i][1] - sortposGoals[i][1]))
    return distance


def heuristic_alternate(posBox, posGoals, PosOfWalls):
    global prev_boxes
    global prev_heuristic
    
    try:
        if prev_boxes == posBox:
            return prev_heuristic
        else:
            prev_boxes = posBox
    except NameError:
        prev_boxes = posBox

    sum_distance = 0
    index = 0
    completes = set(posGoals) & set(posBox)
    sortposBox = list(set(posBox).difference(completes))
    sortposGoals = list(set(posGoals).difference(completes))

    assigned = [0 for _ in range(len(sortposGoals))]

    # Only look at boxes that have not been stored
    for box in sortposBox:
        closest = float('inf')  # Use float('inf') instead of INF
        for goal in sortposGoals:
            if goal not in assigned:
                distance = abs(box[0] - goal[0]) + abs(box[1] - goal[1])
                if distance < closest:
                    closest = distance
                    assigned[index] = goal
        index += 1  # Move index incrementation inside the loop
        sum_distance += closest + personalSpace(box, PosOfWalls)
    prev_heuristic = sum_distance  # Update prev_heuristic
    return sum_distance


def personalSpace(box, obstacles):
  '''Check if any of the 8 neighboring tiles to the box have an obstacle, if they do increase the cost by number of obstacles in this space
  Inspired by looking at the difficult problems 30-39'''
  personal_space = ((box[0], box[1]+1), (box[0]+1, box[1]), (box[0]-1, box[1]), (box[0], box[1]-1),(box[0]-1, box[1]-1),(box[0]+1, box[1]+1),(box[0]+1, box[1]-1), (box[0]-1, box[1]+1))
  return len(set(personal_space)&set(obstacles)) 
