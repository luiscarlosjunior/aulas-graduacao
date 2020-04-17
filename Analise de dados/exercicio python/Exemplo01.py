# -*- coding: utf-8 -*-
"""
Created on Fri Apr 17 08:39:11 2020

@author: luisc
"""

import csv

arquivo = open('titanic.csv');

linhas = csv.reader(arquivo);

for linha in linhas:
    print(linha)
