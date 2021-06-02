import java.util.TreeSet;

public class WorkTime
{
    private TreeSet<TimePeriod> periods;

    /**
     * Set of TimePeriod objects
     */
    public WorkTime()
    {
        periods = new TreeSet<>();
    }

    public void addVisitTime(long visitTime)
    {
        TimePeriod newPeriod = new TimePeriod(visitTime, visitTime);
        for(TimePeriod period : periods)
        {
            if(period.compareTo(newPeriod) == 0)
            {
                period.appendTime(visitTime);
                return;
            }
        }
        periods.add(new TimePeriod(visitTime, visitTime));
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for(TimePeriod period : periods)
        {
            if(builder.length() != 0) {
                builder.append(", ");
            }
            builder.append(period);
        }
        return builder.toString();
    }
}
