package polovinko.leontii.gifrates.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import polovinko.leontii.gifrates.dto.error.ErrorResponseBody;
import polovinko.leontii.gifrates.handlers.GlobalExceptionHandler;

class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler globalExceptionHandler;

  @BeforeEach
  void setUp() {
    globalExceptionHandler = new GlobalExceptionHandler();
  }

  @Test
  void handleException_whenIllegalArgumentExceptionWithMessagePassed_thenExceptionMessageReturnedInResponse() {
    IllegalArgumentException exception = new IllegalArgumentException("Exception message");

    ErrorResponseBody response = globalExceptionHandler.handleException(exception);

    assertEquals(exception.getMessage(), response.getMessage());
    assertNotNull(response.getHappenedAt());
  }

  @Test
  void handleException_whenIllegalArgumentExceptionWithoutMessagePassed_thenDefaultMessageReturnedInResponse() {
    IllegalArgumentException exception = new IllegalArgumentException();

    ErrorResponseBody response = globalExceptionHandler.handleException(exception);

    assertEquals("Illegal argument", response.getMessage());
    assertNotNull(response.getHappenedAt());
  }

  @Test
  void handleException_whenRuntimeExceptionWithMessagePassed_thenExceptionMessageReturnedInResponse() {
    RuntimeException exception = new RuntimeException("Exception Message");
    ErrorResponseBody response = globalExceptionHandler.handleException(exception);

    assertEquals(exception.getMessage(), response.getMessage());
    assertNotNull(response.getHappenedAt());
  }

  @Test
  void handleException_whenRuntimeExceptionWithoutMessagePassed_thenDefaultMessageReturnedInResponse() {
    RuntimeException exception = new RuntimeException();
    ErrorResponseBody response = globalExceptionHandler.handleException(exception);

    assertEquals("Internal Server Error", response.getMessage());
    assertNotNull(response.getHappenedAt());
  }
}
