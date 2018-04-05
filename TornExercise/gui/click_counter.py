from tkinter import *


class Clicker(Tk):

    def __init__(self):
        super(Clicker, self).__init__()

        self.dummy_label = Label(self, text='Click Count:')
        self.label = Label(self, text='0')

        self.bind('<Button-1>', self.click_counter)
        self.count = 0

        self.dummy_label.pack(side=LEFT)
        self.label.pack(side=LEFT)

    def click_counter(self, event):
        self.count += 1
        self.label.configure(text=self.count)


if __name__ == '__main__':
    clicker = Clicker()
    clicker.mainloop()
