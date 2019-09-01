class Vertex:
    def __init__(self, label):
        """Initialize the vertex."""
        self._label = label
        self._neighbors = {}

    def add_neighbor(self, neighbor, weight=0):
        """Adds the vertex to the list of connected vertices

        :param neighbor - The vertex to be connected
        :param weight- Weight of the connection
        """
        self._neighbors[neighbor] = weight

    @property
    def label(self):
        """Returns the label of the vertex."""
        return self._label

    @property
    def neighbors(self):
        """Return the vertices of the graph."""
        return self._neighbors.keys()

    def weight(self, label):
        """Return the weight of the vertices."""
        return self._neighbors.get(label, 0)

    def __delattr__(self, item):
        """Delete's the vertex from the connected list."""
        if item in self._neighbors:
            del self._neighbors[item]
        else:
            raise KeyError

    def __contains__(self, item):
        """
        Checks if the given vertex is connected to this vertex

        :param item: The vertex to be searched
        :return: True - If the given vertex is connected to this vertex
                False - Otherwise
        """
        return item in self._neighbors

    def __iter__(self):
        return iter(self._neighbors)

    def __len__(self):
        return len(self._neighbors)

    def __str__(self):
        """Prints the vertex list connected to this vertex."""

        return '{} is connected to: [{}]'.format(self._label, ', '.join(
                                                [x.label for x in self._neighbors]))


class Graph:
    """The Graph is an abtract datatype which can be implemented in two ways
           1. Adjacency Matrix
           2. Adjacency List

    Adjacency Matrix:
        Implementing the Adjacency matrix is easy. Maintain a 2D array for the
        number of vertices. If the vertices are connected add weight as the value
        for adjacency_matrix[from_vertex][to_vertex] = weight. Similarly connection
        between the vertices could also be found.

        The disadvantage with this effort is that it is not space efficient and
        the matrix is always tends to be sparse. For these reasons all the problems
        given for the interviews will be based on Adjacency list.

    Adjacency List:
        The adjacency list contains the dictionary of {Vertex : Vertex List}
        Vertex List is intern a Class which has id (string) and connection
        dictionary with the vertex connected to with their cost.
    """

    def __init__(self):
        """Initializes the graph."""
        self._vertices_list = {}

    @property
    def num_of_vertices(self):
        return len(self._vertices_list)

    @property
    def vertices(self):
        """List of vertices connected to a graph."""
        return self._vertices_list.keys()

    def add_vertex(self, vertex):
        """Add vertex to the Graph

        :param vertex - New vertex to be added to the Graph
        """
        self._vertices_list[vertex] = Vertex(vertex)

    def add_edge(self, from_vertex, to_vertex, weight=0):
        """Add an edge between vertex

        :param from_vertex - from vertex
        :param to_vertex - to vertex
        :param weight - cost of the edge
        """
        if from_vertex not in self._vertices_list:
            self.add_vertex(from_vertex)

        if to_vertex not in self._vertices_list:
            self.add_vertex(to_vertex)

        self._vertices_list[from_vertex].add_neighbor(self._vertices_list[to_vertex],
                                                      weight)

    def get_vertex(self, vertex):
        return self._vertices_list[vertex]

    def __contains__(self, item):
        """Checks to see if there an edge connected to this vertex.

        :param item - The vertex to be checked
        :returns True - If there is a edge
                False - Otherwise
        """
        return item in self._vertices_list

    def __iter__(self):
        """Iterate through the vertices object"""
        return iter(self._vertices_list)
