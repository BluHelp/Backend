package br.senac.bluhelp.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.senac.bluhelp.exception.address.AddressNotFoundException;
import br.senac.bluhelp.exception.comment.CommentNotFoundException;
import br.senac.bluhelp.exception.contact.ContactEmailRegisteredException;
import br.senac.bluhelp.exception.contact.ContactNotFoundException;
import br.senac.bluhelp.exception.contact.ContactPhoneRegisteredException;
import br.senac.bluhelp.exception.project.ProjectNotFoundException;
import br.senac.bluhelp.exception.review.ReviewNotFoundException;
import br.senac.bluhelp.exception.user.UserCpfRegisteredException;
import br.senac.bluhelp.exception.user.UserNotFoundException;

@ControllerAdvice
public class CustomControllerAdvice {

	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAddressNotFoundException(Exception exception) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage()));
	}

	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCommentNotFoundException(Exception exception) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage()));
	}

	@ExceptionHandler(ContactEmailRegisteredException.class)
	public ResponseEntity<ErrorResponse> handleContactEmailRegistredException(Exception exception) {

		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage()));
	}

	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleContactNotFoundException(Exception exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage()));
	}

	@ExceptionHandler(ContactPhoneRegisteredException.class)
	public ResponseEntity<ErrorResponse> handleContactPhoneRegistredException(Exception exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage()));
	}

	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProjectNotFoundException(Exception exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage()));
	}

	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleReviewNotFoundException(Exception exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage()));
	}

	@ExceptionHandler(UserCpfRegisteredException.class)
	public ResponseEntity<ErrorResponse> handleUserCpfRegistredException(Exception exception) {

		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage()));
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception exception) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage()));
	}

}
