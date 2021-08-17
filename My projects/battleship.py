# import tkinter as tk
# window = tk.Tk()

# label = tk.Label(
#     text="Battleship!",
#     foreground="white",  # Set the text color to white
#     background="black"  # Set the background color to black
# )
# label.pack()

# label = tk.Label(text="Player 1 name:")
# entry = tk.Entry()
# name1 = entry.get()
# label.pack()
# entry.pack()

# label = tk.Label(text="Player 2 name:")
# entry = tk.Entry()
# name2 = entry.get()

# label.pack()
# entry.pack()


# # Assume that this list gets updated automatically
# events_list = []

# # Create an event handler
# def handle_keypress(event):
#     """Print the character associated to the key pressed"""
#     print(event.char)

# # Bind keypress event to handle_keypress()
# window.bind("<Key>", handle_keypress)


ships_dict = {
            "aircarft carrier": 5,
            "battleship": 4,
            "submarine": 3,
            "destroyer": 3,
            "patrol ship": 2
}

def print_board(board):
    for row in board:
        print (" ".join(row))
    print_board(board)

def ask_ship_position(player,ship):
    if player == name1:
        print("From")
        ship1_start_row = int(input("Row: "))
        ship1_start_col = int(input("Col: "))
        print("To")
        ship1_end_row = int(input("Row: "))
        ship1_end_col = int(input("Col: "))
    else:
        print("From")
        ship2_start_row = int(input("Row: "))
        ship2_start_col = int(input("Col: "))
        print("To")
        ship2_end_row = int(input("Row: "))
        ship2_end_col = int(input("Col: "))

name1 = "stefanos"
name2 = "zina"

def battleship():
    board1 = []
    for x in range(0, 10):
        board1.append(["O"] * 10)
        print_board(board1)

    board2 = []
    for x in range(0, 10):
        board2.append(["O"] * 10)
        print_board(board2)

    playagain = "yes"
    while playagain == "yes":
        turn = "player1"

        # label = tk.Label(text="Player 1 set your ship")
        # entry = tk.Entry()
        # ship1_row = entry.get()
        # ship1_col = entry.get()
        # label.pack()
        # entry.pack()

        for i in len(ships_dict):
            print(name1, " set your ", ships_dict[i])
            ask_ship_position(name1,)
            print("From")
            ship11_row = int(input("Row: "))
            ship11_col = int(input("Col: "))
            print("To")

        ask_ship_position(name2,)
      
        # label = tk.Label(text="Player 2 set your ship")
        # entry = tk.Entry()
        # ship2_row = entry.get()
        # ship2_col = entry.get()
        # label.pack()
        # entry.pack()

        # print(name2, " set your ship")
        # ship2_row = int(input("Row: "))
        # ship2_col = int(input("Col: "))

        for over in range(10^x):
            if turn == "player1":
                print ("Player 1 your turn")
                guess_row = int(input("Guess Row: "))
                guess_col = int(input("Guess Col: "))

                if guess_row == ship2_row and guess_col == ship2_col:
                    print ("Congratulations! You sank my battleship!") 
                    print ("PLAYER 1 WINS")
                    break
                else:
                    if guess_row not in range(10) or \
                        guess_col not in range(10):
                        print ("Oops, that's not even in the ocean.")
                    elif board1[guess_row][guess_col] == "X":
                        print ("You guessed that one already." )
                    else:
                        print ("You missed my battleship!")
                        board1[guess_row][guess_col] = "X"
                        print_board(board1)
                    turn = "player2"
            else:
                print ("Player 2 your turn")
                guess2_row = int(input("Guess Row: "))
                guess2_col = int(input("Guess Col: "))

                if guess2_row == ship1_row and guess2_col == ship1_col:
                    print ("Congratulations! You sank my battleship!") 
                    print ("PLAYER 2 WINS")
                    break
                else:
                    if guess2_row not in range(10) or \
                        guess2_col not in range(10):
                        print ("Oops, that's not even in the ocean.")
                    elif board2[guess2_row][guess2_col] == "X":
                        print ("You guessed that one already." )
                    else:
                        print ("You missed my battleship!")
                        board2[guess2_row][guess2_col] = "X"
                        print_board(board2)
                    turn = "player1"
        print('Do you want to play again? (yes or no)')
        playagain = input()


# button = tk.Button(
#     text="Start",
#     width=25,
#     height=5,
#     border=5,
#     bg="blue",
#     fg="yellow",
#     command=battleship
# )
# button.pack()
# window.mainloop()


# Make multiple battleships: you’ll need to be careful because you need to make sure that you don’t place battleships on top of each other on the game board. You’ll also want to make sure that you balance the size of the board with the number of ships so the game is still challenging and fun to play.

# Make battleships of different sizes: this is trickier than it sounds. All the parts of the battleship need to be vertically or horizontally touching and you’ll need to make sure you don’t accidentally place part of a ship off the side of the board.

# Use functions to allow your game to have more features like rematches, statistics and more!