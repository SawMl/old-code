effective(electric, water). %electric deals double damage to water
ineffective(electric, grass). %electric deals half damage to grass
ineffective(electric, electric).
immune(electric, ground). %electric deals no damage to ground

effective(fire, grass).
ineffective(fire, fire).
ineffective(fire, water).

effective(water, fire).
effective(water, ground).
ineffective(water, grass).
ineffective(water, water).

effective(grass, water).
effective(grass, grouind).
ineffective(grass, fire).
ineffective(grass, grass).

effective(ground, fire).
effective(ground, electric).
ineffective(ground, grass).

% Simple facts.

number(pikachu, 25).
evolves(pikachu, raichu).
evolves(charmander, charmeleon).
evolves(charmeleon, charizard).
evolves(eevee, jolteon).
evolves(eevee, flareon).
evolves(eevee, vaporeon).

% Slightly more complex facts.

move(thunderbolt, electric, special, 90).
move(thunderpunch, electric, physical, 75).
learns(pikachu, thunderbolt, level(36)). % Pikachu learns Thunderbolt at level 36.
learns(pikachu, thunderpunch, tm(5)).


% Simple rules.

sibling(X, Y) :- evolves(Parent, X), evolves(Parent, Y), X \= Y. % the comma means "and". "\=" means "does not unify".

canUseItem(Pokemon, tm(X)) :- learns(Pokemon, _, tm(X)). % _ is "don't care", yet again.


% A rule with multiple clauses.
descendent(X, Y) :- evolves(Y, X).
descendent(X, Y) :- evolves(Y, Z), descendent(X, Z). % This one is recursive!!
