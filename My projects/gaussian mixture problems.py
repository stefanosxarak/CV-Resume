import numpy as np

#Enter 2 arrays and produce the weighted mean

xs = []
a = int(input("Size of array X:"))
for i in range(a):
    xs.append(float(input("Element:")))
xs = np.array(xs)
print(xs)

ps = []
b = int(input("Size of array Y:"))
for i in range(b):
    ps.append(float(input("Element:")))
ps = np.array(ps)
print(ps)

def weightedMean(x,p):
    product =(x*p).sum()
    # we normilise by dividing with the sum of the probabilities
    return product/p.sum()

prob = weightedMean(xs,ps)
print(prob)