from tkinter import *


class Root(Tk):
    def __init__(self, tasks=None):
        super().__init__()
        self.title('Chapter 1')
        self.geometry('300x400')

        # Color schemes
        self.color_scheme = [{'bg': 'lightgrey', 'fg': 'black'}, {'bg': 'grey', 'fg': 'white'}]
        self.task_list = tasks or []

        h = Label(self, text='--Add Text here--', pady=10, bg=self.color_scheme[0]['bg'], fg=self.color_scheme[0]['fg'])

        self.task_list.append(h)

        for task in self.task_list:
            task.pack(side=TOP, fill=X)

        self.create_note = Text(self, height=3, bg='white', fg='black')
        self.create_note.focus_set()
        self.create_note.pack(side=BOTTOM, fill=X)
        self.bind('<Return>', self.add_task)

    def add_task(self, event=None):
        txt = self.create_note.get(1.0, END).strip()
        if len(txt) > 0:
            new_task = Label(self, text=txt, pady=10)

            color_scheme = self.color_scheme[len(self.task_list) % 2]
            new_task.configure(bg=color_scheme['bg'])
            new_task.configure(fg=color_scheme['fg'])
            new_task.pack(side=TOP, fill=X)

            self.task_list.append(new_task)

        self.create_note.delete(1.0, END)

if __name__ == '__main__':
    root = Root()
    root.mainloop()
