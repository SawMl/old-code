class hash_table:
    def __init__(self, size = 8):
        self.table = (None,) * size
        self.size = size
        
    def insert(self, value, index):
        temp = list(self.table)
        temp[index] = value
        self.table = tuple(temp)

    def linear_probe(self, value, start_index):
        while self.get_table()[start_index]!=None:
            if start_index==(self.size-1):
                start_index=-1
            start_index=start_index+1
        return start_index

    def hash(self, value):
        self.insert(value,self.linear_probe(value,value%self.size))
        pass
	
    def get_table(self):
        return self.table

    def __str__(self):
        return str(self.table)
        




