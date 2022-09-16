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

let quadraticFormula a b c = 

  let fourAC = Mul (Const 4.0,Mul(Const a,Const c))
  let twoA = Mul (Const 2.0, Const a)
  let negB = Neg (Const b)
  let BSquared = Pow (Const b, Const 2.0)
  let BsquaredMinusfourAC = Sub(BSquared,fourAC)
  let root = Sqrt (BsquaredMinusfourAC)
  let expr1 = Div(Add(negB,root),twoA)
  let expr2 = Div(Sub(negB,root),twoA)

  (expr1, expr2)


[<EntryPoint>]
let main argv =

  let formulas = quadraticFormula 1.0 -5.0 4.0

  (fst formulas) |> formatExpression |> printfn("%s")
  (fst formulas) |> evaluate |> printfn("x=%.1f")

  (snd formulas) |> formatExpression |> printfn("%s")
  (snd formulas) |> evaluate |> printfn("x=%.1f")

    //let demo1 = Sub (Add (Const 10.0, Const 3.0), Neg (Const 2.0))
    //printfn "%O" demo1

    //demo1 |> formatExpression |> printfn("%s")
    //demo1 |> evaluate |> printfn "%.0f"

    //let demo2 = Sqrt (Const 36.0)
    //demo2 |> evaluate |> printfn "%.0f"
  0 // return an integer exit code
