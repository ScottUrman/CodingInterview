package InterviewCake;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

// https://www.interviewcake.com/question/java/merging-ranges?course=fc1&section=array-and-string-manipulation
public class MergingMeetingTimes {
  public static class Meeting {

    private int startTime;
    private int endTime;

    public Meeting(int startTime, int endTime) {
      // number of 30 min blocks past 9:00 am
      this.startTime = startTime;
      this.endTime = endTime;
    }

    public int getStartTime() {
      return startTime;
    }

    public void setStartTime(int startTime) {
      this.startTime = startTime;
    }

    public int getEndTime() {
      return endTime;
    }

    public void setEndTime(int endTime) {
      this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (!(o instanceof Meeting)) {
        return false;
      }
      final Meeting meeting = (Meeting) o;
      return startTime == meeting.startTime && endTime == meeting.endTime;
    }

    @Override
    public int hashCode() {
      int result = 17;
      result = result * 31 + startTime;
      result = result * 31 + endTime;
      return result;
    }

    @Override
    public String toString() {
      return String.format("(%d, %d)", startTime, endTime);
    }

    public boolean overlaps(Meeting meeting) {
      return ((meeting.getStartTime() >= this.startTime) && (meeting.getStartTime() <= this.endTime)) ||
          ((this.startTime >= meeting.getStartTime()) && this.startTime <= meeting.getEndTime());
    }
  }

  // O(n^2), doesn't sort
  public static List<Meeting> mergeRangesFirstTry(List<Meeting> meetingList) {
    List<Meeting> resultList = new ArrayList<>(Collections.emptyList());
    for (Meeting candidateMeeting : meetingList) {
      if (resultList.isEmpty()) {
        resultList.add(candidateMeeting);
      } else {
        boolean foundOverlap = false;
        for (Meeting resultMeeting : resultList) {
          if (!foundOverlap) {
            if (resultMeeting.overlaps(candidateMeeting)) {
              // Merge
              resultMeeting.setStartTime(Math.min(resultMeeting.getStartTime(), candidateMeeting.getStartTime()));
              resultMeeting.setEndTime(Math.max(resultMeeting.getEndTime(), candidateMeeting.getEndTime()));
              foundOverlap = true;
            }
          }
        }

        if (!foundOverlap) {
          resultList.add(candidateMeeting);
        }
      }
    }

    return resultList;
  }

  public static List<Meeting> mergeRanges(List<Meeting> meetingList) {
    List<Meeting> sortedMeetingList = new ArrayList<>(meetingList);
    Collections.copy(sortedMeetingList, meetingList);

    sortedMeetingList.sort(new Comparator<Meeting>() {
      @Override
      public int compare(Meeting o1, Meeting o2) {
        return Integer.compare(o1.startTime, o2.startTime);
      }
    });

    List<Meeting> resultList = new ArrayList<>(Collections.emptyList());
    Meeting resultMeeting = null;
    for (Meeting candidateMeeting : sortedMeetingList) {
      if (resultMeeting == null) {
        resultMeeting = candidateMeeting;
      } else {
        if (resultMeeting.overlaps(candidateMeeting)) {
          // Merge
          resultMeeting.setStartTime(Math.min(resultMeeting.getStartTime(), candidateMeeting.getStartTime()));
          resultMeeting.setEndTime(Math.max(resultMeeting.getEndTime(), candidateMeeting.getEndTime()));
        } else {
          // Add last result to the final list, set result to candiate
          resultList.add(resultMeeting);
          resultMeeting = candidateMeeting;
        }
      }
    }

    // Add last result
    resultList.add(resultMeeting);
    return resultList;
  }
  @Test
  public void test() {
    System.out.println(new Meeting(4, 10).overlaps(new Meeting(3, 6)));

  }
  @Test
  public void meetingsOverlapTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(2, 4));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 4));
    assertEquals(expected, actual);
  }

  @Test
  public void  meetingsTouchTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(5, 6), new Meeting(6, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(5, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingContainsOtherMeetingTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 8), new Meeting(2, 5));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingsStaySeparateTest() {
    final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(4, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(
        new Meeting(1, 3), new Meeting(4, 8)
    );
    assertEquals(expected, actual);
  }

  @Test
  public void multipleMergedMeetingsTest() {
    final List<Meeting> meetings = Arrays.asList(
        new Meeting(1, 4), new Meeting(2, 5), new Meeting (5, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
    assertEquals(expected, actual);
  }

  @Test
  public void meetingsNotSortedTest() {
    final List<Meeting> meetings = Arrays.asList(
        new Meeting(5, 8), new Meeting(1, 4), new Meeting(6, 8));
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(
        new Meeting(1, 4), new Meeting(5, 8)
    );
    assertEquals(expected, actual);
  }

  @Test
  public void oneLongMeetingContainsSmallerMeetingsTest() {
    final List<Meeting> meetings = Arrays.asList(
        new Meeting(1, 10), new Meeting(2, 5), new Meeting(6, 8),
        new Meeting(9, 10), new Meeting(10, 12)
    );
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(
        new Meeting(1, 12)
    );
    assertEquals(expected, actual);
  }

  @Test
  public void sampleInputTest() {
    final List<Meeting> meetings = Arrays.asList(
        new Meeting(0, 1), new Meeting(3, 5), new Meeting(4, 8),
        new Meeting(10, 12), new Meeting(9, 10)
    );
    final List<Meeting> actual = mergeRanges(meetings);
    final List<Meeting> expected = Arrays.asList(
        new Meeting(0, 1), new Meeting(3, 8), new Meeting(9, 12)
    );
    assertEquals(expected, actual);
  }

}
