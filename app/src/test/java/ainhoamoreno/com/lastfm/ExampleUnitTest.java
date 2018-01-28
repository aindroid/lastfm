package ainhoamoreno.com.lastfm;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ainhoamoreno.com.lastfm.data.artist.search.ArtistSearch;
import ainhoamoreno.com.lastfm.network.LastFmService;
import ainhoamoreno.com.lastfm.repository.ArtistRepository;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    String a = "{\"results\":{\"opensearch:Query\":{\"#text\":\"\",\"role\":\"request\",\"searchTerms\":\"cher\",\"startPage\":\"1\"},\"opensearch:totalResults\":\"58148\",\"opensearch:startIndex\":\"0\",\"opensearch:itemsPerPage\":\"1\",\"artistmatches\":{\"artist\":[{\"name\":\"Cher\",\"listeners\":\"1032151\",\"mbid\":\"bfcc6d75-a6a5-4bc6-8282-47aec8531818\",\"url\":\"https://www.last.fm/music/Cher\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/660336460ad748babe1915a8cefca481.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/660336460ad748babe1915a8cefca481.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/660336460ad748babe1915a8cefca481.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/660336460ad748babe1915a8cefca481.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/660336460ad748babe1915a8cefca481.png\",\"size\":\"mega\"}]}]},\"@attr\":{\"for\":\"cher\"}}}";

    @Mock
    LastFmService service;

    ArtistRepository repository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        repository = new ArtistRepository(service);
    }

    @Test
    public void addition_isCorrect() throws Exception {

        Gson g = new Gson();
        ArtistSearch artistSearch =  g.fromJson(a, ArtistSearch.class);

        assertNotNull(artistSearch);
    }

}