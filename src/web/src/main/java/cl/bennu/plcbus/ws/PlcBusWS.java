package cl.bennu.plcbus.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 27-03-14
 * Time: 11:22 AM
 */
@WebService
public class PlcBusWS {

    @WebMethod(operationName = "version")
    public String version(@WebParam(name = "i") int i, @WebParam(name = "j") int j) {
        System.out.println(i);
        System.out.println(j);
        return "1.2";
    }


}
