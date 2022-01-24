package ro.fasttrackit.curs15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuoteServiceTest {
    @Test
    @DisplayName("WHEN null quotes are given THEN empty quote list is returned")
    void testGetQuotesNull() {
        //SETUP
        QuoteService quoteService = new QuoteService(null);

        //RUN
        List<String> result = quoteService.getAllQuotes();

        //ASSERT
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("WHEN a list of quotes is give THEN only the text is returned")
    void testGetQuotesList() {
        //SETUP
        QuoteService quoteService = buildQuoteServiceWithSomeAuthors();

        //RUN
        List<String> actual = quoteService.getAllQuotes();

        //ASSERT
        assertThat(actual).isNotNull();
        assertThat(actual).hasSize(2);
        assertThat(actual).containsExactly("Pasarile-s somnoroase", "Curat murdar!");
    }

    @Test
    @DisplayName("WHEN no quotes THEN no quotes are returned for author")
    void testGetAuthorQuotesNoQuotes() {
        //SETUP
        QuoteService quoteService = new QuoteService(null);

        //RUN
        List<Quote> actual = quoteService.getQuotesForAuthor("Eminescu");

        //ASSERT
        assertThat(actual).isNotNull();
        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("WHEN we have quotes but none for the author THEN getQuotesForAuthor returns empty list")
    void testNoAuthorQuotes() {
        //SETUP
        QuoteService quoteService = buildQuoteServiceWithSomeAuthors();
        //RUN
        List<Quote> actual = quoteService.getQuotesForAuthor("John Doe");
        //ASSERT
        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("WHEN we have quotes for the requested author THEN getQuotesForAuthor returns them all")
    void testQuotesForAuthor() {
        //SETUP
        QuoteService quoteService = new QuoteService(List.of(
                new Quote(1, "Eminescu", "Pasarile-s somnoroase"),
                new Quote(2, "Caragiale", "Curat murdar!"),
                new Quote(3, "Eminescu", "Iara noi, epigonii"),
                new Quote(4, "Shakespeare", "To be or not to be..."),
                new Quote(5, "Eminescu", "Lucreafar")
        ));

        //RUN
        List<Quote> actual = quoteService.getQuotesForAuthor("Eminescu");

        boolean eminescu = actual.contains(new Quote(1, "Eminescu", "Pasarile-s somnoroase"));
        System.out.println(eminescu);
        //ASSERT
        assertThat(actual).hasSize(3)
                .containsExactlyInAnyOrder(
                        new Quote(1, "Eminescu", "Pasarile-s somnoroase"),
                        new Quote(3, "Eminescu", "Iara noi, epigonii"),
                        new Quote(5, "Eminescu", "Lucreafar"));
    }


    private QuoteService buildQuoteServiceWithSomeAuthors() {
        return new QuoteService(List.of(
                new Quote(1, "Eminescu", "Pasarile-s somnoroase"),
                new Quote(2, "Caragiale", "Curat murdar!")
        ));
    }

}
