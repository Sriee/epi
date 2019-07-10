import heapq

from collections import defaultdict, Counter


class FreqStackHeap(object):
    """
    Leet code solution. Timing out. Execution result is correct but times out for
    exceptionally high number of inputs.
    """
    def __init__(self):
        self._mem = {}
        self.heap = []
        self._idx = 0

    def push(self, x):
        self._idx -= 1

        if x in self._mem:
            _temp = []
            while self.heap[0][2] != x:
                _temp.append(heapq.heappop(self.heap))

            found = heapq.heappop(self.heap)
            self._mem[x][0] += 1
            self._mem[x][1].append(found[1])
            _temp.append((found[0] - 1, self._idx, found[2]))

            while _temp:
                heapq.heappush(self.heap, _temp.pop())
        else:
            self._mem[x] = [1, [self._idx]]
            heapq.heappush(self.heap, (-1, self._idx, x))

    def pop(self):
        top = heapq.heappop(self.heap)
        if top[0] + 1 == 0:
            del self._mem[top[2]]
        else:
            self._mem[top[2]][0] -= 1
            heapq.heappush(self.heap, (top[0] + 1, self._mem[top[2]][1].pop(), top[2]))

        return top[2]


class FreqStack(object):
    """
    Leet code solution. Problem -> Accepted.
    """
    def __init__(self):
        self.freq = Counter()
        self.mem = defaultdict(list)
        self.max_f = 0

    def push(self, x):
        self.freq[x] += 1
        self.max_f = max(self.max_f, self.freq[x])
        self.mem[self.freq[x]].append(x)

    def pop(self):
        x = self.mem[self.max_f].pop()
        if not self.mem[self.max_f]:
            self.max_f -= 1

        self.freq[x] -= 1
        return x


obj = FreqStack()
obj.push(5)
obj.push(1)
obj.push(2)
obj.push(5)
obj.push(5)
obj.push(5)
obj.push(1)
obj.push(6)
obj.push(1)
obj.push(5)

print(obj.pop())
print(obj.pop())
print(obj.pop())
print(obj.pop())
print(obj.pop())
print(obj.pop())
print(obj.pop())
print(obj.pop())
print(obj.pop())
print(obj.pop())