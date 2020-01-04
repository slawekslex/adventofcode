from pathlib import Path
from itertools import product
import numpy as np


def lines(input_file):
    return input_file.read_text().strip().split("\n")

def chars(input_file):
    return np.array([list(s) for s in lines(input_file)])

def get(S, x, y ,level =0):
    if not level in range(len(S)):
        return 0
    if x in range(n) and y in range(m) and S[level][x][y] == '#': return 1
    return 0

def neigh_level(x, y, level=0):
    for dx, dy in product([-1,0,1], repeat=2):
         if abs(dx)+abs(dy)==1: 
            xx, yy = x+dx, y+dy
            if (xx,yy) == (2,2):
                if dy ==1: nx, ny = range(5), [0]*5
                if dy ==-1: nx, ny = range(5), [4]*5
                if dx ==-1: nx, ny = [4]*5, range(5)
                if dx ==1: nx, ny = [0]*5, range(5)
                for a,b in zip(nx,ny): yield (a,b,level+1)
            elif xx == -1:
                yield (1,2,level-1)
            elif xx == 5:
                yield (3,2,level-1)
            elif yy == -1:
                yield (2,1,level-1)
            elif yy == 5:
                yield (2,3,level-1)
            else:
                yield (xx,yy,level)

def neigh_simple(x, y, level=0):
    for dx, dy in product([-1,0,1], repeat=2):
         if abs(dx)+abs(dy)==1: 
            xx, yy = x+dx, y+dy
            yield (xx,yy,level)


def mutate(S, level, neigh_func):
    ns = np.full((n,m), '.')
    for i, j in product(range(n), range(m)): 
        bugs = sum (get(S, x, y, l) for x, y, l in neigh_func(i,j, level))
        if S[level][i][j] == '#' and bugs == 1: ns[i][j]='#'
        if S[level][i][j] == '.' and bugs in [1,2]:ns[i][j]='#'          
    return ns

def part1(S):
    V =set()
    S=[S]
    while True:
        code = sum([2**(i*n+j) * get(S, i, j) for i,j in product(range(n), range(m))])
        if code in V: return code
        V.add(code)
        S = [mutate(S,0, neigh_simple)]


def part2(S):
    S = [S]
    for _ in range(200):
        S = [np.full((n,m), '.')]+S+[np.full((n,m), '.')]
        NS = []
        for i in range (len(S)):
            NS.append(mutate(S, i, neigh_level))
        S = NS
    bugs = sum(get(S,x,y,l) for x, y, l in product(range(5), range(5), range(len(S))) if (x,y)!=(2,2))
    return bugs

    

input_file = Path('./D24.txt')
S = chars(input_file)
n, m= len(S), len(S[0])
print(part1(S))
print(part2(S))





