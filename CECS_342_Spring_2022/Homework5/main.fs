open System

type AccountStatus =
  |Empty
  |Balance of int
  |Overdrawn of int

type BankAcount = {
  name: string;
  status: AccountStatus;
  credit: Option<int>
}

let isWealthy account = 
  match account with
  | {name=_;status=s;credit=_;}  -> match s with
                                    |Balance b when b>100000 -> true
                                    |_                       -> false

let isOverdrawn account = 
  match account with
  | {name=_;status=s;credit=_;}  -> match s with
                                    |Overdrawn b -> true
                                    |_           -> false

let value account =
  match account with
  | {name=_;status=s;credit=_;}  -> match s with
                                    |Overdrawn ov -> ov*(-1)
                                    |Balance b    -> b
                                    |Empty        -> 0

let largerAmount a b = 
  if (value a) >= (value b) then a
  else b

let rec amountsWhere pred coll = 
  match coll with
  |[]               -> []
  |h::t when pred h -> h :: (amountsWhere pred t) 
  |_::t             -> amountsWhere pred t

let actType num =
  if num=0 then Empty
  else if num>0 then Balance num
  else Overdrawn num

let combineAccounts coll = 
  coll |> List.map value |> List.reduce (+) |> actType

let rec wealthiestAccount coll =
  match coll with
  |[]              -> failwith "Empty list"
  |h::[]           -> h
  |h::t            -> largerAmount h (wealthiestAccount t)

let findOverdrawn coll =
    coll |> List.filter isOverdrawn

let accountAmounts coll = 
  coll |> List.map value

[<EntryPoint>]
let main argv =
    let neal = {name="neal";status=Balance 1;credit=Some 1}
    let bob = {name="bob";status=Balance 200_000;credit=Some 2}
    let drew = {name="drew";status=Overdrawn 500;credit=None}

    let b = isWealthy bob
    if b then printfn "bob is wealthy\n"
    else printfn ("bob is not wealthy")

    [neal;bob;drew]
    |> findOverdrawn
    |> printfn "%O\n"

    (largerAmount neal bob) |> printfn "%O\n"

    [neal;bob;drew]
    |> accountAmounts
    |> printfn "%O\n"

    [neal;bob;drew]
    |> amountsWhere isWealthy
    |> printfn "%O\n"

    [neal;bob;drew]
    |> combineAccounts
    |> printfn "%O\n"

    let dak = {name="Dack Fayden";status=Balance 200_000;credit=None}
    [dak;neal;bob;drew]
    |> wealthiestAccount
    |> printfn "%O\n"



    0 // return an integer exit code
