import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
import pandas as pd
from sklearn.metrics import mean_squared_error

# Load the real estate data from the file
data = pd.read_csv("RealEstate.csv")

# Extract features (X) and target variable (y)
X_original = data.iloc[:, 1:-1]  # Exclude the first and last columns
y = data.iloc[:, -1]    # Last column is the target variable
X = np.hstack((np.ones((X_original.shape[0], 1)), X_original))

# Task 1: regression with sklearn and fit_intercept = True
reg = LinearRegression(fit_intercept=True)
reg.fit(X_original, y)

print(f'Estimate coefficients and intercept: Intercept {reg.intercept_} Coefficients {reg.coef_}')

# Task 2 : regression with sklearn and fit_intercept = False
reg = LinearRegression(fit_intercept=False)
reg.fit(X, y)

print('Estimate coefficients:', reg.coef_)

# Task 3: regression without sklearn and without gradient descent

XTX = np.dot(X.T, X)
XTXinv = np.linalg.inv(XTX)
XTXinvXT = np.dot(XTXinv, X.T)
beta = np.dot(XTXinvXT, y)

print('Estimate coefficients:', beta.flatten())

# Task 4: regression with gradient descent
class GradientDescentLinearRegression:
    def __init__(self, learning_rate=0.01, iterations=1000):
        self.learning_rate, self.iterations = learning_rate, iterations
    
    def fit(self, X, y):
        b = beta.flatten()[0]
        m = beta.flatten()[1:]
        n = X.shape[0]
        for _ in range(self.iterations):
            b_gradient = -2 * np.sum(y - np.dot(X, m) + b) / n
            m_gradient = -2 * np.sum(np.dot(X.T, (y - (np.dot(X, m) + b)))) / n
            b = b + (self.learning_rate * b_gradient)
            m = m - (self.learning_rate * m_gradient)
        self.m, self.b = m, b
        
    def predict(self, X):
        return self.m*X + self.b

reg = GradientDescentLinearRegression()
reg.fit(X_original, y)
print(reg.m)
