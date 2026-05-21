package com.example.demo.problems.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class MergeIntervals {
//    input :  [[1, 3], [2, 6], [8, 10]],
//    output :  [[1, 6], [8, 10]].
//    Merge all overlapping intervals.
//    logic :
//    Approach
//    Sort intervals by start time
//    Keep first interval in result
//    Compare current interval with last merged interval
//    If overlap:
//    merge using max end
//    Else:
//    add new interval

//      eg.
//      Explanation:
//      [1,3] and [2,6] overlap because:
//      2 <= 3
//      So they are merged into:
//      [1,6]
//      Remaining intervals do not overlap.

    public static void main(String[] arg){
        int [][] input_arr = {{2,6},{8,10},{1,3}};
        int[][] output_arr = MergeIntervals.solutionMergeIntervals(input_arr);
        System.out.println(Arrays.stream(output_arr).map(Arrays::toString).collect(Collectors.toList()));
    }

    public static int[][] solutionMergeIntervals(int[][] intervals){
        //sorting the array using comparator
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0],b[0]));
        //[[1, 3], [2, 6], [8, 10]]
        System.out.println(Arrays.stream(intervals).map(Arrays::toString).collect(Collectors.toList()));
        List<int[]> resultList = new ArrayList<>();
        resultList.add(intervals[0]);
        // add first
        // resultList: {[1,3]]
        for(int i = 1; i < intervals.length; i++){
          //  size = 1 -> (size -1 ) -> 1-1 = 0
          //  resultList.get(0)
          //  currently result only have one value - [1,3]
          // last = [1,3]
          int[] last = resultList.get(resultList.size() - 1);
          //current -> [2, 6]  [for first step hint only ]
          int[] current = intervals[i];

          // current[0] -> 2, last[1] -> 3 ; 2 <= 3
          if(current[0] <= last[1]){
              // last[1] :- max(3, 6) ; here max (6)
              // so here it got replace and merge - [1,3] : [2,6] -> [1, 6]
              last[1] = Math.max(last[1], current[1]);
          } else {
              resultList.add(current);
          }
        }
        return resultList.toArray(new int[resultList.size()][]);
    }

//    2. Insert Interval
//    Problem
//    Insert a new interval into existing non-overlapping sorted intervals and merge if necessary.
//    Input:  [[1,3],[6,9]]
//    newInterval = [2,5]
//    Output:[[1,5],[6,9]]
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

//    3. Meeting Rooms
//    Problem
//    Determine if a person can attend all meetings.
//    Example [[0,30],[5,10],[15,20]]
//    Output: false
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

//    //4. Non-overlapping Intervals
//    [[1,2],[2,3],[3,4],[1,3]]
//    Output: 1
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int count = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                count++;
            } else {
                end = intervals[i][1];
            }
        }
        return count;
    }

    // 5. Interval Intersection
//    A = [[0,2],[5,10]]
//    B = [[1,5],[8,12]]
//    Output:  [[1,2],[5,5],[8,10]]
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            int start = Math.max(A[i][0], B[j][0]);
            int end = Math.min(A[i][1], B[j][1]);
            if (start <= end) {
                result.add(new int[]{start, end});
            }
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

//    6. Employee Free Time
//    Employee 1: [1,3],[6,7]
//    Employee 2: [2,4]
//    Employee 3: [2,5],[9,12]
//    Output:  [5,6],[7,9]
    public List<int[]> employeeFreeTime(List<int[][]> schedule) {
        List<int[]> all = new ArrayList<>();
        for (int[][] emp : schedule) {
            all.addAll(Arrays.asList(emp));
        }
        all.sort((a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        merged.add(all.get(0));
        for (int i = 1; i < all.size(); i++) {
            int[] last = merged.get(merged.size() - 1);
            int[] current = all.get(i);
            if (current[0] <= last[1]) {
                last[1] = Math.max(last[1], current[1]);
            } else {
                merged.add(current);
            }
        }
        List<int[]> free = new ArrayList<>();
        for (int i = 1; i < merged.size(); i++) {
            free.add(new int[]{
                    merged.get(i - 1)[1],
                    merged.get(i)[0]
            });
        }
        return free;
    }

//    7. Minimum Meeting Rooms Required
//    [[0,30],[5,10],[15,20]]
//    Output: 2
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() &&
                    interval[0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(interval[1]);
        }
        return minHeap.size();
    }
}
