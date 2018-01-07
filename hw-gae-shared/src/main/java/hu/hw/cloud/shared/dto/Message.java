/**
 * 
 */
package hu.hw.cloud.shared.dto;

import java.io.Serializable;

/**
 * @author CR
 *
 */
public class Message implements Serializable {

    String message;

    public Message(String message) {
        this.message = message;
    }

    public Message() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
