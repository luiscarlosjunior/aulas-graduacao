# -*- coding: utf-8 -*-
"""
Created on Fri Apr 17 08:42:37 2020

@author: luisc
"""

import numpy as np
import matplotlib.pyplot as plt


data1 = [10,5,2,4,6,8]
data2 = [ 1,2,4,8,7,4]
x = 10*np.array(range(len(data1)))

plt.plot( x, data1, 'go') # green bolinha
plt.plot( x, data1, 'k:', color='orange') # linha pontilha orange

plt.plot( x, data2, 'r^') # red triangulo
plt.plot( x, data2, 'k--', color='blue')  # linha tracejada azul

plt.axis([-10, 60, 0, 11])
plt.title("Mais incrementado")

plt.grid(True)
plt.xlabel("eixo horizontal")
plt.ylabel("que legal")
plt.show()