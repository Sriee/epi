class Vertex:
    def __init__(self, key):
        """Initialize the vertex."""
        self.id = key
        self.connected_to = {}

    def add_neighbor(self, neigh, weight=0):
        """Adds the vertex to the list of connected vertices

        :param neigh - The vertex to be connected
        :param weight- Weight of the connection
        """
        self.connected_to[neigh] = weight

    def get_id(self):
        """Returns the id of the vertex."""
        return self.id

    def get_connections(self):
        """Return the vertices of the graph."""
        return self.connected_to.keys()

    def get_weight(self):
        """Return the weight of the vertices."""
        return self.connected_to.values()

    def __contains__(self, item):
        """
        Checks if the given vertex is connected to this vertex

        :param item: The vertex to be searched
        :return: True - If the given vertex is connected to this vertex
                False - Otherwise
        """
        return item in self.connected_to.keys()

    def __delattr__(self, item):
        """Delete's the vertex from the connected list."""
        if item in self.connected_to.keys():
            del self.connected_to[item]
        else:
            raise KeyError

    def __str__(self):
        """Prints the vertex list connected to this vertex."""
        return str(self.id + "is connected to " + [x.id for x in
                                                   self.connected_to] + ".\n")


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
        self.vertices_list = {}
        self.num_of_vertices = 0

    def add_vertex(self, vx):
        """Add vertex to the Graph

        :param vx - New vertex to be added to the Grapg
        """
        new_vertex = Vertex(vx)
        self.vertices_list[vx] = new_vertex
        self.num_of_vertices += 1
        return new_vertex

    def get_vertex(self, vx):
        """The vertices object connected to this vertex

        :param vx: The vertex key
        :return: The list of vertex object
        """
        if vx in self.vertices_list:
            return self.vertices_list[vx]
        else:
            return None

    def get_vertices(self):
        """List of the vertices connected a vertex."""
        return self.vertices_list.keys()

    def add_edge(self, fromV, toV, weight=0):
        """Add an edge between vertex

        :param fromV - from vertex
        :param toV - to vertex
        :param weight - cost of the edge
        """
        if fromV not in self.vertices_list:
            self.add_vertex(fromV)
        if toV not in self.vertices_list:
            self.add_vertex(toV)

        self.vertices_list[fromV].add_neighbor(self.vertices_list[toV], weight)

    def __contains__(self, item):
        """Checks to see if there an edge connected to this vertex.

        :param item - The vertex to be checked
        :returns True - If there is a edge
                False - Otherwise
        """
        return item in self.vertices_list

    def __itr__(self):
        """Iterate through the vertices object"""
        for itm in self.vertices_list.values():
            yield itm
