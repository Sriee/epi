from lib.graph import DFSGraph

time = 0


def helper(vertex, component):
    global time

    component.append(vertex.label)
    vertex.visited = True
    time += 1
    vertex.discovery = time

    neighbors = sorted(vertex.neighbors, key=lambda k: k.finish)

    for nbr in neighbors:
        if not nbr.visited:
            helper(nbr, component)

    time += 1
    vertex.finish = time


def strongly_connected_components(g):
    # DFS to find start/finish time
    g.dfs()

    # Transpose the graph
    gt = DFSGraph()
    for u in g:
        for v in u.neighbors:
            gt.add_edge(v.label, u.label)

    res = []
    for u in gt:
        component = []
        if not u.visited:
            helper(u, component)

        if component:
            res.append(component)

    print('Printing Components')
    for i, c in enumerate(res):
        print(i, c)


def topological_sort():
    g = DFSGraph()

    g.add_edge("A", "B")
    g.add_edge("A", "D")
    g.add_edge("B", "C")
    g.add_edge("B", "D")
    g.add_edge("D", "E")
    g.add_edge("E", "B")
    g.add_edge("E", "F")
    g.add_edge("F", "C")

    g.dfs()

    top_sort = []
    for i in g:
        print(i)
        top_sort.append(i)

    top_sort = sorted(top_sort, key=lambda x: x.finish)
    print()

    for i, j in enumerate(top_sort):
        print('{}    {}'.format(i, j))


if __name__ == '__main__':
    gp = DFSGraph()

    gp.add_edge("A", "B")
    gp.add_edge("B", "C")
    gp.add_edge("B", "E")
    gp.add_edge("C", "F")
    gp.add_edge("C", "C")
    gp.add_edge("D", "B")
    gp.add_edge("D", "G")
    gp.add_edge("E", "D")
    gp.add_edge("E", "A")
    gp.add_edge("F", "H")
    gp.add_edge("G", "E")
    gp.add_edge("H", "I")
    gp.add_edge("I", "F")

    strongly_connected_components(gp)
