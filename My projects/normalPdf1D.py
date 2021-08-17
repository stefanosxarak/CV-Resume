import numpy as np

# m is the mean 
# v is the variance
# s is the standard deviation

# One-dimensional Gaussian/Normal probability density function
# IMPORTANT: this program is NOT for multi-dimensional Normal pdf
# Enter 2 arrays "a" and "b" if there is a second class, otherwise only "a"

xs = []
a = int(input("Size of array:"))
for i in range(a):
    xs.append(float(input("Element:")))
xs = np.array(xs)
print(xs)

m = xs.sum()/a
v = ((xs-m)**2).sum()/a
s = np.sqrt(v)

# x is hardcoded posterior probability depending on instructions from the question
def normpdf(x,m,s):
    return np.exp(-(x-m)**2/(2*s**2))/np.sqrt(2*np.pi*s**2)

p = normpdf(0.1,m,s)
print(p)

# only answer yes when promted, NOT before
answer = str(input("Is there a second class?"))

if answer == "yes":
    ys = []
    b = int(input("Size of array:"))
    for i in range(b):
        ys.append(float(input("Element:")))
    ys = np.array(ys)
    print(ys)

    m1 = ys.sum()/b
    v1 = ((ys-m1)**2).sum()/b
    s1 = np.sqrt(v1)
    p1 = normpdf(0.1,m1,s1)
    print(p1)

    print("Posterior probability: ")
    print(p1/(p+p1))
