package Fasion.backend.exception;

/**
 * 아이디 중복값 찾는 코드
 */
public class DuplicateMemberIdException extends RuntimeException{
    public DuplicateMemberIdException(String message) {
        super(message);
    }
}
