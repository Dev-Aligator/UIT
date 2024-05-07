import numpy as np
import matplotlib.pyplot as plt

def generate_data(n):
    X = []
    y = []
    for i in range(n):
        if i <= n/2:
            X.append(np.random.normal(loc=4, scale=1))
            y.append(0)
        else:
            X.append(np.random.normal(loc=8, scale=1))
            y.append(1)

    return np.array(X).reshape(n, 1), np.array(y)

def sigmoid(z):
    return 1 / (1 + np.exp(-z))

def logistic_sigmoid_regression(X, y, n, p, alpha, max_count=5000):
    beta = np.random.normal(loc=0, scale=1, size=2)
    X_new = np.hstack((np.ones((n, 1)), X))

    count = 0
    while count < max_count:
        print(count)
        z = beta[0] * X_new[:, 0] + beta[1] * X_new[:, 1]
        for j in range(p + 1):
            gradient_j = np.sum((y - sigmoid(z)) * X_new[:, j])
            beta[j] = beta[j] + alpha * gradient_j

        count = count + 1
    return beta

n = 50
p = 1
X, y = generate_data(n)
# Exercise: Write your own code of gradient descent to compute beta_0 and beta_1
alpha = 0.01
beta = logistic_sigmoid_regression(X, y, n, p, alpha)
print(beta)

xx = np.linspace(2, 10, 1000)
yy = sigmoid(beta[0] + beta[1] * xx)

plt.scatter(X, y, s=80, c=y.flatten())
plt.plot(xx, yy, 'r-', linewidth=2)
plt.show()
