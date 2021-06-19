import numpy as np

#This is K means for 2 cluster centers

cc1 = float(input("Cluster Center 1:"))
cc2 = float(input("Cluster Center 2:"))

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

#cc1=cluster center 1
#cc2=cluster center 2

def clustering(x,y,cc1,cc2):
    return ((x-cc1)**2).sum() + ((y-cc2)**2).sum()

k = clustering(xs,ys,cc1,cc2)
print(k)