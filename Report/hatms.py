import jinja2


def render(data):
    filename = 'index.html'
    return jinja2.Environment(
        loader=jinja2.FileSystemLoader('./')
    ).get_template(filename).render(data)


def main():

    load = []
    with open('borrow.txt', 'r') as inp:

        for line in inp:
            l = line.split(',')
            load.append((l[2], l[3], l[4]))

    context = {
        "branch": "master",
        "harley": "harley-dev3",
        "version": "Latest Version",
        "payload": load
    }

    result = render(context)
    with open("result.html", 'w') as out:
        out.write(result)

if __name__ == '__main__':
    main()