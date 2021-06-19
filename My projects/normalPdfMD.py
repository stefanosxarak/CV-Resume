import numpy as np
from scipy.stats import multivariate_normal

# Multi-dimensional Gaussian/Normal probability density function:
mean = []
covariance = []
xs = []
a = int(input("Size of array:"))
for i in range(a):
    xs.append(float(input("Element:")))
xs = np.array(xs)
print(xs)


b = int(input("Size of array:"))
for i in range(b):
    mean.append(float(input("Element:")))
mean = np.array(mean)
print(mean)

R = int(input("Enter the number of rows:"))
C = int(input("Enter the number of columns:"))
# For the covariance, please input data rowise
covariance = [[float(input()) for x in range (C)] for y in range(R)]

var = multivariate_normal.pdf(xs,mean,covariance)
print(var)
