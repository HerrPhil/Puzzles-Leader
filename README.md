# Puzzles-Leader

The leader of this sequence is the element whose value occurs more than n 2 times.

## Background

This problem is based on a lot of algorithm research.

The "leader" concept is an applied solution of the Boyer-Moore majority vote algorithm.

The Boyer-Moore majority vote algorithm is a prototypical example of a streaming algorithm.

In computer science, streaming algorithms are algorithms for processing data streams
in which the input is presented as a sequence of items and can be examined in only a few passes,
typically just one. These algorithms are designed to operate with limited memory,
generally logarithmic in the size of the stream and/or in the maximum value of the stream,
and may also have limited processing time per item.

See [Boyer-More majorith vote algorithm](https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm) wikipedia page here.

See [geeks-for-geeks Boyer-Moore vote algorithm](https://www.geeksforgeeks.org/boyer-moore-majority-voting-algorithm/) page, too.

See [geeks-for-geeks Majority Element](https://www.geeksforgeeks.org/majority-element/) page, too.

See [streaming algorithm](https://en.wikipedia.org/wiki/Streaming_algorithm) wikipedia page here.

Note: The Leader problem is, in actual fact, a simplified name of a special case of the streaming algorithm.

In the data stream model, the frequent elements problem is to output a set of elements that constitute more than some fixed fraction of the stream.
A special case is the majority problem, which is to determine whether or not any value constitutes a majority of the stream.

See [codility leader note](https://codility.com/media/train/6-Leader.pdf) PDF page here.

## Various Problems

### Core Leader Problem

Let us consider a sequence a<sub>0</sub>, a<sub>1</sub>, ..., a<sub>n-1</sub>.
The leader of this sequence is the element whose value occurs more than n/2 times.

```
a0 a1 a2 a3 a4 a5 a6
6  8  4  6  8  6  6
0  1  2  3  4  5  6
```

In the picture the leader is the "6" value.
Notice that the leader can have at most one leader.
If there were two leaders then there total occurrences would be more than 2*(n/2) = n,
but we only have *n* elements.

The leader may be found in many ways. We describe some methods here,
starting with trivial, slow ideas and ending with very creative, fast algorithms.
The task is to find the value of the leader of the sequence
a<sub>0</sub>, a<sub>1</sub>, ..., a<sub>n-1</sub>,
such that 0 <= a<sub>i</sub> <= 10<sup>9</sup>.

If there is no leader, the result should be -1.

#### Solution with O(n<sup>2</sup>) time complexity

We count the occurrences of every element:

```
def slowLeader(A):
    n = len(a)
    leader = -1
    for k in xrange(n):
        candidate = A[k]
        count = 0
        for i in xrange(n):
            if (A[i] == candidate):
                count += 1
        if ( count > n / 2):
            leader = candidate
    return leader
```

#### Solution with O(*n* log *n*) time complexity

If the sequence is presented in non-decreasing order,
then identical values are adjacent to each other.

```
a0 a1 a2 a3 a4 a5 a6
4  6  6  6  6  8  8
0  1  2  3  4  5  6
```

Having sorted the sequence, we can easily count slices of the same values
and find the leader in a smarter way.
Notice that if the leader occurs somewhere in our sequence,
then it must occur at index n / 2 (the central element).
This is because, given that the leader occurs in more than half
the total values in the sequence, there are more leader values
than will fit on either side of the central element in the sequence.

```
def fastLeader(A):
    n = len(a)
    leader = -1
    A.sort()
    candidate = A[n / 2]
    count = 0
    for i in xrange(n):
        if (A[i] == candidate):
            count += 1
    if (count > n / 2):
        leader = candidate
    return leader
```

The time complexity of the above algorithm is O( *n* log *n* ) due to the sorting time.

#### Solution with O(n) time complexity

Notice that if the sequence a<sub>0</sub>, a<sub>1</sub>, ..., a<sub>n-1</sub> contains a leader,
then after removing a pair of elements of different values, the remaining sequence still has the same leader.
Indeed, if we remove two different elements then only one of them could be the leader.
The leader in the new sequence occurs more than n / 2 - 1 = (n - 2)/2 times.
Consequently, it is still the new leader of the new sequence of n - 2 elements.

```
a0 a1
4  6
0  1
      a2 a3 a4 a5 a6
      6  6  6  8  8
      2  3  4  5  6
```

Removing pairs of different elements is not trivial.
Let's create an empty stack onto which we will be pushing consecutive elements.
After each such operation we check whether the two elements at the top of the stack are different.
If they are, we remove them from the stack.
This is equivalent to removing a pair of different elements from the sequence.

                      8x
                   6  6x 8x
          6x    6  6  6  6x
       4  4x 6  6  6  6  6

       a0 a1 a2 a3 a4 a5 a6
       4  6  6  6  6  8  8

In fact, we do not need to remember all the elements from the stack,
because all the values below the top are always equal.
It is sufficient to remember only the values of elements and the size of the stack.

```
def goldenLeader(A):
    n = len(A)
    size = 0
    for k in xrange(n):
        if (size == 0):
            size += 1
            value = A[k]
        else
            if (value != A[k]):
                size -= 1
            else:
                size += 1
    candidate = -1
    if (size > 1):
        candidate = value
    leader = -1
    count = 0
    for k in xrange(n):
        if (A[k] == candidate):
            count += 1
    if (count > n / 2):
        leader = candidate
    return leader
```

At the beginning we notice that if the sequence contains a leader,
then after the removal of different elements the leader will not have changed.
After removing all pairs of different values, we end up with a sequence containing all the same values.
This value is not necessarily the leader; it is only a candidate for the leader.
Finally, we should iterate through all the elements and count occurrences of the candidate;
if it is greater than n / 2 then we have found the leader;
otherwise the sequence does not contain a leader.

The time complexity of this algorithm is O(*n*) because every element is considered only once.
The final counting of occurrences of the candidate value also works n O(*n*) time.

My notes:

The golden leader is a fabricated name by the Codility authors.
The underlying work is that of Robert Stephen Boyer and J Strother Moore.
Both are accomplished PhD professors and researchers.

For any company to suggest that anyone who cannot create a solution equal to or better than
the work of two Phd professors in the alloted 100 minutes codility provides for questions
is acting irresponsibly.

At the very least, such companies are presenting as smarter than they actually are,
and are exploiting their bought access to high-level research to eliminate candidate with false credibility.

As well, the definition of the stack in this solution is nothing like a "Stack" in java.

It is tracked by a previous value, the current indexed value and the size (frequency) of the current value.

### Codility Leader Problems

There are two practice problems [here](https://app.codility.com/programmers/lessons/8-leader/).

#### Dominator

Find an index of an array such that its value occurs at more than half of indices in the array.

This is effectively the golden leader solution. One change to make is in the return value.

Instead of the solution returning the leader value itself, return the index of, say, the first occurrence of the leader value.

#### EquiLeader

Find the index S such that the leaders of the sequences A[0], A[1], ..., A[S] and A[S+1], A[S+2], ..., A[N-1] are the same.

This is effectively the golden leader solution, applied to a series of sub-arrays of A, bisected by index S.

Check sub-arrays for common index S where S = {0, 1, 2, ..., N - 1}
