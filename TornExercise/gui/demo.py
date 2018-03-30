from tkinter import *

root = Tk()
label = Label(root, text='This is a label')
label.pack(side=LEFT)

button = Button(root, text='Click me.', padx=50, pady=20)
button.pack(side=RIGHT)

root.mainloop()
