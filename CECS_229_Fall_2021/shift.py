
def shift_cipher_encode(string, n):
    newString = ""
    size=len(string)
    for i in range(size):
        num = ord(string[i])
        if num>64 and num<91:
            num=num-65
            num=num+n
            num=num%26
            num=num+65
            newString=newString+chr(num)
        elif num>96 and num<123:
            num=num-97
            num=num+n
            num=num%26
            num=num+97
            newString=newString+chr(num)
        else:
            newString=newString+string[i]
    return newString




def shift_cipher_decode(string, n):
    newString = ""
    size=len(string)
    for i in range(size):
        num = ord(string[i])
        if num>64 and num<91:
            num=num-65
            num=num-n
            num=num%26
            num=num+65
            newString=newString+chr(num)
        elif num>96 and num<123:
            num=num-97
            num=num-n
            num=num%26
            num=num+97
            newString=newString+chr(num)
        else:
            newString=newString+string[i]
    return newString
