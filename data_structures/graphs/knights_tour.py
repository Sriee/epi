from lib.graph import *


class KTVertex(Vertex):

    def __init__(self, label):
        super().__init__(label)

    @property
    def neighbors(self):
        """Returns the neighbors in sorted order"""
        return sorted(self._neighbors, key=lambda k: k.label)

    def __iter__(self):
        """Iterates through the graph vertices in sorted order"""
        return iter(sorted(self._neighbors.items(), key=lambda v: v[0].label))


class KTGraph(Graph):
    """Graph for solving Knights Tour problem"""
    def __init__(self):
        super().__init__()

    def add_vertex(self, vertex):
        """Add vertex to the Graph

        :param vertex - New vertex to be added to the Graph
        """
        self._vertices_list[vertex] = KTVertex(vertex)


kt = KTGraph()
result = []


def get_legal_moves(x, y, size):
    """
    Calculate the legal moves for the knight at position (x, y)

    :param x: row position
    :param y: column position
    :param size: board size (5x5, 8x8)
    :return: list of legal moves the knight can take
    """
    valid_moves = []
    next_moves = [(-1, 2), (1, 2), (-1, -2), (1, -2), (2, 1),
                  (2, -1), (-2, 1), (-2, -1)]

    for move in next_moves:
        new_x, new_y = x + move[0], y + move[1]
        if 0 <= new_x < size and 0 <= new_y < size:
            valid_moves.append((new_x, new_y))
    return valid_moves


def position_to_node(x, y, board_size):
    """
    Convert (r, c) position to a numeric value

    :param x: row position
    :param y: column position
    :param board_size: board size (5x5, 8x8)
    :return: numeric value for the position
    """
    return x * board_size + y


def build_graph(board_size):
    """
    Build the graph. At each position we add edges from the position where the knight
    currently is to its possible positions (This is key while modeling a graph problem.
    Knowing how to represent the problem as a graph itself is part of the problem)

    :param board_size: board size (5x5, 8x8)
    """
    for row in range(board_size):
        for col in range(board_size):
            node = position_to_node(row, col, board_size)
            for nbr in get_legal_moves(row, col, board_size):
                kt.add_edge(node, position_to_node(nbr[0], nbr[1], board_size))


def tour_dfs(v: KTVertex, path, limit):
    global result
    if len(path) == limit:
        # Always note: During recursion, we can either cascade the result up the stack
        # or store the result in a global variable.
        result = path
        return True

    done = False
    for nbr in v.neighbors:
        if nbr.visited:
            continue

        nbr.visited = True
        done = tour_dfs(nbr, path + [nbr.label], limit)

        if not done:
            nbr.visited = False
        else:
            # Adding result = path will result in an empty list because as the stack
            # unwinds the current value of path will eventually be the starting value
            break

    return done


def tour():
    """Driver program for knights tour

    Time Complexity: O(k ^ N+1 - 1) - exponential.
    What is k? - Small constant

    Why there is k?
    For a tree of n nodes the number of nodes at each level is 2 ^ N + 1. We define
    that because at each node the branching factor is 2. But what is the branching
    factor for this problem - Variable!

    Why is it variable and not fixed?
    Legal moves knight can take differs at different positions in the board.

    """
    tour_dfs(kt.get_vertex(12), [], 5)
    print(result)


if __name__ == '__main__':
    build_graph(5)
    tour()
