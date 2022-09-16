open System

type VendingMachine = {name: string; inventory: int; price: float}

let machineDescription machine = 
  match machine.inventory with
  | 0 -> "an empty " + machine.name + " machine"
  | _ -> "a machine with " + (string machine.inventory) + " " + machine.name + " available for $" + (string machine.price) + " each"

let canPurchase machine count dollars = count <= machine.inventory && dollars >= (float count) * machine.price

let purchase machine count dollars =
  if (canPurchase machine count dollars) then
    let duplicate = {machine with inventory = (machine.inventory - count)}
    let total = (float count) * (float machine.price)
    let change = (float dollars) - total
    (change,duplicate)
  else
    (dollars,machine)


[<EntryPoint>]
let main argv =
    let peanutMachine = {name="Peanut"; inventory = 2; price=1.99}
    let bookMachine = {name="Book";inventory = 10; price = 24.49}
    let saladMachine = {name="Salad";inventory = 25; price = 9.99}

//Testing with the peanut machine
    let desc1 = machineDescription peanutMachine
    printfn "Starting with %s" desc1
    let t = purchase peanutMachine 1 2.0
    printfn "You purchased 1 peanut with $2... Change:$%.2f" (fst t)
    let desc2 = machineDescription (snd t)
    printfn "What remains is %s\n" desc2

//Testing with the book machine
    let desc3 = machineDescription bookMachine
    printfn "Starting with %s" desc3
    let r = purchase bookMachine 5 100.0
    printfn "You tried to purchase 5 books with $100...Change:$%.2f" (fst r)
    let desc4 = machineDescription (snd r)
    printfn "What remains is %s\n" desc4

//Testing with the salad machine
    let desc5 = machineDescription saladMachine
    printfn "Starting with %s" desc5
    let s = purchase saladMachine 1 20.0
    printfn "You purchased 1 salad with $20... Change:$%.2f" (fst s)
    let desc6 = machineDescription (snd s)
    printfn "What remains is %s\n" desc6

//Testing with the salad machine again
    let desc7 = machineDescription (snd s)
    printfn "Starting with %s" desc7
    let w = purchase (snd s) 25 10000.0
    printfn "You tried to purchase 25 salads with $10000... Change:$%.2f" (fst w)
    let desc8 = machineDescription (snd w)
    printfn "What remains is %s\n" desc8

    0 // return an integer exit code
