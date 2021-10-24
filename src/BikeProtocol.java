public class BikeProtocol {

    public String processAlertSignal() {
        // TODO: I will have to figure out
        // how to communicate with the android app from here.
        // The android app will return the timeinmillis when it received the alert signal
        // or in this context, when server tried to connect to the android app.
        // For now, I am just simulating the work-flow.
        return System.currentTimeMillis() + "";
    }
}
