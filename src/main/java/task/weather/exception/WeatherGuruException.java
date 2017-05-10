/**
 * 
 */
package task.weather.exception;


/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */
public class WeatherGuruException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -3851318319174742377L;
    
    public WeatherGuruException() {
        super();
    }

    public WeatherGuruException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public WeatherGuruException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherGuruException(String message) {
        super(message);
    }

    public WeatherGuruException(Throwable cause) {
        super(cause);
    }

}
