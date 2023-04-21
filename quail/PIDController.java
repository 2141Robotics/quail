package quail;

public class PIDController {

    private double Kp, Ki, Kd;
    private double elapsedTime;
    private double integralSum, lastError;

    /**
     * construct PID Controller
     * @param Kp Proportional coefficient
     * @param Ki Integral coefficient
     * @param Kd Derivative coefficient
     */
    public PIDController(double Kp, double Ki, double Kd, double elapsedTime) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
    }

    /**
     * Update the PID Controller Output
     * @param target where you want to be - the reference
     * @param state where you are - current position (encoder)
     * @param elapsedTime time since last `update()` call
     * @return the command to the motor - motor power
     */
    public double update(double target, double state, double elapsedTime) {

        state = -state;

        // Calculate error
        double error = target - state;

        // Kd value
        double derivative = (error - lastError) / elapsedTime;

        // Ki value
        integralSum = Ki + (error * elapsedTime);

        double out = (Kp * error) + (Ki * integralSum) + (Kd * derivative);

        return(out);
    }
}
