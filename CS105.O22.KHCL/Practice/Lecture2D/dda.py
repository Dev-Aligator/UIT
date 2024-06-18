import matplotlib.pyplot as plt

plt.title("DDA Algorithm")
plt.xlabel("X Axis")
plt.ylabel("Y Axis")

def DDA(x1, y1, x2, y2):
    dx = x2 - x1
    dy = y2 - y1

    k = dx / dy

    x, y = x1, x2

    xcoordinates = [x]
    ycoordinates = [y]

    while x != x2:
        x += 1
        y = round(y + k)

        xcoordinates.append(x)
        ycoordinates.append(y)

    plt.plot(xcoordinates, ycoordinates)
    plt.show()

def main():
    x1, y1, x2, y2 = map(int, input("Input your coordinates: ").split())
    DDA(x1, y1, x2, y2)

if __name__ == "__main__":
    main()
