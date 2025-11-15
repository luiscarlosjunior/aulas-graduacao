public class TV {
    private boolean isOn;
    private int volume;
    private int channel;

    public TV() {
        this.isOn = false;
        this.volume = 10; // Default volume
        this.channel = 1; // Default channel
    }

    public void powerToggle() {
        isOn = !isOn;
        System.out.println("TV is now " + (isOn ? "ON" : "OFF"));
    }

    public void volumeUp() {
        if (isOn && volume < 100) {
            volume++;
            System.out.println("Volume: " + volume);
        }
    }

    public void volumeDown() {
        if (isOn && volume > 0) {
            volume--;
            System.out.println("Volume: " + volume);
        }
    }

    public void channelUp() {
        if (isOn) {
            channel++;
            System.out.println("Channel: " + channel);
        }
    }

    public void channelDown() {
        if (isOn && channel > 1) {
            channel--;
            System.out.println("Channel: " + channel);
        }
    }

    public int getVolume() {
        return volume;
    }

    public int getChannel() {
        return channel;
    }

    public boolean isOn() {
        return isOn;
    }
}