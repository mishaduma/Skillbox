public class Station {
    private String lineId;
    private String name;

    public Station(String lineId, String name) {
        this.lineId = lineId;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getLineId(){
        return lineId;
    }
}