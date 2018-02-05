//package fr.societe.generale.rest;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 
// * @author mbarki 3 févr. 2018 00:51:33
// *
// */
//public class UnauthorizedEntryPoint implements AuthenticationEntryPoint
//{
//    @Override
//    public void commence(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            AuthenticationException authException
//    ) throws IOException, ServletException
//    {
//        response.sendError(
//                HttpServletResponse.SC_UNAUTHORIZED,
//                "Unauthorized: Authentication token was either missing or invalid."
//        );
//    }
//}
