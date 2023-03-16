def can_win(s):
    if s[0] == s[-1]:
        return "Lose"
    else:
        for i in range(0, len(s)-1):
            if s[i] == s[0] and s[i+1] == s[-1]:
                return "Win"
        return "Lose"
s = input()
print(can_win(s))

