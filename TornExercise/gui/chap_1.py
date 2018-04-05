from tkinter import *
from tkinter import messagebox


class Root(Tk):
    def __init__(self, tasks=None):
        super().__init__()
        self.title('ToDo v2')
        self.geometry('300x400')

        self.tasks_canvas = Canvas(self)
        self.tasks_frame = Frame(self.tasks_canvas)
        self.text_frame = Frame(self)

        self.scroll_bar = Scrollbar(self.tasks_canvas, orient="vertical",
                                    command=self.tasks_canvas.yview)
        self.tasks_canvas.configure(yscrollcommand=self.scroll_bar.set)

        # Color schemes
        self.color_scheme = [{'bg': 'lightgrey', 'fg': 'black'},
                             {'bg': 'grey', 'fg': 'white'}]
        self.task_list = tasks or []

        # Packing
        self.tasks_canvas.pack(side=TOP, fill=BOTH, expand=1)
        self.scroll_bar.pack(side=RIGHT, fill=Y)

        self.canvas_area = self.tasks_canvas.create_window((0, 0),
                                                           window=self.tasks_frame,
                                                           anchor="n")
        self.create_note = Text(self.text_frame, height=3, bg='white', fg='black')
        
        self.create_note.pack(side=BOTTOM, fill=X)
        self.text_frame.pack(side=BOTTOM, fill=X)
        self.create_note.focus_set()

        h = Label(self, text='--Add Text here--', pady=10, bg=self.color_scheme[0]['bg'],
                  fg=self.color_scheme[0]['fg'])
        h.bind("<Button-1>", self.remove_task)

        self.task_list.append(h)

        for task in self.task_list:
            task.pack(side=TOP, fill=X)

        self.bind('<Return>', self.add_task)
        self.bind('<Configure>', self.on_frame_configure)
        self.bind('<Button-4>', self.mouse_scroll)
        self.bind('<Button-5>', self.mouse_scroll)
        self.bind('<MouseWheel>', self.mouse_scroll)
        self.tasks_canvas.bind('<Configure>', self.task_width)

    def add_task(self, event=None):
        txt = self.create_note.get(1.0, END).strip()
        if len(txt) > 0:
            new_task = Label(self.tasks_frame, text=txt, pady=10)
            self.set_task_color(len(self.task_list), new_task)
            new_task.bind('<Button-1>', self.remove_task)
            new_task.pack(side=TOP, fill=X)
            self.task_list.append(new_task)

        self.create_note.delete(1.0, END)

    def remove_task(self, event):
        task = event.widget
        if messagebox.askyesno('Delete Note.', 'Delete ' + task.cget('text') + '?'):
            self.task_list.remove(event.widget)
            event.widget.destroy()
            self.recolor_tasks()

    def mouse_scroll(self, event):
        if event.delta: 
            self.tasks_canvas.yview_scroll(int(-1 * (event.delta / 120)), 'units')
        else: 
            move = 1 if event.num == 5 else -1 
            self.tasks_canvas.yview_scroll(move, 'units')

    def on_frame_configure(self, event):
        self.tasks_canvas.configure(scrollregion=self.tasks_canvas.bbox('all'))

    def task_width(self, event):
        canvas_width = event.width
        self.tasks_canvas.itemconfig(self.canvas_area, width=canvas_width)

    def recolor_tasks(self):
        for index, task in enumerate(self.task_list): 
            self.set_task_color(index, task)

    def set_task_color(self, position, task):
        color_scheme = self.color_scheme[position % 2]
        task.configure(bg=color_scheme['bg'])
        task.configure(fg=color_scheme['fg'])


if __name__ == '__main__':
    root = Root()
    root.mainloop()
