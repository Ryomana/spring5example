package lu.ryo.springframework.spring5webapp.bootstrap;

import lu.ryo.springframework.spring5webapp.model.Author;
import lu.ryo.springframework.spring5webapp.model.Book;
import lu.ryo.springframework.spring5webapp.model.Publisher;
import lu.ryo.springframework.spring5webapp.repositories.AuthorRepository;
import lu.ryo.springframework.spring5webapp.repositories.BookRepository;
import lu.ryo.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        // Eric
        Publisher harper = new Publisher("Harper Collins", "Harpers home address");
        publisherRepository.save(harper);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", harper);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);


        // Rod
        Publisher worx = new Publisher("Worx", "Worx address");
        publisherRepository.save(worx);

        Author rod = new Author("Rod", "Johnson");

        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }
}
