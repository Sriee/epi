#! /usr/bin/python 

from DS.sort import insertion_sort

if __name__ == "__main__":
   
   ls = [54, 26, 93, 17, 77, 31, 44, 55, 20]
   print ls
   ins = insertion_sort.InsertionSort()
   print ins.sort( ls )
   
