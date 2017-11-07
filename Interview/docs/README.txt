*********************************************************
*			CE 6302 - Microprocessor Systems
*				Assignment - 3
*
To use gettimeofday() function use 
	gcc interrupt.c -o interrupt
	gcc poll.c -o poll

To use time() function use
	gcc interrupt.c -D HAVE_TIME -o interrupt
	gcc poll.c -D HAVE_TIME -o poll
