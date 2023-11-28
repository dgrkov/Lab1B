package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "author-servlet", urlPatterns = "/author")
public class AuthorServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorServlet(SpringTemplateEngine springTemplateEngine, AuthorService authorService, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("authorList",authorService.listAuthors());
        String isbn = req.getParameter("isbn");
        req.getSession().setAttribute("isbn", isbn);
        context.setVariable("isbn", isbn);
        springTemplateEngine.process("authorList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authId = req.getParameter("authorId");
        String isbn = req.getSession().getAttribute("isbn").toString();
//        Book book = bookService.findBookByIsbn(isbn);
//        req.getSession().setAttribute("book", book);
        bookService.addAuthorToBook(Long.parseLong(authId), isbn);
        resp.sendRedirect("/bookDetails?isbn=" + isbn + "&authId=" + authId);
    }
}
