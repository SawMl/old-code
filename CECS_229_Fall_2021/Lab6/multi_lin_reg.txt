import numpy as np
import pandas as pd

def multivar_linreg(file_name):
    d_frame = pd.read_csv(file_name) #a csv file
    y_col_index = len(d_frame.columns) - 1 #y col given last is given
    y_col = np.array(d_frame.iloc[:,y_col_index]) #make an np array for the y col from the df
    num = len(y_col) #number of rows (datapoints)
    d_frame = d_frame.drop(d_frame.columns[y_col_index], axis=1) #drop the y col from the df
    ones_col = np.ones(num) #create a numpay array (length = number of rows/datapoints) of 1's
    ones_col = ones_col.reshape((num,1)) #convert the array to a n x 1 matrix (column) to be appended
    y_col = y_col.reshape((num,1)) # convert the array to a n x 1 matrix (column) to use in matrix operations
    d_frame['ones'] = ones_col #append the column of ones to the dataframe
    X = d_frame.to_numpy() #convert the dataframe to a numpy matrix to be used in matrix operations
    vector = np.matmul(np.matmul(np.linalg.inv(np.matmul(np.transpose(X),X)),np.transpose(X)),y_col) #create the [m|b] column
    vector = vector.reshape(y_col_index+1) #set it in the required numpy format to return (,num) Like a 0 row matrix
    vector = np.round(vector,4)
    return vector

def predict(inputs, file_name):
    d_frame = pd.read_csv(file_name)
    num = len(d_frame.columns)
    d_frame = d_frame.drop(d_frame.columns[num - 1], axis=1)
    numRows = d_frame.shape[0]
    ones_col = np.ones(numRows)
    ones_col = ones_col.reshape((numRows,1))
    d_frame['ones'] = ones_col
    list = []
    for index, rows in d_frame.iterrows():
        r = rows.to_numpy()
        list.append(np.matmul(inputs,r))
        
    list = np.array(list)
    list = np.round(list,4)
    return list

def MAE(inputs, file_name):
    d_frame = pd.read_csv(file_name)
    predictions = predict(inputs, file_name)
    y_col_index = len(d_frame.columns) - 1 #y col given last is given
    y_col = np.array(d_frame.iloc[:,y_col_index]) #make an np array for the y 
    cnt = len(y_col)
    s = 0.0
    for i in range(cnt):
        s = s + abs(predictions[i] - y_col[i])
    s = s / cnt
    return round(s,4)

def MRE(inputs, file_name):
    d_frame = pd.read_csv(file_name) 
    predictions = predict(inputs, file_name)
    y_col_index = len(d_frame.columns) - 1 #y col given last is given
    y_col = np.array(d_frame.iloc[:,y_col_index]) #make an np array for the y 
    cnt = len(y_col)
    s = 0.0
    for i in range(cnt):
        s = s + abs((predictions[i] - y_col[i]) / y_col[i])
    s = s / cnt
    return round(s,4)