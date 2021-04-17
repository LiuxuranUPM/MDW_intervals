package usantatecla;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntervalTest {

  private Point left = new Point(-2.2);
  private Point right = new Point(4.4);
  private IntervalBuilder intervalBuilder;

  @BeforeEach
  public void before() {
    this.left = new Point(-2.2);
    this.right = new Point(4.4);
    this.intervalBuilder = new IntervalBuilder();
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));
    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenInc3ludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWit3hIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithInclude5dValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenTwoIntervalsOpenOpenWhenValueIntersectionThenFalse() {
    Interval firstInterval = new IntervalBuilder().open(left.getLess()).open(left.getGreater()).build();
    Interval secondInterval = new IntervalBuilder().open(right.getLess()).open(right.getGreater()).build();
    assertFalse(firstInterval.intersects(secondInterval));
    firstInterval = new IntervalBuilder().open(left.getLess()).open(left.getGreater()).build();
    secondInterval = new IntervalBuilder().open(left.getGreater()).open(right.getGreater()).build();
    assertFalse(firstInterval.intersects(secondInterval));
  }

  @Test
  public void givenTwoIntervalsOpenOpenWhenValueIntersectionThenTrue() {
    Interval firstInterval = new IntervalBuilder().open(left.getEquals()).open(right.getEquals()).build();
    Interval secondInterval = new IntervalBuilder().open(right.getLess()).open(right.getGreater()).build();
    assertTrue(firstInterval.intersects(secondInterval));
    firstInterval = new IntervalBuilder().open(left.getLess()).open(left.getGreater()).build();
    secondInterval = new IntervalBuilder().open(left.getEquals()).open(right.getEquals()).build();
    assertTrue(firstInterval.intersects(secondInterval));
  }

  @Test
  public void givenTwoIntervalsCloseCloseWhenValueIntersectionThenFalse() {
    Interval firstInterval = new IntervalBuilder().closed(left.getLess()).closed(left.getGreater()).build();
    Interval secondInterval = new IntervalBuilder().closed(right.getLess()).closed(right.getGreater()).build();
    assertFalse(firstInterval.intersects(secondInterval));
  }

  @Test
  public void givenTwoIntervalsCloseCloseWhenValueIntersectionThenTrue() {
    Interval firstInterval = new IntervalBuilder().closed(left.getLess()).closed(left.getGreater()).build();
    Interval secondInterval = new IntervalBuilder().closed(left.getGreater()).closed(right.getGreater()).build();
    assertTrue(firstInterval.intersectsClosed(secondInterval));
    firstInterval = new IntervalBuilder().closed(left.getLess()).closed(left.getGreater()).build();
    secondInterval = new IntervalBuilder().closed(left.getEquals()).closed(right.getEquals()).build();
    assertTrue(firstInterval.intersectsClosed(secondInterval));
  }

  @Test
  public void givenTwoIntervalsOpenCloseWhenValueIntersectionThenFalse(){
    Interval firstInterval = new IntervalBuilder().open(left.getLess()).open(left.getGreater()).build();
    Interval secondInterval = new IntervalBuilder().closed(right.getLess()).closed(right.getGreater()).build();
    assertFalse(firstInterval.intersects(secondInterval));
    firstInterval = new IntervalBuilder().open(left.getLess()).open(right.getLess()).build();
    secondInterval = new IntervalBuilder().closed(right.getLess()).closed(right.getGreater()).build();
    assertFalse(firstInterval.intersects(secondInterval));
  }

  @Test
  public void givenTwoIntervalsOpenCloseWhenValueIntersectionThenTrue(){
    Interval firstInterval = new IntervalBuilder().open(left.getLess()).open(right.getEquals()).build();
    Interval secondInterval = new IntervalBuilder().closed(right.getLess()).closed(right.getGreater()).build();
    assertTrue(firstInterval.intersects(secondInterval));
  }
}