import numpy as np


def per_to_dec(mat):
	return 0.01*mat



def sig_change(oldmat, newmat):
    mat = oldmat-newmat
    for x in mat:
        for y in x:
            if y<0:
                if y<=-0.0001:
                    return True
            elif y>0:
                if y>=0.0001:
                    return True
    return False




def prob_x(mat, x):
    newMat = per_to_dec(mat)
    outMat = newMat
    for i in range(x-1):
        outMat = np.dot(outMat,newMat) 
    return outMat



def long_run_dist(probs):
    ogMat = per_to_dec(probs)
    stageMat = ogMat
    outMat = np.dot(stageMat,ogMat)
    while sig_change(stageMat,outMat):
        stageMat=outMat
        outMat = np.dot(stageMat,ogMat)
    return outMat