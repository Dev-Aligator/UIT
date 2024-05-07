import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
import pandas as pd
from sklearn.preprocessing import StandardScaler
# Load the real estate data from the file
data = pd.read_csv("RealEstate.csv")

# Extract features (X) and target variable (y)
X_original = data.iloc[:, 1:-1]  # Exclude the first and last columns
y = data.iloc[:, -1]    # Last column is the target variable
X = StandardScaler().fit_transform(X_original)
X_original = X
X = np.hstack((np.ones((X.shape[0], 1)), X))


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
Theta = beta
print('Estimate coefficients:', beta.flatten())

# Task 4: regression with gradient descent
learning_rate = 0.02
num_iterations = 2000
n = len(y)
# Initialize weights
theta = [40, 1, -3, -5,3,3,0]

# Gradient Descent
for _ in range(num_iterations):
    # Calculate predictions
    predictions = np.dot(X, theta)
    
    # Calculate error
    error = predictions - y
    
    # Calculate gradients
    gradients = (1/n) * np.dot(X.T, error)
    
    # Update weights
    theta -= learning_rate * gradients

print('Estimated coefficients using Gradient Descent:', ["{:.2f}".format(coef) for coef in theta])
