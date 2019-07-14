import lib.graph as g
import unittest


class TestGraph(unittest.TestCase):

    def setUp(self):
        self.test_graph = g.Graph()
        for i in range(1, 7):
            self.assertIsNotNone(self.test_graph.add_vertex(i))

    def test_vertex_connection(self):
        res = [x for x in range(1, 7)]
        self.assertEqual(res, self.test_graph.get_vertices())

    def test_vertex_in(self):
        for i in range(1, 7):
            self.assertTrue(i in self.test_graph)

    def test_add_edge(self):
        self.test_graph.add_edge(0, 1, 5)
        self.test_graph.add_edge(0, 5, 2)
        self.test_graph.add_edge(1, 2, 4)
        self.test_graph.add_edge(2, 3, 9)
        self.test_graph.add_edge(3, 4, 7)
        self.test_graph.add_edge(3, 5, 3)
        self.test_graph.add_edge(4, 0, 1)
        self.test_graph.add_edge(5, 4, 8)
        self.test_graph.add_edge(5, 2, 1)

        for vx_object in self.test_graph.vertices_list.values():
            self.assertIsInstance(vx_object, g.Vertex)

        check_connection = [(0, 5), (0, 1), (1, 2), (2, 3), (3, 4), (3, 5),
                            (4 ,0), (5, 4), (5, 2)]

        for v in self.test_graph.vertices_list.values():
            for w in v.get_connections():
                self.assertIn((v.get_id(), w.get_id()), check_connection)


if __name__ == "__main__":
    unittest.main()