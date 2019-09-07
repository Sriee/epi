from collections import defaultdict

from lib.graph import Graph, Vertex
from lib.queue import Queue


class WVertex(Vertex):

    def __init__(self, label):
        """Initialize the Word ladder vertex."""
        super().__init__(label)
        self._distance = 0
        self._predecessor = None
        self._visited = False

    @property
    def distance(self):
        return self._distance

    @distance.setter
    def distance(self, value):
        self._distance = value

    @property
    def predecessor(self):
        return self._predecessor

    @predecessor.setter
    def predecessor(self, value):
        self._predecessor = value

    @property
    def visited(self):
        return self._visited

    @visited.setter
    def visited(self, value):
        self._visited = value

    def __str__(self):
        nbr = ', '.join([x.label for x in self._neighbors])
        pred = self._predecessor.label if self._predecessor else None
        return '{0} is connected to: [{1}], pred: {2}, dist: {3}'\
            .format(self._label, nbr, pred, self.distance)


class WGraph(Graph):

    def __init__(self):
        super().__init__()

    def add_vertex(self, vertex):
        """Add vertex to the Graph

        :param vertex - New vertex to be added to the Graph
        """
        self._vertices_list[vertex] = WVertex(vertex)

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


g = WGraph()


def breath_first_search(start: WVertex):
    """
    Breadth first search the world ladder graph. While discovering nodes mark it's
    predecessor and it's distance from the starting vertex.

    Note than both distance and predecessor changes based on your starting vertex

    :param start: Starting vertex
    """
    queue = Queue()
    queue.enqueue(start)

    while queue.size() > 0:
        current_vtx = queue.deque()
        for nbr in current_vtx.neighbors:
            if not nbr.visited:
                nbr.distance = current_vtx.distance + 1
                nbr.predecessor = current_vtx
                queue.enqueue(nbr)
        current_vtx.visited = True


def traverse(start):
    """
    Helper function to print world ladder

    :param start: Vertex to print the world ladder
    """
    itr = start
    while itr.predecessor:
        print(itr.label)
        itr = itr.predecessor
    print(itr.label)


if __name__ == '__main__':
    bucket, words = defaultdict(list), ['fool', 'foul', 'foil', 'fail', 'fall', 'cool',
                                        'pool', 'pall', 'poll', 'pole', 'pope', 'pale',
                                        'sale', 'sage', 'page']

    for word in words:
        for i in range(4):
            b = word[: i] + '_' + word[i + 1:]
            bucket[b].append(word)

    # Populate the graph
    for b in bucket:
        # print(b, bucket[b])
        for word1 in bucket[b]:
            for word2 in bucket[b]:
                if word1 != word2:
                    g.add_edge(word1, word2)

    # Build predecessor and distance relation
    breath_first_search(g.get_vertex("fool"))
    traverse(g.get_vertex("sage"))
