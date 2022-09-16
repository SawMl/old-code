import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

def least_sq(file_name):
    data_frame = pd.read_csv(file_name)
    x_squared = []
    xy = []
    for index, row in data_frame.iterrows():
        x_squared.append(row['x']*row['x'])
        xy.append(row['x']*row['y'])
    data_frame["xx"] = x_squared
    data_frame["xy"] = xy
    n = len(data_frame.index)
    m = (n * data_frame['xy'].sum() - data_frame['x'].sum() * data_frame['y'].sum()) / ( n * data_frame['xx'].sum() - (data_frame['x'].sum() * data_frame['x'].sum()) )
    y_intercept = ( data_frame['y'].sum() - m * data_frame['x'].sum() ) / n
    m = round(m,4)
    y_intercept = round(y_intercept,4)
    return m, y_intercept


def mat_least_sq(file_name):
    data_frame = pd.read_csv(file_name)
    X=[]
    y=[]
    for index, row in data_frame.iterrows():
        X.append([row['x'],1])
        y.append(row['y'])
        
    X = np.array(X)
    
    vector = np.matmul(np.matmul(np.linalg.inv(np.matmul(np.transpose(X),X)),np.transpose(X)),y)
    return round(vector[0],4), round(vector[1],4)
    

def predict(file_name, x):
    m, b = mat_least_sq(file_name)
    y = m * x + b
    return round(y,4)


def plot_reg(file_name, using_matrix):
    data_frame = pd.read_csv(file_name)
    x_coords = data_frame['x'].tolist()
    y_coords = data_frame['y'].tolist()
    x_max = max(x_coords)
    x_min = min(x_coords)
    x = np.linspace(x_min,x_max)
    l = 'y = '
    if using_matrix:
        m, b = mat_least_sq(file_name)
        l = l + str(m) + 'x + ' + str(b)
        plt.scatter(x_coords,y_coords, color='black', marker='*',label='Datapoints')
        plt.title('Using Matrix Method')
        plt.xlabel('x-coordinates')
        plt.ylabel('y-coordinates')
        plt.plot(x,x*m+b, color='black', label=l)
    else:
        m, b = least_sq(file_name)
        l = l + str(m) + 'x + ' + str(b)
        plt.scatter(x_coords,y_coords, color='red', marker='x',label='Datapoints')
        plt.title('Using Algebra Method')
        plt.xlabel('x-coordinates')
        plt.ylabel('y-coordinates')
        plt.plot(x,x*m+b, color='red', label=l)
    plt.legend()
    plt.show()
    pass
