package web.lab4.mbeans;

public class CustomObject {
    private int allPoints;
    private int missPoints;
    private int checkPoints;

    public CustomObject() {
        this.allPoints = 0;
        this.missPoints = 0;
        this.checkPoints = 0;
    }

    public void addMissPoint() {
        missPoints++;
        allPoints++;
    }

    public void addCheckPoint() {
        checkPoints++;
        allPoints++;
    }

    public int getAllPoints() {
        return allPoints;
    }

    public int getMissPoints() {
        return missPoints;
    }

    public int getCheckPoints() {
        return checkPoints;
    }

    public void clear() {
        allPoints = 0;
        missPoints = 0;
        checkPoints = 0;
    }
}
