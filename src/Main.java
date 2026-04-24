public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

interface Engine {
    void increase();
    void decrease();
    int getInternalSpeed();
    String getName();
}

class GasolineEngine implements Engine {
    private int speed = 0;
    public void increase() { speed++; }
    public void decrease() { speed--; }
    public int getInternalSpeed() { return speed; }
    public String getName() { return "Gasoline Engine"; }
}

class ElectronicEngine implements Engine {
    private int speed = 0;
    public void increase() { speed++; }
    public void decrease() { speed--; }
    public int getInternalSpeed() { return speed; }
    public String getName() { return "Electronic Engine"; }
}

class MixedHybridEngine implements Engine {
    private GasolineEngine gas = new GasolineEngine();
    private ElectronicEngine electric = new ElectronicEngine();
    private int speed = 0;

    public void increase() { speed++; sync(); }
    public void decrease() { speed--; sync(); }

    // Logic: Electric < 50, Gas >= 50. Cost-optimized (only one active).
    private void sync() {
        if (speed < 50) {
            // Logic to simulate running only electric
        } else {
            // Logic to simulate running only gas
        }
    }

    public int getInternalSpeed() { return speed; }
    public String getName() {
        return (speed < 50) ? "Hybrid [Mode: Electric]" : "Hybrid [Mode: Gas]";
    }
}