package com.example.demo.Error;



import java.time.LocalDateTime;

public class CustomErrorResponse { 
LocalDateTime timestamp;

String status;
String errorCode;
String message;
public CustomErrorResponse(String errorCode, String message) {
this.errorCode = errorCode; this.message = message;
} public LocalDateTime getTimestamp() {
return timestamp;
} public void setTimestamp (LocalDateTime timestamp) { this.timestamp = timestamp;
} public String getStatus() {
return status; }
public void setStatus (String status) {
this.status = status;
}
public String getErrorCode() {
return errorCode;
  }
 public void setErrorCode(String errorCode) {
 this.errorCode = errorCode; }
  public String getMessage() { return message;
  } public void setMessage(String message) {
 this.message = message;
 }}