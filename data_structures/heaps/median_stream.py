import heapq


class MedianFinder(object):

    def __init__(self):
        self.min_heap, self.max_heap = [], []

    def add_num(self, val):
        heapq.heappush(self.min_heap, val)
        heapq.heappush(self.max_heap, -heapq.heappop(self.min_heap))

        if len(self.max_heap) > len(self.min_heap):
            heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))

    def find_median(self):
        if len(self.min_heap) == len(self.max_heap):
            res = (self.min_heap[0] - self.max_heap[0]) / 2
        else:
            res = self.min_heap[0]
        return float(res)


a = MedianFinder()
a.add_num(1)
a.add_num(2)
print(a.find_median())
a.add_num(3)
c = a.find_median()
print(c, type(c))
