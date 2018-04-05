from tkinter import *

root = Tk()


def click_me(event):
    print('Great a button has been clicked.')


def right_click(event):
    print('Is Button 2 for right click. Nope its Button-3')


button = Button(root, text='Click Me.')
button.bind('<Button-1>', click_me)
button.bind('<Button-3>', right_click)
button.pack()

root.mainloop()
