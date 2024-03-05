package lab2;

import java.util.Objects;

public class Client {
    private String name;
    private ClientType type;
    private int startTime;
    private int endTime;

    public Client(String name, ClientType type, int startTime, int endTime) {
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name=" + name +
                ", type=" + type +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Client))
            return false;
        Client other = (Client)obj;
        return Objects.equals(name,other.name);
    }
}
