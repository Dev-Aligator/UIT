import numpy as np


# SOKOBAN HEURISTICS

def heuristic_displaced(posBox, posGoals):
    countTargetFilled = 0
    for box in posBox:
        if box not in posGoals:
            countTargetFilled += 1
    return countTargetFilled