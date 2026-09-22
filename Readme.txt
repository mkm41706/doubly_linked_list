Name: Morgan Mallard
Email: mkm41706@uga.edu
MyID: 811826814

To Compile from project root(Mallard_assignment2):

   javac -d bin src/*.java

To Run from project root(Mallard_assignment2):

   java -cp bin DoublyLinkedListDriver <input.txt>


Steps for deleteSubsection:    Big-O = O(n)

      Step 1: If head is null, stop.
      Step 2: Set location = head.
      Step 3: While location is not null AND location.info < lower:
      	      	   location = location.next
      Step 4: If location is null, stop.
      Step 5: Set start = location
      Step 6: While location is not null AND location.info <= upper
      	         location = location.next
              (Stop when we pass upper bound)
      Step 7: Set endNext = location
      Step 8: Set startPrev = start.back
      Step 9: If startPrev is not null:
                 startPrev.next = endNext
	      Else:
	         head = endNext (deleted from the head)
      Step 10: If endNext is not null:
      	          endNext.back = startPrev

     In the algorithm above we can see that each node is visited
only once. First, it moves to the first node that is greater than
or equal to the lower bound. Then it continues moving forward
until it passes the upper bound. So, no nested loops and all
pointers are constant time. Therefore, the total work is O(n).


Steps for reverseList:      Big-O = O(n)

      Step 1: If head is null, stop.
      Step 2: Set location = head.
              Set prev = null.
      Step 3: While location is not null:
              a. Save next = location.next
	      b. Flip location.next to point to prev
	      c. Flip location.back to point to next
	      d. Move prev to location
	      e. Move location to next

      Step 4: Set head = prev (new head of the reversed list.)

      In the algorithm above we can see that each node is visited
only once. Each visit performs a constant amount of pointer
flipping. There are no nested loops. Therefore, the total work
is O(n).


Steps for swapAlternate:    Big-O = O(n)

      Step 1: If head is null OR head.next is null, stop.
      Step 2: Set first = head
              Set second = head.next
	      Set prevTail = null
      Step 3: Update head = second (after the first swap)
      Step 4: While first and second are not null:
              a. Save nextPair = second.next
	      b. Swap the pair:
	            second.next = first
		    first.back = second
	      c. Connect previous swapped pair:
	            If prevTail is not null:
		       prevTail.next = second
		       second.back = prevTail
		    Else:
		       second.back = null
	      d. Connect the swapped pair to the next pair:
	            If nextPair is not null:
		       first.next = nextPair
		       nextPair.back = first
		    Else:
		       first.next = null
	      e. Move prevTail to first
	      f. Move first to nextPair
	      g. If first is not null:
	            second = first.next

      In the algorithm above the list is traversed through pair by
pair. Each pair is processed once and each swap involves only
costant time pointer updates. There are no nested loops. Therefore,
the work is O(n) even though the logic is more detailed than the
other functions.
