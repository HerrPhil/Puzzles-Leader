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

See [streaming algorithm](https://en.wikipedia.org/wiki/Streaming_algorithm) wikipedia page here.

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

