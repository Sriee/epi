from collections import defaultdict, deque

from lib.graph import Graph, Vertex
from lib.queue import Queue


class WVertex(Vertex):

    def __init__(self, label):
        """Initialize the Word ladder vertex."""
        super().__init__(label)
        self._distance = 0
        self._predecessor = None

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

    def __str__(self):
        nbr = ', '.join([x.label for x in self._neighbors])
        pred = self._predecessor.label if self._predecessor else None
        return '{0} is connected to: [{1}], pred: {2}, dist: {3}' \
            .format(self._label, nbr, pred, self.distance)


class WGraph(Graph):

    def __init__(self):
        super().__init__()

    def add_vertex(self, vertex):
        """Add vertex to the Graph

        :param vertex - New vertex to be added to the Graph
        """
        self._vertices_list[vertex] = WVertex(vertex)


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


def ladder_length(begin_word, end_word, word_list):
    """
    Leet code. Solution -> Accepted

    Run Time: 132 ms. Above Average Run Time. Solution without using Graph class

    Given two words (begin and end), and a dictionary's word list, find the length of
    shortest transformation sequence from begin word to end word, such that:

        1. Only one letter can be changed at a time.
        2. Each transformed word must exist in the word list. Note that beginWord is not
        a transformed word.

    :param begin_word: Start node
    :param end_word: end node
    :param word_list: list of words of same length
    :return: 0 if there is no path else shortest transformation between start node and
    end node
    """
    lew, bucket, queue, is_visited = len(begin_word), defaultdict(list), deque(), {}
    word_list.append(begin_word)

    for word in word_list:
        for i in range(lew):
            w = word[:i] + '_' + word[i + 1:]
            bucket[w].append(word)

    queue.append((begin_word, 1))
    is_visited[begin_word] = True

    while queue:
        vertex, distance = queue.popleft()
        for i in range(lew):
            word = vertex[:i] + '_' + vertex[i + 1:]

            for nbr in bucket[word]:
                if nbr not in is_visited:
                    if nbr == end_word:
                        return distance + 1

                    is_visited[nbr] = True
                    queue.append((nbr, distance + 1))
            bucket[word] = []

    return 0


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
