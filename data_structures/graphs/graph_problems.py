from lib.graph import DFSGraph


if __name__ == '__main__':
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
