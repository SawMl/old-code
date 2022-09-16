""" 
Name: Shawn Anthony
"""
class Vector:
    
    def __init__(self, l):
        self.v = l
        self.size = len(l)
        
        
        
        
        
    def get_ith_element(self, i):
        return self.v[i]
    
    
    
    def __add__(self, other):
        if self.size != other.size:
            return None
        sum = []
        for i in range(self.size):
            sum.append(self.get_ith_element(i) + other.get_ith_element(i))
        return Vector(sum)
    
    
    
    def __sub__(self, other):
        if self.size != other.size:
            return None
        sum = []
        for i in range(self.size):
            sum.append(self.get_ith_element(i) - other.get_ith_element(i))
        return Vector(sum)



	# Part2
	# TODO: implement dot product
	# inputs: self, other
	# output: if dot product is possible, return the dot product
	# 		  if dot product is not possible, return None
    def __mul__(self, other):
        if self.size != other.size:
            return None
        sum = 0
        for i in range(self.size):
            sum = sum + self.get_ith_element(i) * other.get_ith_element(i)
        return sum




	# DO NOT TOUCH! For debugging purposes
    def __str__(self):
        return str(self.v)







