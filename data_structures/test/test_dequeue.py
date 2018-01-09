#! /usr/bin/python

from DS.lib import dequeue

if __name__ == "__main__":

   d = dequeue.Dequeue()

   print( d.isEmpty() )
   d.addRear(4)
   d.addRear('dog')
   d.addFront('cat')
   d.addFront(True)
   print( d.isEmpty() )
   print ""
   d.addRear(8.4)
   d.printDequeue()
   print( d.removeRear() )
   print( d.removeFront() )
   print ""
   d.printDequeue()
