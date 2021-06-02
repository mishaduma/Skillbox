import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndexTest;
    RouteCalculator routeCalculator;

    Line line1, line2, line3;

    Station stationA, stationB, stationC, stationD, stationE, stationF, stationG;

    @Override
    protected void setUp() throws Exception {
        stationIndexTest = new StationIndex();

        stationIndexTest.addLine(line1 = new Line(1, "Blue"));
        stationIndexTest.addLine(line2 = new Line(2, "Black"));
        stationIndexTest.addLine(line3 = new Line(3, "Red"));

        line1.addStation(stationA = new Station("A", line1));
        line1.addStation(stationB = new Station("B", line1));
        line1.addStation(stationC = new Station("C", line1));
        line2.addStation(stationD = new Station("D", line2));
        line2.addStation(stationE = new Station("E", line2));
        line3.addStation(stationF = new Station("F", line3));
        line3.addStation(stationG = new Station("G", line3));

        stationIndexTest.addConnection(new ArrayList<Station>() {{
            add(stationA);
            add(stationD);
        }});
        stationIndexTest.addConnection(new ArrayList<Station>() {{
            add(stationC);
            add(stationF);
        }});

        routeCalculator = new RouteCalculator(stationIndexTest);
    }

    /* Схема тестового метро.
     *  A-----B-----C
     *  D           F
     *  |           |
     *  E           G
     */

    public void testGetShortestRoute() {
        List<Station> actual = routeCalculator.getShortestRoute(stationE, stationG);
        List<Station> expected = new ArrayList<Station>() {{
            add(stationE);
            add(stationD);
            add(stationA);
            add(stationB);
            add(stationC);
            add(stationF);
            add(stationG);
        }};
        assertEquals(expected, actual);
    }

    public void testCalculateDuration() {
        double actual = routeCalculator.calculateDuration(routeCalculator.getShortestRoute(stationE, stationG));
        double expected = 17.0;
        assertEquals(expected, actual);
    }

    public void testGetRouteOnTheLine() {
        List<Station> actual = routeCalculator.getShortestRoute(stationC, stationA);
        List<Station> expected = new ArrayList<Station>() {{
            add(stationC);
            add(stationB);
            add(stationA);
        }};
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection() {
        List<Station> actual = routeCalculator.getShortestRoute(stationB, stationG);
        List<Station> expected = new ArrayList<Station>() {{
            add(stationB);
            add(stationC);
            add(stationF);
            add(stationG);
        }};
        assertEquals(expected, actual);
    }

    public void testIsConnected() {
        double actual = routeCalculator.calculateDuration(routeCalculator.getShortestRoute(stationA, stationD));
        double expected = 3.5;
        assertEquals(expected, actual);
    }

    public void testGetRouteViaConnectedLine() {
        List<Station> actual = routeCalculator.getShortestRoute(stationD, stationF);
        List<Station> expected = new ArrayList<Station>() {{
            add(stationD);
            add(stationA);
            add(stationB);
            add(stationC);
            add(stationF);
        }};
        assertEquals(expected, actual);
    }

    public void testGetRouteWithTwoConnections() {
        List<Station> actual = routeCalculator.getShortestRoute(stationG, stationE);
        List<Station> expected = new ArrayList<Station>() {{
            add(stationG);
            add(stationF);
            add(stationC);
            add(stationB);
            add(stationA);
            add(stationD);
            add(stationE);
        }};
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
