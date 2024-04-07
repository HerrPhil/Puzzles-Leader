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

#### Solution witn O(*n* log *n*) time complexity

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


