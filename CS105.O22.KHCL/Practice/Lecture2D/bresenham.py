import matplotlib.pyplot as plt

plt.title("Bresenham Algorithm")
plt.xlabel("X Axis")
plt.ylabel("Y Axis")

def BresenhamAlgorithm(x1, y1, x2, y2):
    dy = y2 - y1
    dx = x2 - x1
    p = 2*dy - dx

    x, y = x1, y1
    xcoordinates = [x]
    ycoordinates = [y]
    while x != x2:
        if p < 0:
            x += 1
            p += 2*dy
        else:
            x += 1
            y += 1
            p += 2*(dy - dx)

        xcoordinates.append(x)   
        ycoordinates.append(y)

    plt.plot(xcoordinates, ycoordinates)
    plt.show()


def main():
    x1, y1, x2, y2 = map(int, input("Input your coordinates: ").split())
    BresenhamAlgorithm(x1, y1, x2, y2)

if __name__ == "__main__":
    main()

