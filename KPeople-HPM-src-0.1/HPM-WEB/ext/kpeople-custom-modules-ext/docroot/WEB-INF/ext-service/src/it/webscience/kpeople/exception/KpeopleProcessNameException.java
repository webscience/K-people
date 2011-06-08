package it.webscience.kpeople.exception;

import com.liferay.portal.kernel.exception.PortalException;



public class KpeopleProcessNameException extends PortalException
{
  public KpeopleProcessNameException()
  {
      super();
  }

  public KpeopleProcessNameException(String msg)
  {
    super(msg);
  }

  public KpeopleProcessNameException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public KpeopleProcessNameException(Throwable cause) {
    super(cause);
  }
}