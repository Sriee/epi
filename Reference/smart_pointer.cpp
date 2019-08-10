#include<iostream>

/**
 * Why do we need to over load operators inside smart pointer implementation class? 
 * When users make use of our implemented smart pointer like below
 * 		SmartPtr<MyClass> myClass = new MyClass(4, 3);
 *
 * myClass object is of type SmartPtr. When users access variables using arrow operator or assign one pointer to another pointer
 * what will happen? 
 * 		myClass->variables/function - In this case SmartPtr itself doesn't have the member variables/functions. The pointer to object (*ptr) inside it will 
 * 		be the one having the desired member variables. Hence, we need to overload "-> operator" 
 *
 * 		Similarly we need to overload "* operator"
 * 		
 * 		SmartPtr<AnotherClass> myClass2 = myClass;
 * 		In this case, if we don't overide copy constructor then there will 2 copies of objects. 
 * 		Pitfalls of this: 
 * 		  - If we have a huge heap allocation inside it, the program will crash (due to exceeding memory limit) or will have two copies of it. 
 * 		  - When the function goes out of scope, our SmartPtr class will delete myClass2 copy of the object leaving a memory leak
 *     Hence to avoid this we need to implement copy constructor
 */

template<class T>
class SmartPtr {
	T *ptr_;	// Internal reference

	public:
		// Constructor which stores the reference
		SmartPtr(T *value_=NULL) { 
			std::cout << "Assigning value to interanal pointer" << std::endl;
			ptr_ = value_;
		}

		// Copy constructor
		SmartPtr(SmartPtr<T>& rhs) {
			// Transfer the ownership of rhs to this
			ptr_ = rhs.ptr_;

			rhs.ptr_ = NULL;
		}

		// Destructire to delete the reference
		~SmartPtr() { 
			std::cout << "Deleting memory for  internal pointer" << std::endl; 
			delete (ptr_); 
		}

		// Overload dereference operator so that we return our internal pointer  
		T & operator *() { return *ptr_; }

		// Overload arrow operator. Since this is a template class, objects to pointers
		// could be called as well. In order to access the memeber functions of these object
		// pointers we are overloading arrow operator
		T * operator ->() { return ptr_; }
};

class MyClass {
	int height, weight;
	
	public:	
		MyClass(int h, int w) { height = h; weight = w; }
		long area();
};

long MyClass :: area() {
	return this->height * this->weight;
}

int main() {
	SmartPtr<int> myInt = { new int() };
	*myInt = 20;

	std::cout << *myInt << std::endl;
	MyClass 
	SmartPtr<MyClass> myClass = new MyClass(4, 3);

	std::cout << "Area: " << myClass->area() << std::endl;

	
	return 0; 
}
