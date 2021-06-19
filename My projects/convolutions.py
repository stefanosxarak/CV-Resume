import numpy as np

# np.conv does NOT normalise and leaves it to the user
# IMPORTANT: this program outputs ONLY 1D arrays

A = []
a = int(input("Size of array:"))
for i in range(a):
    A.append(float(input("Element:")))
A = np.array(A)
print(A)

B = []
b = int(input("Size of array:"))
for i in range(b):
    B.append(float(input("Element:")))
B = np.array(B)
print(B)

conv = np.convolve(A, B)
print("Convolution result is:")
print(conv)