public class LuzAmbiente {
    private boolean isOn;
    private int brightness; // Brightness level from 0 to 100

    public LuzAmbiente() {
        this.isOn = false;
        this.brightness = 50; // Default brightness
    }

    public void powerToggle() {
        isOn = !isOn;
        System.out.println("Luz Ambiente está agora " + (isOn ? "LIGADA" : "DESLIGADA"));
    }

    public void increaseBrightness() {
        if (isOn && brightness < 100) {
            brightness += 10;
            if (brightness > 100) brightness = 100;
            System.out.println("Brilho: " + brightness);
        }
    }

    public void decreaseBrightness() {
        if (isOn && brightness > 0) {
            brightness -= 10;
            if (brightness < 0) brightness = 0;
            System.out.println("Brilho: " + brightness);
        }
    }

    public int getBrightness() {
        return brightness;
    }

    public boolean isOn() {
        return isOn;
    }
}
