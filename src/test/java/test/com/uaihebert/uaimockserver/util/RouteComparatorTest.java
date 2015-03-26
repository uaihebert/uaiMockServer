package test.com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiHeader;
import com.uaihebert.uaimockserver.model.UaiRequest;
import com.uaihebert.uaimockserver.model.UaiRoute;
import com.uaihebert.uaimockserver.util.RouteComparator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RouteComparatorTest {
    private final Comparator<UaiRoute> comparator = new RouteComparator();

    @Test
    public void isWorkingWhenSecondHasMoreThanFirst() {
        final List<UaiRoute> toSort = new ArrayList<UaiRoute>();

        final UaiRoute first = new UaiRoute();
        first.createId();
        final UaiRequest firstRequest = new UaiRequest
                .UaiRequestBuilder()
                .requiredHeaderList(Arrays.asList(new UaiHeader("first", false, Arrays.asList("VAL"))))
                .build();

        first.setRequest(firstRequest);

        final UaiRoute second = new UaiRoute();
        second.createId();
        final UaiRequest secondRequest = new UaiRequest
                .UaiRequestBuilder()
                .requiredHeaderList(Arrays.asList(
                        new UaiHeader("second_01", false, Arrays.asList("VAL")),
                        new UaiHeader("second_02", false, Arrays.asList("VAL"))
                ))
                .build();

        second.setRequest(secondRequest);

        toSort.add(first);
        toSort.add(second);

        assertEquals("the first must be the first inserted", first.getId(), toSort.get(0).getId());

        Collections.sort(toSort, comparator);

        assertEquals("the first must the second that has more data", second.getId(), toSort.get(0).getId());
    }
}
