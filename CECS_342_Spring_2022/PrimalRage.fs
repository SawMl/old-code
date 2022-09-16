//Shawn Anthony
//.01/22/2022
//CECS 342
//This program determines if a number is a prime number
//by checking x mod i for i={2,3,5,7,...,t} where t is the sqrt of x

open System
printfn "Input an integer"
let x = Console.ReadLine() |> int //input from the user
let y : int = int((sqrt (float x))) //get the (int) of the sqrt of x
let mutable cnt = 2 //first to check evens
let mutable flag = false

if x % cnt = 0 then flag <- true //check evens

cnt <- cnt + 1 //increment to next check 3

while not flag && cnt <= y do //stop looping as soon as found
  if x % cnt = 0 then flag <- true
  cnt <- cnt + 2 //5...7...9...y


let mutable message = ""
if flag then
  message <- " is not prime"
else
  message <- " is prime"

printfn "The number %d%s" x message //output the result
