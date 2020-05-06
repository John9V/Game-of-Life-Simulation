My Game of Life by John Vaganov
-------------------------------

This simulation was designed with flexibility in mind.

The design can adapt to accept a variety of boards. Using the GameInfo class,
all relevant information about the game dimentions and scaling can be adjusted.
The regular board could be swapped out for a whole new board such a hex board.
The life forms will still be able to make their move given the appropriate
tiles.

Each tile calls action on its occupant when the user clicks the screen.
No matter what life form is in the tile, it will be called to action. The
life form will know what to do without the tile's instructions. The tile
does not have to know what kind of life form it has. This allows for
addition of any new life form.