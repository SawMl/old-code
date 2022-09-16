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
  | Var of string
  | Call of string * Expression list
  | Mod of Expression * Expression

type Condition = 
  | Equals of Expression * Expression
  | And of Condition * Condition
  | Or of Condition * Condition
  | Not of Condition

type Statement =
  | Noop
  | PrintStr of string
  | PrintExp of Expression
  | Branch of Condition * Statement list * Statement list
  | Repeat of int * Statement list
  | Set of string * Expression
  | While of Condition * Statement list
  | Function of string * string list * Statement list
  | Return of Expression

type NamedEntity = {var: float; func: string list * Statement list}

let funct s = 
  printfn "%s" s
  let num = Console.ReadLine() |> float
  (Const num)

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
  

                              
let rec interpretProgram list states = 
  match list with  
  |[] -> states
  |h::t -> let newState1 = interpret h states
           interpretProgram t newState1

and interpret statement state = 
  match statement with
  | Noop -> state
  | PrintStr s -> (printfn "%s" s)
                  state
  | PrintExp (expr) -> evaluate expr state |> printfn "%f"
                       state
  | Branch (cond,s1,s2) -> if (testCondition cond state) then (interpretProgram s1 state) else (interpretProgram s2 state)
  | Repeat (num,sList) -> if num = 0 then state
                              else
                                let s = interpretProgram sList state
                                interpretProgram [Repeat (num-1,sList)] s
  | Set (var, expr) -> let value = (evaluate expr state)
                       let newState = state |> Map.add var {var = value; func = ([],[])}
                       newState
  | While (cond,sList) -> if (testCondition cond state) then 
                            interpret (While (cond,sList)) (interpretProgram sList state)
                          else state
  | Function (name,params,sList) -> let newState = state |> Map.add name {var = 0.0; func = (params, sList)}
                                    newState
  | Return (expr) -> let x = evaluate expr state
                     let newState = state |> Map.add "RETURN" {var = x; func = ([],[])} 
                     newState

and evaluate expr (state : Map<string,NamedEntity>) =
  match expr with
  | Const c -> c
  | Add (expr1, expr2) -> (evaluate expr1 state) + (evaluate expr2 state)
  | Sub (expr1, expr2) -> (evaluate expr1 state) - (evaluate expr2 state)
  | Neg (expr1) -> (-1.0) * (evaluate expr1 state)
  | Mul (expr1, expr2) -> (evaluate expr1 state) * (evaluate expr2 state)
  | Div (expr1, expr2) -> (evaluate expr1 state) / (evaluate expr2 state)
  | Sqrt (expr1) -> sqrt (evaluate expr1 state)
  | Pow (expr1, expr2) -> (evaluate expr1 state) ** (evaluate expr2 state)
  | Input s -> evaluate (funct s) state
  | Var x -> (state |> Map.find x).var
  | Call (name, exprList) -> let f = (state |> Map.find name).func //(state.[name]).func
                             let p = fst(f)
                             let predicate = fun n e -> not (List.isEmpty (fst(e.func)))
                             let newMap = (state |> Map.filter predicate)
                             let s = zipper p exprList state newMap
                             let s2 = interpretProgram (snd(f)) s
                             (s2 |> Map.find "RETURN").var
  | Mod (expr1, expr2) -> (float ((int (evaluate expr1 state)) % (int (evaluate expr2 state))))


and zipper paramList exprList oldState newState =
  match paramList with
  | [] -> newState
  | h::t -> let newState2 = newState |> Map.add h {var = (evaluate (exprList.Head) oldState); func = ([],[])}
            zipper t exprList.Tail oldState newState2

and testCondition condition state = 
  match condition with
  | Equals (expr1, expr2) -> (evaluate expr1 state) = (evaluate expr2 state)
  | And (cond1,cond2) -> (testCondition cond1 state) && (testCondition cond2 state)
  | Or (cond1, cond2) -> (testCondition cond1 state) || (testCondition cond2 state)
  | Not (cond1) -> not (testCondition cond1 state)

[<EntryPoint>]
let main argv =

  let program = [
    Function ("gcd", ["a";"b"],[
      While ((Not (Equals(Var "b",Const 0.0))), [
        Set ("t", Var "b");
        Set ("b", Mod(Var "a", Var "t"));
        Set ("a", Var "t")
      ]);
      Return (Var "a")
    ]);
    Set ("x",Input ("Please enter a float:"));
    Set ("y",Input ("Please enter another float:"));
    PrintStr "The gcd of ";
    PrintExp (Var "x");
    PrintStr "and ";
    PrintExp (Var "y");
    PrintStr "is ";
    Set ("result", Call ("gcd",[Var "x"; Var "y"]));
    PrintExp (Var "result");
    Function ("gcdRec", ["a";"b"],[
      Branch (Equals(Var "b", Const 0.0),
        [Return (Var "a")],
        [ Set ("t", Var "b");
          Set ("z", Mod (Var "a", Var "b"));
          Return (Call ("gcdRec",[Var "t"; Var "z"]))]
      )
    ]);
    Set ("x",Input ("Please enter a float:"));
    Set ("y",Input ("Please enter another float:"));
    PrintStr "The gcd of ";
    PrintExp (Var "x");
    PrintStr "and ";
    PrintExp (Var "y");
    PrintStr "is ";
    Set ("result", Call ("gcdRec",[Var "x"; Var "y"]));
    PrintExp (Var "result")
  ]

  let initialState = Map.ofList []
  let newState = interpretProgram program initialState

  0 // return an integer exit code