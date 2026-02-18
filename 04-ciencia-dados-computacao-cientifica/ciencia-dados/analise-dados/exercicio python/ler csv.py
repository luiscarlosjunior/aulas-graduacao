# -*- coding: utf-8 -*-
"""
Created on Thu Apr 16 23:28:44 2020

@author: luisc
"""

import csv

arquivo = open('titanic.csv');

linhas = csv.reader(arquivo);

for linha in linhas:
    print(linha)
