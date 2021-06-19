import numpy as np
import sys

# func = sys.argv[1]
# X = sys.argv[2]
# Y = sys.argv[3]


xs = []
a = int(input("Size of array X:"))
for i in range(a):
    xs.append(float(input("Element:")))
xs = np.array(xs)
print(xs)

ys = []
b = int(input("Size of array Y:"))
for i in range(b):
    ys.append(float(input("Element:")))
ys = np.array(ys)
print(ys)

def linear(X,Y):
    # Calculates slope 'λ' and intersect 'c'

    ones = np.ones(X.shape)
    x_e = np.column_stack((ones, X))
    v = np.linalg.inv(x_e.transpose().dot(x_e)).dot(x_e.transpose()).dot(Y)
    print(v)
    return v

def parabola(X,Y):
    y_i = np.array(Y)

    M   = np.column_stack(((np.ones(X.shape)), X, X**2))
    x_i = np.column_stack(((np.ones(X.shape)), X, X**2))

    a = np.linalg.inv(x_i.T.dot(M)).dot(x_i.T).dot(y_i)
    print(a)
    return a

def cubic(X,Y):
    y_i = np.array(Y)

    M   = np.column_stack(((np.ones(X.shape)), X, X**2, X**3))
    x_i = np.column_stack(((np.ones(X.shape)), X, X**2, X**3))

    a = np.linalg.inv(x_i.T.dot(M)).dot(x_i.T).dot(y_i)
    print(a)
    return a
    
print("Linear:")
linear(xs,ys)
print("Parabola:")
parabola(xs,ys)
# print("Cubic:")
# cubic(xs,ys)

# if func == "lin":
#     linear(X,Y)
# elif func == "par":
#     parabola(X,Y)
# else:
#     cubic(X,Y)