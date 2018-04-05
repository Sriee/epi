from tkinter import *

root = Tk()

root.geometry('500x300')
root.title('Login')

email_label = Label(root, text='Email:')
pwd_label = Label(root, text='Password:')

email_entry = Entry(root)
pwd_entry = Entry(root)

email_label.grid(row=1, column=1)
pwd_label.grid(row=2, column=1)
email_entry.grid(row=1, column=2)
pwd_entry.grid(row=2, column=2)

root.mainloop()
