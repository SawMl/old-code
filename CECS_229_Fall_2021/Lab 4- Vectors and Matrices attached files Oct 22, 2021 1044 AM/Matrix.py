""" 
Name: Shawn Anthony
"""
class Matrix:
	
	# input: mat (a 2d list)
	# Example: Matrix([[1, 2, 3], [2, 4, 6]]) makes a matrix like...
	# [1 2 3 ]
	# [2 3 6 ]
    def __init__(self, mat):
		# ALREADY DONE FOR YOU! DO NOT TOUCH
        self.m = mat 				# the matrix
        self.rows = len(mat)		# number of rows
        self.cols = len(mat[0])		# number of columns

	# get's the element of the matrix in row i, column j
    def get_element(self, i, j):
		# ALREADY DONE FOR YOU! DO NOT TOUCH
        return self.m[i][j]

	# Part3
	# TODO: implement matrix addition
	# inputs: self, other
	# output: if matrix addition is possible, return the sum Matrix
	#		  DO NOT RETURN A 2D LIST! YOU WILL GET IT WRONG!
	# 		  if matrix addition is not possible, return None
    def __add__(self, other):
        if self.rows != other.rows:
            return None
        if self.cols != other.cols:
            return None
        mat = []
        for i in range(self.rows):
            r=[]
            for j in range(self.cols):
                r.append(self.get_element(i,j)+other.get_element(i,j))
            mat.append(r)
        return Matrix(mat)

	# Part4
	# TODO: implement matrix subtraction
	# inputs: self, other
	# output: if matrix subtraction is possible, return the difference Matrix
	#		  DO NOT RETURN A 2D LIST! YOU WILL GET IT WRONG!
	# 		  if matrix subtraction is not possible, return None
    def __sub__(self, other):
        if self.rows != other.rows:
            return None
        if self.cols != other.cols:
            return None
        mat = []
        for i in range(self.rows):
            r = []
            for j in range(self.cols):
                s = self.get_element(i,j) - other.get_element(i,j)
                r.append(s)
            mat.append(r)
        return Matrix(mat)


    def __mul__(self, other):
        if self.cols != other.rows:
            return None
        mat=[]
        for i in range(self.rows):
            r = []
            for j in range(other.cols):
                dot = 0
                for k in range(self.cols):
                    dot = dot + self.get_element(i,k) * other.get_element(k,j)
                r.append(dot)
            mat.append(r)
                    
        return Matrix(mat)
        
        


	# DO NOT TOUCH! For debugging purposes
    def __str__(self):
        string = ""
        for r in self.m:
            string += "["
            for c in r:
                string += str(c) + " "
            string += "]\n"
        return string



