open System

type Expression =
  | Const of float
  | Add of Expression * Expression
  | Sub of Expression * Expression
  | Neg of Expression
  | Mul of Expression * Expression
  | Div of Expression * Expression
  | Sqrt of Expression
  | Pow of Expression * Expression
  | Input of string

type Condition = 
  | Equals of Expression * Expression
  | And of Condition * Condition
  | Or of Condition * Condition
  | Not of Condition

type Statement =
  | Noop
  | PrintStr of string
  | PrintExp of Expression
  | Branch of Condition * Statement * Statement
  | Repeat of int * Statement

let funct s = 
  printfn "%s" s
  let num = Console.ReadLine() |> float
  (Const num)

let rec evaluate expr =
  match expr with
  | Const c -> c
  | Add (expr1, expr2) -> (evaluate expr1) + (evaluate expr2)
  | Sub (expr1, expr2) -> (evaluate expr1) - (evaluate expr2)
  | Neg (expr1) -> (-1.0) * (evaluate expr1)
  | Mul (expr1, expr2) -> (evaluate expr1) * (evaluate expr2)
  | Div (expr1, expr2) -> (evaluate expr1) / (evaluate expr2)
  | Sqrt (expr1) -> sqrt (evaluate expr1)
  | Pow (expr1, expr2) -> (evaluate expr1) ** (evaluate expr2)
  | Input s -> evaluate (funct s)

let rec testCondition condition = 
  match condition with
  | Equals (expr1, expr2) -> (evaluate expr1) = (evaluate expr2)
  | And (cond1,cond2) -> (testCondition cond1) && (testCondition cond2)
  | Or (cond1, cond2) -> (testCondition cond1) || (testCondition cond2)
  | Not (cond1) -> not (testCondition cond1)

let rec formatExpression expr =
  match expr with
  | Const c -> sprintf "%.1f" c
  | Add (expr1, expr2) -> sprintf "(%s + %s)" (formatExpression expr1) (formatExpression expr2)
  | Sub (expr1, expr2) -> sprintf "(%s - %s)" (formatExpression expr1) (formatExpression expr2)
  | Neg (expr1) -> sprintf "-(%s)" (formatExpression expr1)
  | Mul (expr1, expr2) -> sprintf "(%s * %s)" (formatExpression expr1) (formatExpression expr2)
  | Div (expr1, expr2) -> sprintf "(%s / %s)" (formatExpression expr1) (formatExpression expr2)
  | Sqrt (expr1) -> sprintf "sqrt(%s)" (formatExpression expr1)
  | Pow (expr1, expr2) -> sprintf "(%s ^ %s)" (formatExpression expr1) (formatExpression expr2)

let rec interpret statement = 
  match statement with
  | Noop -> ()
  | PrintStr s -> (printfn "%s" s)
  | PrintExp (expr) -> expr |> evaluate |> printfn "%f"
  | Branch (cond,s1,s2) -> if (testCondition cond) then (interpret s1) else (interpret s2)
  | Repeat (num,statement) -> if num = 0 then ()
                              else
                                interpret statement
                                interpret (Repeat (num-1,statement))


[<EntryPoint>]
let main argv =

  interpret (Branch (Or (Equals (Input "Enter a float:", Const 0.0),Not(Equals(Input "Enter another float:", Const 0.0))), PrintStr "Good Job", Repeat (3, PrintExp (Pow (Input "Enter a base:",Input "Enter an exponent:")))))

  0 // return an integer exit code