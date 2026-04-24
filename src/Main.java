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
/////////////////////////// car class ////////////////////////

class Car {
    private Engine engine;
    private int currentSpeed = 0;
    private boolean isRunning = false;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public void setEngine(Engine newEngine) {
        System.out.println(">> Replacing engine with: " + newEngine.getName());
        this.engine = newEngine;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            currentSpeed = 0;
            System.out.println("Car started. Speed: " + currentSpeed);
        }
    }

    public void accelerate() {
        if (!isRunning) return;
        int target = Math.min(currentSpeed + 20, 200);
        while (currentSpeed < target) {
            currentSpeed++;
            engine.increase(); // Advise the engine of speed change
        }
        System.out.println("Accelerating... Current Speed: " + currentSpeed + " | Engine: " + engine.getName());
    }

    public void brake() {
        int target = Math.max(currentSpeed - 20, 0);
        while (currentSpeed > target) {
            currentSpeed--;
            engine.decrease(); // Advise the engine of speed change
        }
        System.out.println("Braking... Current Speed: " + currentSpeed + " | Engine: " + engine.getName());
    }

    public void stop() {
        if (currentSpeed == 0) {
            isRunning = false;
            System.out.println("Car stopped successfully.");
        } else {
            System.out.println("Error: Cannot stop. Speed must be 0! Current speed: " + currentSpeed);
        }
    }
}

////////////////////////////////////////////car factory ///////////////////////////////////
class CarFactory {
    public enum EngineType { GAS, ELECTRIC, HYBRID }

    public static Car createCar(EngineType type) {
        Engine engine;
        switch (type) {
            case GAS: engine = new GasolineEngine(); break;
            case ELECTRIC: engine = new ElectronicEngine(); break;
            case HYBRID: engine = new MixedHybridEngine(); break;
            default: throw new IllegalArgumentException("Unknown Engine Type");
        }
        return new Car(engine);
    }
}